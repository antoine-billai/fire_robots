package io.robot;

import io.carte.Case;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

/*public Robot(Case position, int volumeDeau, int maxVolumeDeau, double vitesse, double tempsRemplissage, double debit)*/
/*  public enum NatureTerrain{EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT}*/

//Drone
public class Drone extends Robot{

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public Drone(Case position){
        super(position,10000,10000,100/3.6,30*60,10000.0/30);
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
    /**
     * Dessine le Drone
     * @param gui Interface graphique
     */
    @Override
    public void Draw(GUISimulator gui){ //Pourcentage doit être compris entre 0 et 100
        int limite=7*super.getpourcentage()/100;//Niveau de remplissage
        Color couleur_drone=Color.CYAN;         //Couleur de la zone remplie
        int pxl = 40/12;                        //Taille d'un pixel

        int Y = this.getPosition().getPosY()+40/2-3*pxl;
        int X = this.getPosition().getPosX()-40/2+pxl;
        int ligne=0;

        //Ligne 0
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=5; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y, couleur_drone, couleur_drone, pxl));

        //Ligne 1
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=4; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-1*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 2
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-2*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=3; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+11*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 3
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=1; i<4; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+4*pxl, Y-3*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=5; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-3*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=8; i<11; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 4
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=0; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 5
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-5*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=4; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+9*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+11*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 6
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+9*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 7
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=0; i<2; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=3; i<5; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=7; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=10; i<12; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
    }
}
