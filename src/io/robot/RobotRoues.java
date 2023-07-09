package io.robot;

import io.carte.*;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

public class RobotRoues extends Robot{
/* ---------------------------------------------------------
                        CONSTRUCTEUR
--------------------------------------------------------- */
    public RobotRoues(Case position){
        super(position,5000,5000,80/3.6,10*60,100.0/5);
    }

/* ---------------------------------------------------------
                    Autres Methodes
--------------------------------------------------------- */
    /**
     * Retourne la vitesse du Robot en fonction de la Nature de la Case
     * @param nature Nature de la case sur laquelle se trouve le Robot
     */
    @Override
    public double getVitesse(Case.NatureTerrain nature){
        if (nature == Case.NatureTerrain.TERRAIN_LIBRE
                || nature == Case.NatureTerrain.HABITAT){// Vitesse normale sur TERRAIN_LIBRE et HABITAT
          return super.getVitesse(nature);
        }else //Ne peut circuler sur EAU, ROCHE et FORET
          return -1;
    }

    /**
     * Déplace le Robot d'une case dans la direction donnée
     * @param carte La carte sur laquelle le Robot est placé
     * @param d La direction vers laquelle le Robot souhaite se déplacer
     * @return une durée de déplacement si le Robot peut se déplacer sur la Case. -1 sinon.
     */
    @Override
    public double deplacement(Carte carte, Carte.Direction d){
        Case c = (carte.getVoisin(this.getPosition(),d)); //getVoisin protégée par une assert
        if (c.getNature() == Case.NatureTerrain.EAU || c.getNature() == Case.NatureTerrain.ROCHE
                || c.getNature() == Case.NatureTerrain.FORET){ //Ne peut circuler sur EAU, ROCHE et FORET
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
        int limite=9*super.getpourcentage()/100;//Niveau de remplissage
        Color couleur_drone=Color.CYAN;         //Couleur de la zone remplie
        int pxl = 40/12;                        //Taille d'un pixel

        int Y = this.getPosition().getPosY()+40/2-2*pxl;
        int X = this.getPosition().getPosX()-40/2+2*pxl;
        int ligne=0;

        //Ligne 0
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y, couleur_drone, couleur_drone, pxl));

        //Ligne 1
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+1*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+3*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+5*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+7*pxl, Y-pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 2
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y-2*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 3
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y-3*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 4
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=2; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-4*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 5
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=0; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-5*pxl, couleur_drone, couleur_drone, pxl));


        //Ligne 6
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        for (int i=0; i<3; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+3*pxl, Y-6*pxl, Color.WHITE, Color.WHITE, pxl));
        gui.addGraphicalElement(new Rectangle(X+4*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+5*pxl, Y-6*pxl, Color.WHITE, Color.WHITE, pxl));
        for (int i=6; i<9; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-6*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 7
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-7*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=2; i<7; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-7*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 8
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-8*pxl, couleur_drone, couleur_drone, pxl));
        for (int i=3; i<6; i++) gui.addGraphicalElement(new Rectangle(X+i*pxl, Y-8*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-8*pxl, couleur_drone, couleur_drone, pxl));

        //Ligne 9
        ligne ++;
        if (couleur_drone!=Color.PINK)
        if (ligne>limite)couleur_drone = Color.PINK;
        gui.addGraphicalElement(new Rectangle(X, Y-9*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+2*pxl, Y-9*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+6*pxl, Y-9*pxl, couleur_drone, couleur_drone, pxl));
        gui.addGraphicalElement(new Rectangle(X+8*pxl, Y-9*pxl, couleur_drone, couleur_drone, pxl));
    }
}
