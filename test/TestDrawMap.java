import java.util.LinkedList;
import java.util.zip.DataFormatException;

import io.Simulation.DonneesSimulation;
import io.Simulation.Simulateur;
import io.evenement.Evenement;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import java.awt.Color;
import java.io.FileNotFoundException;

import io.robot.*;
import io.carte.*;
import io.evenement.*;
import io.incendie.*;
import io.Simulation.*;
import io.robotChef.*;

class TestDrawMap {
    public static void main(String[] args)
        throws FileNotFoundException, DataFormatException, InterruptedException {
        //Initialisation de la console et du simulateur (FONCTIONNEL)
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        LinkedList<Evenement> listevenements = new LinkedList<Evenement>();
        Simulateur sim = new Simulateur(gui, args, listevenements, 0);
    }
}