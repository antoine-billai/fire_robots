package io.robotChef;

import java.util.LinkedList;
import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;
import java.util.PriorityQueue;


import io.robot.*;
import io.Simulation.*;
import io.carte.*;
import io.incendie.*;
import io.evenement.*;

public class RobotChef{

  private DonneesSimulation DS;
  private GUISimulator gui;
  private Simulateur sim;


  /* ---------------------------------------------------------
                          CONSTRUCTEUR
  --------------------------------------------------------- */

  public RobotChef(Simulateur sim){
    this.sim = sim;
    this.DS = sim.getDS();
    this.gui = sim.getGui();

  }


  /* ---------------------------------------------------------
              Fonctions pour trouver les cases Eau
  --------------------------------------------------------- */

  /**
   * Trouve les cases Eau de la carte
   * @param DS Donnees de la simulation en cours
   * @return LinkedList<Case> contenant les cases Eau de la carte
   */
  public LinkedList<Case> casesDeau(DonneesSimulation DS){

    Carte carte = DS.getCarte(); int i; int j;
    LinkedList<Case> casesDeau = new LinkedList<Case>();
    for (i=0;i<carte.getNbColonne();i++){
      for(j=0;j<carte.getNbLigne();j++){
        if (carte.getCase(j,i).getNature() == Case.NatureTerrain.EAU){
              casesDeau.add(carte.getCase(j,i));
        }
      }
    }
    
    return casesDeau;
  }

  /**
   * Trouve la case Eau la plus proche du robot
   * @param robot Robot étudié
   * @return la case Eau la plus proche du robot
   */
  public Case eauPlusProche(Robot robot){
    LinkedList<Case> casesDeau = casesDeau(DS);
    if (casesDeau.size() != 0) {
      double min_temps = Double.POSITIVE_INFINITY;
      Case min_case = casesDeau.get(0);

      // Compare les cases d'eau pour trouver celle la plus proche du robot
      for (int i = 0; i < casesDeau.size(); i ++){
        if (robot.getClass().getName() == "io.robot.Drone") {
          Chemin chemin = new Chemin(DS.getCarte(),robot);
          chemin.plusCourtChemin(robot.getPosition(), casesDeau.get(i));

          if (chemin.getTemps(casesDeau.get(i))< min_temps){
            min_temps = chemin.getTemps(casesDeau.get(i));
            min_case = casesDeau.get(i);
          }

        } else {
          for (Carte.Direction d : Carte.Direction.values()){
            if(DS.getCarte().voisinExiste(casesDeau.get(i),d)==true){
              Chemin chemin = new Chemin(DS.getCarte(),robot);
              chemin.plusCourtChemin(robot.getPosition(), DS.getCarte().getVoisin(casesDeau.get(i),d));
              if (chemin.getTemps(DS.getCarte().getVoisin(casesDeau.get(i),d))< min_temps){
                min_temps = chemin.getTemps(DS.getCarte().getVoisin(casesDeau.get(i),d));
                min_case = DS.getCarte().getVoisin(casesDeau.get(i),d);
              }
            }
          }
        }
      }
      return min_case;
    }
    return robot.getPosition();
  }


    /* ---------------------------------------------------------
                Fonctions pour choix des robots
    --------------------------------------------------------- */


  /**
   * Trouve le robot le plus proche de l'incendie
   * @param incendie Incendie étudié
   * @param listRobot Liste de robot de la simulation
   * @return L'indice du robot le plus proche de la liste des robots de la simulation
   */
  public int robotPlusProche(Incendie incendie, Robot[] listRobot){
    if (listRobot.length != 0){
      int numRobotPlusProche = -1;
      double min_temps = Double.POSITIVE_INFINITY;

      for (int i = 0; i < listRobot.length; i ++){
        if (listRobot[i].getTraite() == 0) {
          Chemin chemin = new Chemin(DS.getCarte(),listRobot[i]);
          chemin.plusCourtChemin(listRobot[i].getPosition(),incendie.getPosition());
          if (chemin.getTemps(incendie.getPosition()) < min_temps){
            min_temps = chemin.getTemps(incendie.getPosition());
            numRobotPlusProche = i;
          }
        }
      }
        return numRobotPlusProche;
    }
      return -1;
  }


  /**
  * Trouve un robot libre dans la simulation
  * @return L'indice du robot libre de la liste des robots de la simulation
  */
  public int choixRobot(){
    int numeroRobot = 0;
    for (numeroRobot = 0; numeroRobot< DS.getRobots().length; numeroRobot++){
      if (DS.getRobots()[numeroRobot].getTraite() == 0){
        return numeroRobot;
      }
    }
    return -1;
  }


  /* ---------------------------------------------------------
            Fonctions déversement et remplissage d'eau
  --------------------------------------------------------- */

