package io.robot;
import io.carte.*;
import java.util.LinkedList;
import java.util.Collections;
import io.IllegalActionException;

public class Chemin {
  private Carte carte;          //Carte sur laquelle se déroule le scénario
  private Robot robot;          //Robot cherchant à se déplacer
  private Case[][] chemin;      //Tableau de Cases 
  private double[][] sommet;    //Contient la durée pour atteindre chacune des cases en partant du point de départ de l'ACM
  private int[][] atteint;      //Tableau de 0 ou 1 permettant de savoir si la case à été testée
  private int traite;           //Vaut 1 si on a calculé le plus court chemin. 0 sinon.
  private LinkedList<Case> Pcc; //Liste chainée contanant chacune des cases du plus court chemin

  /* ---------------------------------------------------------
                            CONSTRUCTEUR
    --------------------------------------------------------- */
  public Chemin(Carte carte, Robot robot){
    this.carte = carte;
    this.robot = robot;
    this.chemin = new Case[carte.getNbLigne()][carte.getNbColonne()];
    this.sommet = new double[carte.getNbLigne()][carte.getNbColonne()];
    this.atteint = new int[carte.getNbLigne()][carte.getNbColonne()];
    this.Pcc = new LinkedList<Case>();
    this.traite = 0;
  }
  
  /* ---------------------------------------------------------
                    Methodes Obtention Variables
    --------------------------------------------------------- */
  public double getTemps(Case arrive){
    if (traite == 1) {
      return sommet[arrive.getLigne()][arrive.getColonne()];
    }
    return -1;
  }

  public LinkedList<Case> getPcc(){
    return Pcc;
  }

  public Carte getCarteChemin(){
    return carte;
  }

  public Case getCaseChemin(int x,int y){
    return this.chemin[y][x];
  }

  /* ---------------------------------------------------------
                        Autres Methodes
    --------------------------------------------------------- */
  /**
   * Calcule le temps que met le robot pour se déplacer de sa position à une case
   * @param position Position du robot
   * @param suivante Destination du robot
   * @param carte Carte sur laquelle le scénario se déroule
   */
  public double temps(Case position, Case suivante, Carte carte){
       if (robot.getVitesse(suivante.getNature()) == -1){ //Si le robot ne peut pas aller sur la Case de destination, 
         return Double.POSITIVE_INFINITY;                 //La durée entre les deux case devient infinié
       }
       double temps = carte.getTailleCase()/((robot.getVitesse(position.getNature())+robot.getVitesse(suivante.getNature()))/2);
       return temps;
  }

  /**
   * Initialise la case de départ de l'ACM
   * @param depart Case de départ de l'ACM
   */
  private void init(Case depart){ 
    for (int i = 0; i < carte.getNbColonne(); i++){
      for (int j = 0; j < carte.getNbLigne(); j++){
        sommet[j][i] = Double.POSITIVE_INFINITY;
        atteint[j][i] = 0;
      }
    }  //System.out.println(depart.getColonne()+ "x"+depart.getLigne());
    sommet[depart.getLigne()][depart.getColonne()] = 0;
    chemin[depart.getLigne()][depart.getColonne()] = depart;
  }

  /**
   * Trouve la case la plus proche temporellement du point de départ 
   * @return Case la plus proche du point de départ
   */
  private Case min_distance(){
    int x = 0 ; 
    int y = 0; 
    double min = Double.POSITIVE_INFINITY;
    for (int i = 0; i < carte.getNbColonne(); i++){
      for (int j = 0; j < carte.getNbLigne(); j++){
        if (atteint[j][i] == 0){
          if(sommet[j][i] < min){
            min = sommet[j][i];
                y = j;
                x = i;
          }
        }
      }
    }
    return carte.getCase(y,x);
  }

  /**
   * Regarde si en passsant par a c'est plus rapide qu'un autre façon d'aller à b
   * @param a Case intermédiaire
   * @param b Case d'Arrivée
   */
  private void maj_distance(Case a, Case b){
    if (sommet[a.getLigne()][a.getColonne()] + temps(a,b,carte) < sommet[b.getLigne()][b.getColonne()]){
      sommet[b.getLigne()][b.getColonne()] = sommet[a.getLigne()][a.getColonne()] + temps(a,b,carte);
      chemin[b.getLigne()][b.getColonne()]= a;
    }
  }

  /**
   * Algorithme de Dijkra qui détermine le plus court chemin entre les Cases de départ et destination
   * @param depart Case de départ
   * @param arrive Case destination
   */
  public LinkedList<Case> plusCourtChemin(Case depart, Case arrive){
    //1 si la case a été atteint 0 sinon
    int compteur = 0; 
    boolean fini = false; 
    Case c;
    LinkedList<Case> plusCourtChemin = new LinkedList<Case>();

    init(depart);
    while (sommet[min_distance().getLigne()][min_distance().getColonne()] < Double.POSITIVE_INFINITY 
            && fini == false && compteur <= carte.getNbColonne()*carte.getNbLigne()+10){
      
      c = min_distance();
      atteint[c.getLigne()][c.getColonne()] = 1;
      for (Carte.Direction d : Carte.Direction.values()){
        maj_distance(c,carte.getVoisin(c,d));
      }

      compteur++;

      if (arrive.equal(c)==true) {
        fini = true;
      }
    }
    if (sommet[min_distance().getLigne()][min_distance().getColonne()] < Double.POSITIVE_INFINITY 
            && compteur <= carte.getNbColonne()*carte.getNbLigne()){

    plusCourtChemin.add(arrive);
    int x = arrive.getColonne(); 
    int y = arrive.getLigne(); 
    int a, b;

    while (depart.equal(chemin[y][x])==false){
      plusCourtChemin.add(chemin[y][x]);
      a = chemin[y][x].getColonne(); b = chemin[y][x].getLigne();
      x = a; y = b;
    }

    plusCourtChemin.add(depart);
    Collections.reverse(plusCourtChemin);}
    traite = 1;
    this.Pcc = plusCourtChemin;
    return plusCourtChemin;
  }

  /**
   * Affiche une liste de Case
   * @param list
   * @throws IllegalActionException
   */
  public void printChemin(LinkedList<Case> list) throws IllegalActionException{ int i;
    try {
      if (list.size()==0)
        throw new IllegalActionException("Chemin.printPlusCourtChemin");

      for (i = 0; i < list.size();i ++){
        list.get(i).printCase();
      }
    } catch(IllegalArgumentException e){
      return;
    }
  }

  /**
   * Affiche si les Cases ont été explorées (1 si oui et 0 sinon)
   */
  public void printAtteint(){
    for (int j=0;j < carte.getNbLigne();j++){
      String a = " ";
      for (int i=0; i < carte.getNbColonne();i++){
        a = a + atteint[j][i] + " ";
      }
      System.out.println(a);
    }
  }

  /**
   * Affiche la liste contenant le chemin le plus court
   * @param list Chemin le plus court sous la forme d'une liste de Cases
   * @throws IllegalActionException
   */
  public void printPlusCourtChemin(LinkedList<Case> list) throws IllegalActionException{
    try{
      if (list.size()==0)
        throw new IllegalActionException("Chemin.printPlusCourtChemin");

      for (int j=0;j < carte.getNbLigne();j++){
        String a = " ";
        for (int i=0; i < carte.getNbColonne();i++){
          if (list.contains(carte.getCase(j,i))){
          a = a + 1 + " ";}
          else { a = a + 0 +" ";}
        }
        System.out.println(a);
      }
    } catch (IllegalArgumentException e){
      return;
    }
  }
}
