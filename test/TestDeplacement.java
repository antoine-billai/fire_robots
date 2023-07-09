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
import io.IllegalActionException;
import io.Simulation.*;

import gui.GUISimulator;
import gui.Rectangle;


class TestDeplacement {

    public static void main(String[] args)
        throws FileNotFoundException, DataFormatException, InterruptedException{

        //Initialisation de la console et du simulateur (FONCTIONNEL)
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        LinkedList<Evenement> listevenements = new LinkedList<Evenement>();
        Simulateur sim = new Simulateur(gui, args, listevenements, 0);
        DonneesSimulation DS=sim.getDS();

        Case destination = DS.getCarte().getCase(18,18);
        Case dest1 = DS.getIncendies()[4].getPosition();
        Case dest0 = DS.getIncendies()[5].getPosition();
        Case dest2 = DS.getIncendies()[6].getPosition();

        Robot a = DS.getRobots()[0];
        Robot b = DS.getRobots()[1];
        Robot c = DS.getRobots()[2];
        Robot d = DS.getRobots()[3];
        Robot e = DS.getRobots()[4];

        /*--------------------------------------------------------------
        *                          ROBOT A
         -------------------------------------------------------------*/
        long DA=0;
            //Deplacement Case Feu
        long DA1 = EvenementDeplacement.pushEvent(a, dest0, sim.getDateSimulation(), sim.getList(), DS,gui, a.getPosition());
        DA+=DA1;
            //Intervention Case Feu
        EvenementIntervention iA1= new EvenementIntervention(sim.getDateSimulation()+DA,a, DS.getIncendies()[5] , DS.getIncendies());
        iA1.pushEvent(sim.getDateSimulation()+DA, sim.getList());
        DA+=iA1.duree();
        /*
            //Deplacement Case Eau
        long DA2 = EvenementDeplacement.pushEvent(a, destination, sim.getDateSimulation()+DA, sim.getList(), DS,gui);
        DA+=DA2;
            //Remplissage Robot
        EvenementRemplissage rA2 = new EvenementRemplissage(sim.getDateSimulation()+DA, a,DS.getCarte());
        rA2.pushEvent(sim.getDateSimulation()+DA, sim.getList());


        /*--------------------------------------------------------------
         *                          ROBOT B
         -------------------------------------------------------------*/
    /*
        long DB=0;
            //Deplacement Case Feu
        long DB1 = EvenementDeplacement.pushEvent(b, dest1, sim.getDateSimulation(), sim.getList(), DS,gui);
        DB+=DB1;
            //Intervention Case Feu
        EvenementIntervention iB1= new EvenementIntervention(sim.getDateSimulation()+DB,b, DS.getIncendies()[4] , DS.getIncendies());
        iB1.pushEvent(sim.getDateSimulation()+DB, sim.getList());
        DB+=iB1.duree();
            //Deplacement Case Eau
        long DB2 = EvenementDeplacement.pushEvent(b, destination, sim.getDateSimulation()+DB, sim.getList(), DS,gui);
        DB+=DB2;
            //Remplissage Robot
        EvenementRemplissage rB2 = new EvenementRemplissage(sim.getDateSimulation()+DB, b,DS.getCarte());
        rB2.pushEvent(sim.getDateSimulation()+DB, sim.getList());
    */

        /*--------------------------------------------------------------
         *                          ROBOT C
         -------------------------------------------------------------*/
    /*
        long DC=0;
            //Deplacement Case Feu
        long DC1 = EvenementDeplacement.pushEvent(c, dest2, sim.getDateSimulation(), sim.getList(), DS,gui);
        DA+=DC1;
            //Intervention Case Feu
        EvenementIntervention iC1= new EvenementIntervention(sim.getDateSimulation()+DC,c, DS.getIncendies()[6] , DS.getIncendies());
        iC1.pushEvent(sim.getDateSimulation()+DC, sim.getList());
        DC+=iC1.duree();
            //Deplacement Case Eau
        long DC2 = EvenementDeplacement.pushEvent(c, destination, sim.getDateSimulation()+DC, sim.getList(), DS,gui);
        DC+=DC2;
        //     //Remplissage Robot
        EvenementRemplissage rC2 = new EvenementRemplissage(sim.getDateSimulation()+DC, c,DS.getCarte());
        rC2.pushEvent(sim.getDateSimulation()+DC, sim.getList());

        */
    }
}
