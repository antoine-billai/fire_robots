package io.evenement;


import gui.GUISimulator;

import io.robot.*;
import io.Simulation.DonneesSimulation;
import io.carte.*;
import io.incendie.*;

import java.util.Iterator;
import java.util.LinkedList;

import java.util.ListIterator;
import javax.lang.model.util.ElementScanner14;

public class EvenementDeplacement extends Evenement{
    private Robot robot;
    private Case destination;
    private DonneesSimulation DS;
    private GUISimulator gui;
    private Case mouv;
    private Case départ;



    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */


    public EvenementDeplacement( long date, Robot robot, Case destination, DonneesSimulation DS, GUISimulator gui, Case départ){
        super(date);
        this.robot = robot;
        this.destination = destination;
        this.DS =DS;
        this.gui = gui;
        this.départ=départ;
    }

    /* ---------------------------------------------------------
                            Autres Fonctions
    --------------------------------------------------------- */

    public Robot getRobot(){
        return this.robot;
    };

    /**
     * Calcul la durée pour aller à une case appartenant au plus court chemin
     * @param chemin Chemin pour lequel le plusCourtChemin a été calculé vers l'incendie
     * @param incendie Incendie à éteindre
     * return la durée de l'action totale
     */
    public long duree(Chemin chemin, int i){
        return (long)chemin.getTemps(chemin.getPcc().get(i));
    }



    /**
     * Met un évenement deplacement élémentaire dans la liste d'événement
     * @param dateDebut Date du début du déplacement
     * @param list_event La liste d'événement
     * @param chemin Chemin pour lequel le plusCourtChemin a été calculé
     * @param indice de la case destination dans plusCourtChemin
     * @return La duree du deplacement
     */
    public void pushEventElem(long dateDebut, LinkedList<Evenement> list_event, Chemin chemin, int i){
        this.setDate(dateDebut + duree(chemin,i));
        list_event.add(this);
    }



    /**
     * Met une série d'évenements deplacement élémentaire dans la liste d'événement allant de
     * la position actuelle du robot vers la case destination
     * @param robot Robot qui se déplace
     * @param destination Case d'arrivé
     * @param dateDebut Date du début du déplacement
     * @param list_event La liste d'événement
     * @param DS Donnees de la Simulation
     * @param gui Interface graphique
     * @return La duree de tous les déplacements
     */
    public static long pushEvent(Robot robot,Case destination ,long dateDebut, LinkedList<Evenement> list_event,DonneesSimulation DS, GUISimulator gui, Case depart){
        robot.setTraite(1);
        Case caseactuelle = depart;
        Chemin chemin = new Chemin(DS.getCarte(), robot);
        LinkedList<Case> mouvList = chemin.plusCourtChemin(caseactuelle, destination);;

        System.out.println("Départ : "+ caseactuelle.printCaseString() 
                        + "\nTaille du chemin : " + mouvList.size() 
                        + "\n Arrivée : " + destination.printCaseString());

        System.out.println("\nAffichage du chemin");
        for (Case element : mouvList) {
            element.printCase();
        }

        if(mouvList.size() == 0){
            System.out.println("\nliste nulle");
            return 0;
        }

        System.out.println("\nAffichage des evenements : " + mouvList.size());
        for (int i=1; i<mouvList.size(); i++){
            EvenementDeplacement event = new EvenementDeplacement(dateDebut, robot, mouvList.get(i), DS, gui, robot.getPosition());
            event.printEvenement();
            event.pushEventElem(dateDebut, list_event, chemin, i);
            
            System.out.println("\n Affichage liste event");
            printListEvent(list_event);
        }
        return (long)chemin.getTemps(destination);
    } 

    @Override
    public void printEvenement(){
        System.out.println("EVENEMENT - destination : "+destination.getLigne()+","+destination.getColonne() +" date : " + this.getDateExe());
    }


    public static void printListEvent(LinkedList<Evenement> list){ int i;
        if (list.size()==0){
                System.out.println("la liste est vide");
                return;
        }
        for (i = 0; i < list.size();i ++){
                list.get(i).printEvenement();
        }
    }


    /**
     * effectue le déplacement élémentaire
     * @return true si déplacement réussi et false si le robot ne peut pas bouger
     */
    public boolean execute(){

        double tiempo=0;
        int x = robot.getPosition().getColonne();
        int y = robot.getPosition().getLigne();
        System.out.println("x = " + x +", y = "+y );
        int deplacementx = x - destination.getColonne();
        int deplacementy = y - destination.getLigne();

        if(deplacementx<0){
            tiempo = robot.deplacement(DS.getCarte(),Carte.Direction.EST);
        } else if (deplacementx>0){
            tiempo = robot.deplacement(DS.getCarte(),Carte.Direction.OUEST);
        } else if(deplacementy<0){
            tiempo = robot.deplacement(DS.getCarte(),Carte.Direction.NORD);
        } else if(deplacementy>0){
            tiempo = robot.deplacement(DS.getCarte(),Carte.Direction.SUD);
        } else{
            return false;
        }

        return true;
    }

}
