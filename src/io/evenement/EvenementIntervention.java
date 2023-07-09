package io.evenement;

import gui.GUISimulator;

import io.robot.*;
import io.incendie.*;

import java.util.LinkedList;
import javax.lang.model.util.ElementScanner14;

public class EvenementIntervention extends Evenement{
    Robot robot;                        //robot intervenant
    Incendie incendie;                  //incendie à traiter
    Incendie[] feu;                     //Liste des incendies présents sur la carte
    static int incendieEtteint = 0;     //Etat de l'incendie

    public EvenementIntervention(long date, Robot robot, Incendie incendie, Incendie[] feu){
        super(date);
        this.robot = robot;
        this.incendie = incendie;
        this.feu=feu;
    }

    /**
     * Retourne la durée d'un événement
     */
    @Override
    public long duree(){
        long duree;
        if(robot.getVolumeDeau()<=incendie.getLitresDeau()){
            duree=(long)(Double.valueOf(robot.getVolumeDeau())/robot.getdebit());
        } else {
            duree=(long)(Double.valueOf(incendie.getLitresDeau())/robot.getdebit());
        }
        return duree;
    }

    /**
     * Ajoute un evenement a la liste list_event. Cet evenement commencera a la date dateDebut
     * @param dateDebut date de début d'évnement
     * @param list_event liste contenant les événements à executer
     */
    @Override
    public void pushEvent(long dateDebut, LinkedList<Evenement> list_event){
        robot.setTraite(1);
        incendie.setTraite(1);
        this.setDate(dateDebut+this.duree());
        list_event.add(this);
    }

    /**
     * Affiche un Evenement
     */
    @Override
    public void printEvenement(){
        System.out.print("EVENEMENT INTERVENTION - "+this.getDateExe()+" - INCENDIE : "+
                incendie.getLitresDeau()+" - ROBOT : "+robot.getVolumeDeau()+"\n");
    }

    /**
     * Executée à chaque clock, 
     * Défini l'état du robot (Occupé, Vide, Libre) et de l'incendie (traité, en cours de traitement)
     * Eteint l'incendie en vidant le réservoir du robot
     */
    @Override
    public boolean execute(){
        if(robot.getVolumeDeau()>=incendie.getLitresDeau()){
            robot.deverserEau(incendie.getLitresDeau());
            incendie.setLitresEau(0);
            incendie.setTraite(-1);
            incendieEtteint++;
            robot.setTraite(0);
        }  else {
            int b = robot.deverserEau(robot.getVolumeDeau());
            incendie.setLitresEau(incendie.getLitresDeau()- b);
            robot.setTraite(-1);
            incendie.setTraite(0);
        }

        return true;
    }
}
