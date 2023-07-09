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

public abstract class Evenement{
    private long date;

    //constructeur
    public Evenement(long dateDebut){
        this.date = dateDebut;
    }

    public void setDate(long date){
        this.date = date;
    }

    public long getDateExe(){
        return date;
    }

    //retourne le temps d'un event
    public long duree(){
      return 0;
    };

    //execute l'event
    public abstract boolean execute();

    public void pushEvent(long dateDebut, LinkedList<Evenement> list_event) {
        System.out.println("empty push");
    }

    public void printEvenement(){
        System.out.println("Je suis un événement :" + this.date);
    }
}
