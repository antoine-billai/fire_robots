package io.incendie;

import io.carte.Case;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class Incendie{
    private Case position;      //Case sur laquelle est située l'Incendie
    private int litres_eau;     //Quantité d'eau nécessaire pour éteindre l'Incendie
    private int traite;         //Variable déterminant l'état de l'Incendie 
                                    //-1 si traité | 1 si en cours de traitement | 0 si non traité
    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public Incendie(Case position, int litres_eau){
        this.position=position;
        this.litres_eau=litres_eau;
        this.traite = 0;
    }

    /* ---------------------------------------------------------
                    Methodes Définition Variables
    --------------------------------------------------------- */
    public void setTraite(int i){
        this.traite = i;
    }

    public void setPosition(Case box){
        this.position=box;
    }

    public void setLitresEau(int intensite){
        this.litres_eau=intensite;
    }
    /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
    public Case getPosition(){
        return this.position;
    }

    public int getLitresDeau(){
        return this.litres_eau;
    }

    public int getTraite(){
        return this.traite;
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */

    /**
     * Affiche l'Incendie sous forme d'une chaine de caractère
     */
    public void printIncendie(){
        System.out.println("[("+this.position.getLigne()+";"+this.position.getColonne()+"); "+this.litres_eau+"]");
    }

    /**
     * Dessine l'Incendie si il n'est pas traité
     * @param gui L'interface graphique
     */
    public void drawIncendie(GUISimulator gui){
        int taille = 16;                                //Nombre de pixels sur la Case
        int pxl = 40/taille;                            //Taille d'un pixel
        int Y = this.position.getPosY()+40/2-2*pxl;     //Position ligne en pixel du début du dessin
        int X = this.position.getPosX()-40/2+3*pxl;     //Position colonne en pixel du début du dessin

        if (this.traite==-1){ //Si l'incendie est traité on ne le dessine pas
            this.getPosition().CaseDraw(gui);
            return;
        };

        // Ligne 0
        for (int i=5; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y, Color.RED, Color.RED, 40/16));

        // Ligne 1
        for (int i=2; i<6; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-1*pxl, Color.RED, Color.RED, 40/16));
        for (int i=7; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-1*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=13; i<14; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-1*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 2
        for (int i=1; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, Color.RED, Color.RED, 40/16));
        for (int i=3; i<6; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=6; i<10; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, Color.WHITE, Color.WHITE, 40/16));
        for (int i=10; i<13; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=13; i<15; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 3
        for (int i=1; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, Color.RED, Color.RED, 40/16));
        for (int i=3; i<5; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=5; i<11; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, Color.WHITE, Color.WHITE, 40/16));
        for (int i=11; i<13; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=13; i<15; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 4
        for (int i=0; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, Color.RED, Color.RED, 40/16));
        for (int i=3; i<5; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+5*pxl, Y-4*pxl, Color.WHITE, Color.WHITE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y-4*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-4*pxl, Color.WHITE, Color.WHITE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-4*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+9*pxl, Y-4*pxl, Color.WHITE, Color.WHITE, 40/16));
        for (int i=10; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=12; i<15; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 5
        for (int i=0; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, Color.RED, Color.RED, 40/16));
        for (int i=3; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-5*pxl, Color.WHITE, Color.WHITE, 40/16));
        for (int i=8; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=12; i<16; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 6
        gui.addGraphicalElement(new Rectangle(X, Y-6*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-6*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+3*pxl, Y-6*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+4*pxl, Y-6*pxl, Color.RED, Color.RED, 40/16));
        for (int i=5; i<11; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-6*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=11; i<16; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-6*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 7
        for (int i=1; i<4; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, Color.RED, Color.RED, 40/16));
        for (int i=4; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-7*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-7*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=9; i<14; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+15*pxl, Y-7*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 8
        for (int i=1; i<5; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-8*pxl, Color.RED, Color.RED, 40/16));
        for (int i=5; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-8*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=8; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-8*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+12*pxl, Y-8*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+13*pxl, Y-8*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+15*pxl, Y-8*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 9
        for (int i=2; i<6; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-9*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y-9*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=7; i<14; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-9*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 10
        for (int i=2; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-10*pxl, Color.RED, Color.RED, 40/16));
        for (int i=7; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-10*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        for (int i=9; i<13; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-10*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+14*pxl, Y-10*pxl, Color.ORANGE, Color.ORANGE, 40/16));

        // Ligne 11
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-11*pxl, Color.RED, Color.RED, 40/16));
        for (int i=4; i<13; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-11*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 12
        for (int i=4; i<14; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-12*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 13
        gui.addGraphicalElement(new Rectangle(X+4*pxl, Y-13*pxl, Color.RED, Color.RED, 40/16));
        for (int i=6; i<11; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-13*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+12*pxl, Y-13*pxl, Color.ORANGE, Color.ORANGE, 40/16));
        gui.addGraphicalElement(new Rectangle(X+13*pxl, Y-13*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 14
        gui.addGraphicalElement(new Rectangle(X+4*pxl, Y-14*pxl, Color.RED, Color.RED, 40/16));
        for (int i=7; i<11; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-14*pxl, Color.RED, Color.RED, 40/16));
        gui.addGraphicalElement(new Rectangle(X+13*pxl, Y-14*pxl, Color.RED, Color.RED, 40/16));

        // Ligne 15
        for (int i=8; i<10; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-15*pxl, Color.RED, Color.RED, 40/16));

        //Ligne 16
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-16*pxl, Color.RED, Color.RED, 40/16));
    }
}
