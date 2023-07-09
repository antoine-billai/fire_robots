package io.robot;

import io.carte.*;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class RobotChenille extends Robot{

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public RobotChenille(Case position){
        super(position,2000,2000,60/3.6,5*60,100.0/8);
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

        if (nature == Case.NatureTerrain.FORET){ //Vitesse divisée par deux sur FORET
            return super.getVitesse(nature)/2;
        } else if (nature == Case.NatureTerrain.EAU || nature == Case.NatureTerrain.ROCHE){ //Ne peut circuler sur EAU ou ROCHE
            return -1;
        }
        else //Vitesse normale sur HABITAT et TERRAIN_LIBRE
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
    public double deplacement(Carte carte, Carte.Direction d){
        Case c = (carte.getVoisin(this.getPosition(),d)); //getVoisin protégée par une assert
        if (c.getNature() == Case.NatureTerrain.EAU || c.getNature() == Case.NatureTerrain.ROCHE){ //Ne peut circuler sur EAU et ROCHE
            return -1;
        }
        return super.deplacement(carte,d);
    }

    /**
     * Dessine le RobotChenille
     * @param gui Interface graphique
     */
    @Override
    public void Draw(GUISimulator gui){
        int limite=7*super.getpourcentage()/100;//Niveau de remplissage
        Color couleur_drone=Color.CYAN;         //Couleur de la zone remplie
        int pxl = 40/12;                        //Taille d'un pixel

        int Y = this.getPosition().getPosY()+40/2-3*pxl;
        int X = this.getPosition().getPosX()-40/2+pxl+2;
        int ligne=0;

        //Ligne 0
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=0; i<11; i=i+2) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y, couleur_drone, couleur_drone, pxl));

        //Ligne 1
        ligne++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+3*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+9*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 2
        ligne ++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 3
        ligne ++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=1; i<10; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 4
        ligne ++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=1; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+3*pxl, Y-4*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=4; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-4*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=8; i<10; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 5
        ligne ++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-5*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=2; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+10*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 5
        ligne ++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-6*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=3; i<8; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+10*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 6
        ligne ++;
        if (couleur_drone!=Color.PINK)
            if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=4; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
    }
}
