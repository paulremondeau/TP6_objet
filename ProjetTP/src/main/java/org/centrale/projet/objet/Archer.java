/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe des archers, sous-classe des personnages.
 * @author bodet
 */
public class Archer extends Personnage implements Combattant{
    // Hérite de la classe Personnage
    
    private int nbFleches;
    /**
     * Construit un archer en précisant ses attributs
     * @param nom Nom de l'archer
     * @param pV Point de vie
     * @param pA Pourcentage d'attaque
     * @param pP Pourcentage de parade
     * @param rM Pourcentage de résistance magique
     * @param dA Dégats d'attaque
     * @param distMax Distance maximale d'attaque 
     * @param pos Positon de l'archer
     * @param nbF Nombre de flèches de l'archer
     * @param ptPara Point de parade.
     * @param vivant Dit si l'archer est vivant.
     */
    public Archer(String nom, int pV, int pA, int pP,  int rM, int dA, int distMax, Point2D pos, int nbF, int ptPara,boolean vivant) {
        super(nom, pV, 0, pA, pP, 0, rM, dA, 0, distMax, pos, ptPara,vivant);   // On sous-entend que l'archer a 0 points de mana
        this.nbFleches = nbF;
    }
    
    /**
     * Constructeur aléatoire utilisé dans la classe Joueur
     * @param nom Nom de l'archer
     * @param pos Position de l'archer
     * @param vivant Indique si l'archer est vivant 
     */
    public Archer(String nom,Point2D pos, boolean vivant){
        super(nom,pos);
    
        this.nbFleches = ThreadLocalRandom.current().nextInt(20,30);
        this.setPtVie(ThreadLocalRandom.current().nextInt(40,50));
        this.setPourcentageAtt(ThreadLocalRandom.current().nextInt(30,40));
        this.setPourcentagePar(ThreadLocalRandom.current().nextInt(20,30));
        this.setPourcentageMag(ThreadLocalRandom.current().nextInt(10,20));
        this.setPourcentageResistMag(ThreadLocalRandom.current().nextInt(30,35));
        this.setDegAtt(ThreadLocalRandom.current().nextInt(15,25));
        this.setDegMag(ThreadLocalRandom.current().nextInt(1,5));
        this.setDistAttMax(ThreadLocalRandom.current().nextInt(20,30));
        this.setPtPar(ThreadLocalRandom.current().nextInt(20,30));    
    }
    
    /**
     * Constructeur de recopie.
     * @param a L'archer que l'on copie
     */
    public Archer(Archer a) {
        super(a);
        this.nbFleches= a.nbFleches;
    }
    /**
     * Constructeur par défaut.
     */
    public Archer() {
        super();
        this.nbFleches=0;
    }

    public int getNbFleches() {
        return nbFleches;
    }

    public void setNbFleches(int value) {
        this.nbFleches = value;
    }
    
    @Override
    public void affiche(){
        super.affiche();
        System.out.println(this.getNom() + " a " + this.nbFleches + " flèches.");
    }
    
    /**
     * Méthode combattre de l'archer
     *
     * @param c Cible de l'attaque
     */
    @Override
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) > 1 && this.getPos().distance(c.getPos()) < this.getDistAttMax() && this.getNbFleches() >= 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jetArcher = generateurAleatoire.nextInt(100);
            this.setNbFleches(this.getNbFleches() - 1); // On retire un point de mana
            if (jetArcher <= this.getPourcentageAtt()) {
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                }
            else {
                System.out.println("L'attaque a échoué...");
            }
        } else {
            System.out.println("La cible n'est pas à portée !");
        }
    }
    
}
