package io.Simulation;

import io.MyString;
import io.carte.*;
import io.incendie.*;
import io.robot.*;


import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;



/**
 * Lecteur de cartes au format spectifié dans le sujet.
 * Les données sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichées.
 * A noter: pas de vérification sémantique sur les valeurs numériques lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher.
 * A vous de modifier ou d'ajouter des méthodes, inspirées de celles présentes
 * (ou non), qui CREENT les objets au moment adéquat pour construire une
 * instance de la classe DonneesSimulation à partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une méthode qui crée et retourne un objet
 * contenant toutes les données lues:
 *    public static DonneesSimulation creeDonnees(String fichierDonnees);
 * Et faire des méthode creeCase(), creeRobot(), ... qui lisent les données,
 * créent les objets adéquats et les ajoutent ds l'instance de
 * DonneesSimulation.
 */
public class LecteurDonnees {


    /**
     * Lit et affiche le contenu d'un fichier de donnees (cases,
     * robots et incendies).
     * Ceci est méthode de classe; utilisation:
     * LecteurDonnees.lire(fichierDonnees)
     * @param fichierDonnees nom du fichier à lire
     */
    // public static void lire(String fichierDonnees)
    //     throws FileNotFoundException, DataFormatException {
    //     System.out.println("\n == Lecture du fichier" + fichierDonnees);
    //     LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
    //     lecteur.lireCarte();
    //     lecteur.lireIncendies();
    //     lecteur.lireRobots();
    //     scanner.close();
    //     System.out.println("\n == Lecture terminee");
    // }

    public static DonneesSimulation lire(String[] args, int carte_number)
        throws FileNotFoundException, DataFormatException {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }

        try {
            LecteurDonnees lecteur = new LecteurDonnees(args[carte_number]);

            // Lecture de la carte
            Carte map = lecteur.lireCarte();
            // map.printCarte();

            // Lecture des incendies
            Incendie feu[] = lecteur.lireIncendies(map);

            // Lecture des robots
            Robot rob[] = lecteur.lireRobots(map);

            scanner.close();

            // for (int i=0; i<feu.length; i++){
            //     feu[i].printIncendie();
            // }

            // for (int i=0; i<rob.length; i++){
            //     // rob[i].printRobot();
            // }

            DonneesSimulation DS=new DonneesSimulation(map, feu, rob);
            return DS;

        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }

