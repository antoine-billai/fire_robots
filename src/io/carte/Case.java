package io.carte;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

import io.incendie.*;

public class Case{

    public enum NatureTerrain{EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT}

    private int x;                  // La Case est située dans la colonne x
    private int y;                  // La Case est située dans la ligne y
    private NatureTerrain nature;   // Nature de la case

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public Case(int y,int x, NatureTerrain nature){
        this.x = x;
        this.y = y;
        this.nature = nature;
    }

    /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
    public int getLigne(){
        return y;
    }

    public int getPosX(){
        return this.x*40+40;
    }

    public int getPosY(){
        return this.y*40+40;
    }

    public int getColonne(){
        return x;
    }

    public NatureTerrain getNature(){
        return nature;
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
    /** 
     * Affiche la Case sous la forme d'une chaine de caractères
     **/
    public void printCase(){
        System.out.println("[("+this.y+";"+this.x+"); "+this.nature+"]");
    }

    /** 
     * Retourne la Case sous la forme d'une chaine de caractères
     **/
    public String printCaseString(){
        return "[("+this.y+";"+this.x+"); "+this.nature+"]";
    }

    /** 
     * Compare deux Cases. Si elles sont identiques retourne true. false sinon.
     * @param b Case avec laquelle on compare la Case actuelle
     **/
    public boolean equal(Case b){
        if (this.x == b.x && this.y ==b.y && this.nature==b.nature){
            return true;
        }
        else return false;
    }

    public boolean isInFire(Incendie[] feu){
        for (int i=0; i<feu.length; i++){
            if (this.equals(feu[i].getPosition())){
                return true;
            }
        }
        return false;
    }

    /** 
     * Dessine la Case en fonction de sa Nature
     * @param gui L'interface graphique
     **/
    public void CaseDraw(GUISimulator gui){
        switch(nature){
            case EAU:
                gui.addGraphicalElement(new Rectangle(this.getPosX(), this.getPosY(), Color.BLACK, Color.BLUE, 40));
                break;
            case FORET:
                gui.addGraphicalElement(new Rectangle(this.getPosX(), this.getPosY(), Color.BLACK, Color.GREEN, 40));
                break;
            case ROCHE:
                gui.addGraphicalElement(new Rectangle(this.getPosX(), this.getPosY(), Color.BLACK, Color.GRAY, 40));
                break;
            case TERRAIN_LIBRE:
                gui.addGraphicalElement(new Rectangle(this.getPosX(), this.getPosY(), Color.BLACK, Color.decode("#8A550F"), 40));
                break;
            case HABITAT:
                gui.addGraphicalElement(new Rectangle(this.getPosX(), this.getPosY(), Color.BLACK, Color.YELLOW, 40));
                break;
            default:
                gui.addGraphicalElement(new Rectangle(this.getPosX(), this.getPosY(), Color.BLACK, Color.BLACK, 42));
                break;
        }
    }
}
