import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import javax.print.event.PrintEvent;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import gui.Text;

import io.robot.*;
import io.carte.*;
import io.evenement.*;
import io.incendie.*;
import io.Simulation.*;
import io.robotChef.*;


class TestFinal {

    public static void main(String[] args)
        throws FileNotFoundException, DataFormatException, InterruptedException{

        //Initialisation de la console et du simulateur (FONCTIONNEL)
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        LinkedList<Evenement> listevenements = new LinkedList<Evenement>();
        Simulateur sim = new Simulateur(gui, args, listevenements, 0);
        DonneesSimulation DS=sim.getDS();
        RobotChef robotChef = new RobotChef(sim);

        for (int i=listevenements.size()-1; i>=0; i--){
            listevenements.get(i).printEvenement();
        }

      }
    }