        return null;
    }

    // Tout le reste de la classe est prive!

    private static Scanner scanner;

    /**
     * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
     * @param fichierDonnees nom du fichier a lire
     */
    private LecteurDonnees(String fichierDonnees)
        throws FileNotFoundException {
        scanner = new Scanner(new File(fichierDonnees));
        scanner.useLocale(Locale.US);
    }

    /**
     * Lit et affiche les donnees de la carte.
     * @throws ExceptionFormatDonnees
     */
    private Carte lireCarte() throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbLignes = scanner.nextInt();
            int nbColonnes = scanner.nextInt();
            int tailleCases = scanner.nextInt();	// en m
            Case[][] carte = new Case[nbLignes][nbColonnes];

            // System.out.println("Carte " + nbLignes + "x" + nbColonnes
            //         + "; taille des cases = " + tailleCases);

            for (int lig = 0; lig < nbLignes; lig++) {
                for (int col = 0; col < nbColonnes; col++) {
                    carte[lig][col]=lireCase(lig, col, carte[lig][col]);
                }
            }

            Carte map = new Carte(tailleCases*1000, nbLignes, nbColonnes, carte); //conversion de la taille de carte en metre
            // mapLocal.setTailleCase(tailleCases);
            // mapLocal.setNbColonne(nbColonnes) ;
            // mapLocal.setNbLigne(nbLignes);
            // mapLocal.setCarte(carte);
            return map;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbLignes nbColonnes tailleCases");
        }
        // une ExceptionFormat levee depuis lireCase est remontee telle quelle
    }


    /**
     * Lit et affiche les donnees d'une case.
     */
    private Case lireCase(int lig, int col, Case box) throws DataFormatException {
        ignorerCommentaires();

        try {
            MyString chaineNature = new MyString(scanner.next());

            if(chaineNature.isInNatureTerrain()){
                box = new Case(lig, col, Case.NatureTerrain.valueOf(chaineNature.str));
            }
            // si NatureTerrain est un Enum, vous pouvez recuperer la valeur
            // de l'enum a partir d'une String avec:
            //			NatureTerrain nature = NatureTerrain.valueOf(chaineNature);

            verifieLigneTerminee();
            return box;
            // System.out.print("nature = " + chaineNature);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de case invalide. "
                    + "Attendu: nature altitude [valeur_specifique]");
        }

        // System.out.println();
    }


    /**
     * Lit et affiche les donnees des incendies.
     */
    private Incendie[] lireIncendies(Carte map) throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbIncendies = scanner.nextInt();
            if (nbIncendies > map.getNbColonne()*map.getNbColonne()){
                throw new DataFormatException("nombre incendie doit être inférieur au nombre de cases");
            }

            Incendie feu[] = new Incendie[nbIncendies];
            // System.out.println("Nb d'incendies = " + nbIncendies);
            for (int i = 0; i < nbIncendies; i++) {
                feu[i]=lireIncendie(i, map);
            }

            return feu;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbIncendies");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme incendie.
     * @param i
     */
    private Incendie lireIncendie(int i, Carte map) throws DataFormatException {
        ignorerCommentaires();
        // System.out.print("Incendie " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            if (intensite <= 0) {
                throw new DataFormatException("incendie " + i
                        + "nb litres pour eteindre doit etre > 0");
            }
            Incendie feu = new Incendie(map.getCase(lig, col), intensite);
            verifieLigneTerminee();

            return feu;

            // System.out.println("position = (" + lig + "," + col
            //         + ");\t intensite = " + intensite);

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format d'incendie invalide. "
                    + "Attendu: ligne colonne intensite");
        }
    }


    /**
     * Lit et affiche les donnees des robots.
     */
    private Robot[] lireRobots(Carte map) throws DataFormatException {
        ignorerCommentaires();
        try {
            int nbRobots = scanner.nextInt();
            // System.out.println("Nb de robots = " + nbRobots);
            Robot rob[]=new Robot[nbRobots];
            for (int i = 0; i < nbRobots; i++) {
                rob[i] = lireRobot(i, map);
            }

            return rob;

        } catch (NoSuchElementException e) {
            throw new DataFormatException("Format invalide. "
                    + "Attendu: nbRobots");
        }
    }


    /**
     * Lit et affiche les donnees du i-eme robot.
     * @param i
     */
    private Robot lireRobot(int i, Carte map) throws DataFormatException {
        ignorerCommentaires();
        // System.out.print("Robot " + i + ": ");

        try {
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            // System.out.print("position = (" + lig + "," + col + ");");
            String type = scanner.next();

            // System.out.print("\t type = " + type);


            // lecture eventuelle d'une vitesse du robot (entier)
            // System.out.print("; \t vitesse = ");
            String s = scanner.findInLine("(\\d+(\\.\\d+)?)");	// 1 or more digit(s) ?
            // pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

            // if (s == null) {
            //     System.out.print("valeur par defaut");
            // } else {
            //     int vitesse = Integer.parseInt(s);
            //     System.out.print(vitesse);
            // }

            double vitesse=0;
            if (s!=null){
                vitesse = Double.parseDouble(s)/3.6; //conversion en m/s 
            }

            switch(type.toUpperCase()){
                case "DRONE":
                    Drone drone0 = new Drone(map.getCase(lig, col));
                    if (vitesse!=0) drone0.setVitesse(vitesse);
                    return drone0;
                case "ROUES":
                    RobotRoues roues0 = new RobotRoues(map.getCase(lig, col));
                    if (vitesse!=0) roues0.setVitesse(vitesse);
                    return roues0;
                case "PATTES":
                    RobotPattes pattes0 = new RobotPattes(map.getCase(lig, col));
                    if (vitesse!=0) pattes0.setVitesse(vitesse);
                    return pattes0;
                case "CHENILLES":
                    RobotChenille chenille0 = new RobotChenille(map.getCase(lig, col));
                    if (vitesse!=0) chenille0.setVitesse(vitesse);
                    return chenille0;
            }

            verifieLigneTerminee();
            return null;

            // System.out.println();

        } catch (NoSuchElementException e) {
            throw new DataFormatException("format de robot invalide. "
                    + "Attendu: ligne colonne type [valeur_specifique]");
        }

    }


    /** Ignore toute (fin de) ligne commencant par '#' */
    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }
}
