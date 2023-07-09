package io.robot;

import io.carte.*;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class RobotPattes extends Robot{
    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public RobotPattes(Case position){
        super(position,2147483647,2147483647,30/3.6,0,10);
    }

    /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
    /**
     * Retourne la vitesse du Robot en fonction de la Nature de la Case
     * @param nature Nature de la case sur laquelle se trouve le Robot
     */
    @Override
    public double getVitesse(Case.NatureTerrain nature){
        if (nature == Case.NatureTerrain.ROCHE){ //Vitesse divisée par 3 sur ROCHE
            return super.getVitesse(nature)/3;}
        else if (nature == Case.NatureTerrain.EAU ){ //Ne peut circuler sur EAU
            return -1;
        } else //Vitesse normale sur HABITAT, TERRAIN_LIBRE et FORET
            return super.getVitesse(nature);
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
    /**
     * Déplace le Robot d'une case dans la direction donnée
     * @param carte La carte sur laquelle le Robot est placé
     * @param d La direction vers laquelle le Robot souhaite se déplacer
     * @return une durée de déplacement si le Robot peut se déplacer sur la Case. -1 sinon.
     */
    @Override
    public double deplacement(Carte carte,Carte.Direction d){
        Case c = (carte.getVoisin(this.getPosition(),d)); //getVoisin protégée par une assert
        if (c.getNature() == Case.NatureTerrain.EAU ){ //Ne peut circuler sur EAU
            return -1;
        }
        return super.deplacement(carte,d);
    }

    /**
     * Dessine le RobotPattes
     * @param gui Interface graphique
     */
    @Override
    public void Draw(GUISimulator gui){
        Color couleur_drone=Color.CYAN;         //Couleur de la zone remplie
        int pxl = 40/12;                        //Taille d'un pixel

        int Y = this.getPosition().getPosY()+40/2-3*pxl;
        int X = this.getPosition().getPosX()-40/2+2*pxl;

        //Ligne 0
        for (int i=2; i<4; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y, couleur_drone, couleur_drone, pxl));
        for (int i=6; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y, couleur_drone, couleur_drone, pxl));

        //Ligne 1
        gui.addGraphicalElement(new Rectangle(X, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+9*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 2
        gui.addGraphicalElement(new Rectangle(X, Y-2*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=2; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+9*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 3
        for (int i=0; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+3*pxl, Y-3*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=4; i<6; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y-3*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=7; i<10; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 4
        for (int i=1; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 5
        for (int i=2; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 6
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 7
        gui.addGraphicalElement(new Rectangle(X+1*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));

    }

}
