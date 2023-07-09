import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;
import io.robot.*;
import io.carte.*;
import io.IllegalActionException;
import io.Simulation.*;
import java.util.LinkedList;

public class TestPCC {
    public static void main(String[] args)
        throws FileNotFoundException, DataFormatException, IllegalActionException{
        DonneesSimulation DS = LecteurDonnees.lire(args,0);
        Case depart = DS.getCarte().getCase(5,5);
        Case arrive = DS.getCarte().getCase(0,0);

        Robot a = new Drone(DS.getCarte().getCase(7,3));

        Chemin ca = new Chemin(DS.getCarte(),a);
        //Chemin cb = new Chemin(DS.getCarte(),b);
        
        //Informations generales
        System.out.println("taille : " + ca.getCarteChemin().getNbLigne()+ "x" +ca.getCarteChemin().getNbColonne());
        depart.printCase();
        arrive.printCase();
        System.out.println("\n");

        // Cases atteintes par l'algorithme
        System.out.println("Cases atteintes par l'algorithme : \n");
        LinkedList<Case> PCca = ca.plusCourtChemin(depart, arrive);
        ca.printAtteint();
        System.out.println("\n");

        // Plus court chemin
        if (PCca.size() > 0) {
            System.out.println("Le plus court chemin entre "+depart.printCaseString()+" et "+arrive.printCaseString()+" est :\n");
        }

        ca.printChemin(PCca);
        System.out.println("\n");

        ca.printPlusCourtChemin(PCca);
        System.out.println("\n");

        // Informations plus court chemin
        System.out.println("nombre de cases parcourues : "+PCca.size());
        System.out.println("le temps de parcours est : "+ca.getTemps(arrive));
    }
}

