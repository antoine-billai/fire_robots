package io.carte;

import gui.GUISimulator;
import gui.Rectangle;


public class Carte {

    public enum Direction{NORD, SUD, EST, OUEST}

    private int taille_cases;   //Taille de la case en m
    private int nb_ligne;       //Nombre de lignes
    private int nb_colonne;     //Nombre de colonnes
    private Case[][] carte;     //Tableau de cases Case[colonne][ligne]

    /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
    public Carte(int tailleCases, int NbLigne, int NbColonne, Case[][] carte){
        this.taille_cases=tailleCases;
        this.nb_ligne=NbLigne;
        this.nb_colonne=NbColonne;
        this.carte=carte;
    }

    /* ---------------------------------------------------------
                    Methodes Définition Variables
    --------------------------------------------------------- */
    public void setNbLigne(int NbLignes){
        this.nb_ligne=NbLignes;
    }

    public void setNbColonne(int NbColonnes){
        this.nb_colonne=NbColonnes;
    }

    public void setTailleCase(int TailleCase){
        this.taille_cases=TailleCase;
    }

    public void setCarte(Case[][] map){
        this.carte=map;
    }

    /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
    public int getNbLigne(){
        return this.nb_ligne;
    }

    public int getNbColonne(){
        return this.nb_colonne;
    }

    public Case getCase(int ligne,int colonne){
        return this.carte[ligne][colonne];
    }

    public int getTailleCase(){
        return this.taille_cases;
    }

    /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */

    /**
     * Retourne la Case du voisin de la Case c dans la Direction d
     * @param c La Case que dont vous souhaitez récupérer le voisin
     * @param d La Direction dans laquelle le voisin est situé
     */
    public Case getVoisin(Case c, Direction d){
        int x = c.getColonne();
        int y = c.getLigne();

        if(voisinExiste(c, d)){

            switch(d){
                case NORD:
                    y++; break;
                case SUD:
                    y --; break;
                case EST:
                    x ++; break;
                case OUEST:
                    x --;break;
                default:
                    break;
            }
        }

        return this.getCase(y,x);
    }

    /**
     * Vérifie que la Case c possède un voisin dans la Direction d.
     * false si la Case c ne possède pas de voisin dans la Direction d. true sinon.
     * @param c La Case que dont vous souhaitez récupérer le voisin
     * @param d La Direction dans laquelle le voisin est situé
     */
    public boolean voisinExiste(Case c, Direction d){
        int x = c.getColonne();
        int y = c.getLigne();

        switch(d){
            case NORD:
                y++; break;
            case SUD:
                y --; break;
            case EST:
                x ++; break;
            case OUEST:
                x --; break;
        }

        if (x > nb_colonne-1 || x < 0) return false;
        else if (y > nb_ligne-1 || y < 0) return false;
        else return true;
    }

    /**
     * Dessine la Carte
     * @param gui L'interface graphique
     */
    public void CarteDraw(GUISimulator gui){
        for (int i=0; i<nb_ligne; i++)
            for (int j=0; j<nb_colonne; j++){
                this.carte[i][j].CaseDraw(gui); //On dessine chacune des cases
            }
    }

    /**
     * Affiche la Carte sous la forme d'une chaine de caractères
     */
    public void printCarte(){
        System.out.println("Taille Case: "+this.taille_cases+
            "\nTaille: ("+this.nb_colonne+", "+this.nb_ligne+")\n");
        for (int i=0; i<nb_colonne; i++){
            for (int j=0; j<nb_ligne; j++){
                this.carte[j][i].printCase();

            }
        }
    }

}
