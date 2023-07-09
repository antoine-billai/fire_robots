package io.evenement;

import java.util.LinkedList;

import gui.GUISimulator;

import io.robot.*;
import io.carte.*;

public class EvenementRemplissage extends Evenement{
    Robot robot;
    Carte carte;

    public EvenementRemplissage(long date, Robot robot, Carte carte){
        //Error Emplacement ?
        super(date);
        this.robot = robot;
        this.carte = carte;
    }

    @Override
    public void printEvenement(){
        System.out.println("EVENEMENT - remplissage : "+ robot.getPosition().getLigne()+","+ robot.getPosition().getColonne() +" date : " + this.getDateExe());
    }

    @Override
    public long duree(){
    //retourne le temps d'un event
        return (long)robot.getTempsRemplissage();
    }

    @Override
    public boolean execute(){
        // int remplissage = (int)((double)robot.getMaxVolumeDeau()/(double)this.duree());
        this.robot.remplirReservoir();
        this.robot.setTraite(0);
        // return robot.getTempsRemplissage();
        return false;
    }

    @Override
    public void pushEvent(long dateDebut, LinkedList<Evenement> list_event) {
        robot.setTraite(1);
        this.setDate(dateDebut+this.duree());
        list_event.add(this);
    }
}
