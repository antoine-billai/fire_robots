package io.Simulation;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import io.robot.*;
import io.carte.*;
import io.robotChef.*;
import io.evenement.Evenement;
import io.incendie.*;
import io.Simulation.*;
import java.util.LinkedList;

import java.io.*;
import java.util.zip.DataFormatException;

public class Simulateur implements Simulable {
    private GUISimulator gui;
    private String[] args;
    private long dateSimulation;
    private DonneesSimulation DS;
    private LinkedList<Evenement> listevenements;
    private long time;
    private DonneesSimulation DSsaved;
    //private RobotChef robotChef;

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public Simulateur(GUISimulator gui, String[] args, LinkedList<Evenement> list, long dateSimulation)
        throws FileNotFoundException, DataFormatException{
        this.gui = gui;
        gui.setSimulable(this);
        this.time=0;

        this.DS= LecteurDonnees.lire(args,0);
        this.DSsaved= new DonneesSimulation(LecteurDonnees.lire(args,0));
        this.listevenements = list;
        this.dateSimulation = dateSimulation;

        DS.Draw(gui);
        // for(int i=0; i<DS.getRobots().length; i++)
        //     DS.getRobots()[i].Draw(0,gui);
    }

    /* ---------------------------------------------------------
                   Methodes Obtention Variables
    --------------------------------------------------------- */
    public long getDateSimulation(){
        return this.dateSimulation;
    }

    public DonneesSimulation getDS(){
        return this.DS;
    }

    public LinkedList<Evenement>getList(){
        return this.listevenements;
    }

    public GUISimulator getGui(){
        return this.gui;
    }

    /* ---------------------------------------------------------
                   Methodes Obtention Variables
    --------------------------------------------------------- */
    public void setList(LinkedList<Evenement> list){
        this.listevenements=list;
    }

    /* ---------------------------------------------------------
                        Autres Méthodes
    --------------------------------------------------------- */
    private long incrementeDate(long tempo){
        return this.dateSimulation+tempo;
    }

    public void ajouteEvenement(Evenement e){
        //pour avoir liste d'elements rangés selon leur date
        this.listevenements.add(e);
    }

    private boolean simulationTerminee(){
        if(this.listevenements.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public void next(){
        for (int i=listevenements.size()-1; i>=0; i--){
            
            if (listevenements.get(i).getDateExe() <= dateSimulation){
                listevenements.get(i).printEvenement();
                listevenements.get(i).execute();
                listevenements.remove(i);
            }
        }

        RobotChef robotChef = new RobotChef(this);
        
          //ROBOT CHEF 1
        // Fonction principale qui organise le déplacement de tous les robots pour éteindre les incendies selon la stratégie
        // élémentaire d'envoi de robots libres vers les incendies libres sans conditions particulières et sans remplissage
        // des robots vides
        //robotChef.eteindreIncendie();


          //ROBOT CHEF 2
        // Fonction principale qui organise le déplacement de tous les robots pour éteindre les incendies selon la stratégie
        // élémentaire d'envoi de robots libres vers les incendies libres sans conditions particulières et avec remplissage
        // des robots vides

        //robotChef.eteindreIncendieRemplirEau();

          //ROBOT CHEF 3
        // Fonction principale qui organise le déplacement de tous les robots pour éteindre les incendies selon la stratégie
        // plus développée de sélection du robot le plus proche d'un incendie et avec remplissage des robots vides
        robotChef.eteindreIncendieRobotPlusProche();


        gui.reset();
        DS.Draw(gui);
        dateSimulation = incrementeDate(1000);
          //System.out.println(DS.getRobots()[2].getVolumeDeau());
           // ajoute de evenement dans listevenements
        }



        /*if(!simulationTerminee()){
            System.out.println("YEEEY");
            if (time!=-1){
                System.out.println("hohe");
                time=this.listevenements.get((int)this.dateSimulation).execute();
            }else {
                for (int i=0; i<DS.getRobots().length; i++)
                    DS.getRobots()[i].Draw(100, gui);
                this.dateSimulation = incrementeDate(1);
                // this.listevenements.remove(0);
                if(dateSimulation<listevenements.size())
                    time=0;
                else
                    System.out.println("Plus d'evenement");*/




    @Override
    public void restart(){
        this.listevenements.clear();
        this.DS = this.DSsaved;
        this.DS.Draw(gui);
    }
  }