  /**
   * Effectue le trajet du robot jusqu'à un incendie et déversement de son réservoir
   * @param robot Robot qui va éteindre l'incendie
   * @param incendie Incendie à éteindre
   * return la durée de l'action totale
   */
  public long RobotDeverseEau(Robot robot, Incendie incendie){
    long a = EvenementDeplacement.pushEvent(robot, incendie.getPosition(), sim.getDateSimulation(), sim.getList(), DS,gui, robot.getPosition());
    EvenementIntervention intervention = new EvenementIntervention(a, robot, incendie, DS.getIncendies());
    intervention.pushEvent(a + sim.getDateSimulation(),sim.getList());
    return a+intervention.duree();
  }


  /**
   * Effectue le trajet du robot jusqu'à un incendie et déversement de son réservoir
   * si l'incendie n'est pas étteint
   * @param robot Robot qui va éteindre l'incendie
   * @param incendie Incendie à éteindre
   * return la durée de l'action totale
   */
  public long RobotDeverseEau2(Robot robot, Incendie incendie){
    long event = EvenementDeplacement.pushEvent(robot, incendie.getPosition(), sim.getDateSimulation(), sim.getList(), DS,gui, robot.getPosition());
    if (incendie.getTraite() != -1){
      EvenementIntervention intervention = new EvenementIntervention(event, robot, incendie, DS.getIncendies());

      intervention.pushEvent(event + sim.getDateSimulation(),sim.getList());

      return event+intervention.duree();
    }
    return 0;
  }


  /**
   * Envoi le robot remplir son réservoir à la case Eau la plus proche
   * @param robot Robot qui va remplir son réservoir
   * @param casesDeau liste des cases obtenu avec LinkedList<Case> casesDeau(DonneesSimulation DS)
   * @return la durée du déplacement et du remplissage de son réservoir
   */
  public long RobotVaRemplir(Robot robot){
    Case destination = eauPlusProche(robot);
    long a = EvenementDeplacement.pushEvent(robot, destination, sim.getDateSimulation(), sim.getList(), DS,gui, robot.getPosition());
    EvenementRemplissage remplissage = new EvenementRemplissage(a,robot,DS.getCarte());
    remplissage.pushEvent(sim.getDateSimulation() + a,sim.getList());
    return a+remplissage.duree();
  }


  /* ---------------------------------------------------------
          Fonctions principale pour éteindre les incendies
  --------------------------------------------------------- */

  /**
   * Fonction principale qui organise le déplacement de tous les robots pour éteindre les incendies selon la stratégie
   * élémentaire d'envoi de robots libres vers les incendies libres sans conditions particulières et sans remplissage
   * des robots vides
   */
  public void eteindreIncendie(){
    int numeroRobot;
    for (int i = 0; i < DS.getIncendies().length; i++){
      if (DS.getIncendies()[i].getTraite() == 0){
          numeroRobot = choixRobot();
        if (choixRobot()!=-1){
          this.RobotDeverseEau(DS.getRobots()[numeroRobot], DS.getIncendies()[i]);
        }
        return ;
      }
    }
  }

  /**
   * Fonction principale qui organise le déplacement de tous les robots pour éteindre les incendies selon la stratégie
   * élémentaire d'envoi de robots libres vers les incendies libres sans conditions particulières et avec remplissage
   * des robots vides
   */
  public void eteindreIncendieRemplirEau(){
    int numeroRobot;
    for (int j = 0; j< DS.getRobots().length; j ++){
      if (DS.getRobots()[j].getTraite()== - 1){
        this.RobotVaRemplir(DS.getRobots()[j]);
      }
    }

    for (int i = 0; i< DS.getIncendies().length; i++){
      if (DS.getIncendies()[i].getTraite() == 0){
        numeroRobot = choixRobot();

        if (numeroRobot != -1){
          this.RobotDeverseEau(DS.getRobots()[numeroRobot], DS.getIncendies()[i]);
        }

        return ;
      }
    }
  }




  /**
  * Fonction principale qui organise le déplacement de tous les robots pour éteindre les incendies selon la stratégie
  * plus développée de sélection du robot le plus proche d'un incendie et avec remplissage des robots vides
  */
  public void eteindreIncendieRobotPlusProche(){

    int i; int j ; int numeroRobot;
    for (j = 0; j< DS.getRobots().length; j ++){
      if (DS.getRobots()[j].getTraite()== - 1){

        this.RobotVaRemplir(DS.getRobots()[j]);
      }
    }

    for (i = 0; i< DS.getIncendies().length; i++){
      if (DS.getIncendies()[i].getTraite() != -1){
        numeroRobot = this.robotPlusProche(DS.getIncendies()[i],DS.getRobots());

        if (numeroRobot != -1){
          this.RobotDeverseEau2(DS.getRobots()[numeroRobot], DS.getIncendies()[i]);
        }
      }
    }
  }

}
