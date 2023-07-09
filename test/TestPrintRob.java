import io.robot.Robot;

import io.carte.*;
import io.robot.*;

import java.awt.Color;

import gui.GUISimulator;
import gui.Rectangle;

class TestPrintRob {
    public static void main(String[] args){
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        Case box0= new Case(0, 0, Case.NatureTerrain.FORET);
        Robot rob0 = new Drone(box0);
        rob0.setVolumeEau(0);
        Case box1= new Case(2, 0, Case.NatureTerrain.FORET);
        Robot rob1 = new Drone(box1);
        rob1.setVolumeEau(30);
        Case box2= new Case(4, 0, Case.NatureTerrain.FORET);
        Robot rob2 = new Drone(box2);
        rob2.setVolumeEau(100);

        rob0.Draw(gui);
        rob1.Draw(gui);
        rob2.Draw(gui);
    }
}
