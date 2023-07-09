package io.robot;

import io.IllegalActionException;
import io.carte.*;
import io.incendie.Incendie;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public abstract class Robot{

    private Case position;              //Case sur laquelle le Robot est positionné
    protected int volume_eau;           //Volume d'eau contenu dans le réservoir du Robot
    protected int max_volume_eau;       //Contenance maximale du réservoir d'eau
    private double vitesse;             //Vitesse de marche du Robot
    private double temps_remplissage;   //Durée de remplissage du réservoir d'eau
    private double debit;               //Vitesse de deversement en L/s
    private int traite;                 //Détermine l'état du Robot
                                            //1 si Robot occupé | 0 sinon | -1 si le reservoir est vide
    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public Robot(Case position, int volume_eau, int max_volume_eau, double vitesse, double temps_remplissage, double debit){
        this.position = position;
        this.volume_eau = volume_eau;
        this.max_volume_eau = max_volume_eau;
        this.vitesse = vitesse;
        this.temps_remplissage = temps_remplissage;
        this.debit = debit;
        this.traite=0;
    }

    /* ---------------------------------------------------------
                    Methodes Definition Variables
    --------------------------------------------------------- */
    public void setVitesse(double vit){
        this.vitesse=vit;
    }

    public void setPosition(Case position){
        this.position=position;
    }

    public void setTraite(int i){
        this.traite = i;
    }

    public void setVolumeEau(int pourcentage){
        this.volume_eau=this.max_volume_eau*pourcentage/100;
    }



    public void setMaxVolumeDeau(int max_volume_eau){
        this.max_volume_eau=max_volume_eau;}

    /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
    public Case getPosition(){
        return position;
    }

    public int getVolumeDeau(){
        return volume_eau;
    }

    public double getVitesse(Case.NatureTerrain nature){
        return vitesse;
    }

    public int getMaxVolumeDeau(){
        return max_volume_eau;
    }

    public double getTempsRemplissage(){
        return temps_remplissage;
    }

    public int getTraite(){
        return traite;
    }

    public double getdebit(){
        return debit;
    }

    public int getpourcentage(){
        return 100*volume_eau/max_volume_eau;
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
    /**
     * Affiche le Robot sous la forme d'une chaine de caractères
     */
    public void printRobot(){
        System.out.println("[("+ this.position.getLigne() +";"+ this.position.getColonne() +"); "+ this.vitesse +"], "+ "volume_eau = "+ this.volume_eau+ ", volume_eau_max = " + this.max_volume_eau);
    }

    /**
     * Vide le réservoir d'un certain volume sur un Incendie
     * @param vol Volume d'eau a déverser
     */
    public int deverserEau(int vol){
        if(vol > volume_eau){
            int a = volume_eau;
            this.volume_eau=0;
            return a;
        } else
            this.volume_eau = volume_eau - vol;
            return vol;
        // try{
        //     if (!this.position.isInFire(feu))
        //         throw new IllegalActionException("Robot.deverserEau - Vous tentez de déverser plus d'eau que le robot n'en contient");

        //     if(vol > volume_eau){
        //         this.volume_eau=0;
        //     } else
        //         this.volume_eau = volume_eau - vol;

        // } catch(IllegalActionException e){
        //     System.out.println(e);
        //     e.printStackTrace();
        //     System.exit(1);
        // }
    }

    /**
     * Rempli le réservoir du Robot au maximum
     */
    public void remplirReservoir(){
        this.volume_eau = max_volume_eau;
    }

    /**
     * Déplace le Robot d'une case dans la direction donnée
     * @param carte La carte sur laquelle le Robot est placé
     * @param d La direction vers laquelle le Robot souhaite se déplacer
     * @return un temps
     */
    public double deplacement(Carte carte, Carte.Direction d){
         Case c = (carte.getVoisin(position,d)); //getVoisin est protégée par une assert
         double temps = carte.getTailleCase()/((getVitesse(position.getNature())+getVitesse(c.getNature()))/2);
         this.position = carte.getVoisin(this.position,d);
         return temps;
    }

    /**
     * Dessine le Robot
     * @param gui Interface graphique
     */
    public abstract void Draw( GUISimulator gui);
}
