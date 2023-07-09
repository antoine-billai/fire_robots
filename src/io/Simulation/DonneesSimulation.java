package io.Simulation;

import io.carte.Carte;
import io.incendie.*;
import io.robot.*;

import gui.GUISimulator;
import gui.Rectangle;

public class DonneesSimulation {
    private Carte map;
    private Incendie feu[];
    private Robot rob[];

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public DonneesSimulation(Carte map, Incendie feu[], Robot rob[]){
        this.map=map;
        this.feu=feu;
        this.rob=rob;
    }

    public DonneesSimulation(DonneesSimulation DS){
      this(DS.map,DS.feu,DS.rob);
    }

    /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
    public Carte getCarte(){
        return this.map;
    }

    public Incendie[] getIncendies(){
        return this.feu;
    }

    public Robot[] getRobots(){
        return this.rob;
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
    public void Draw(GUISimulator gui){
        this.map.CarteDraw(gui);
        for (int i=0; i<this.feu.length; i++){
            feu[i].drawIncendie(gui);
        }
        for (int i=0; i<this.rob.length; i++){
            // rob[i].Draw(rob[i].getPourcentage(), gui);
            rob[i].Draw(gui);
        }
    }

}
