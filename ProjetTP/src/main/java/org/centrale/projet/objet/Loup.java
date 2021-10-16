/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * La classe des louups
 * @author remondeau
 */
public class Loup extends Monstre implements Combattant{

    /**
     * Constructeur ayant tous les attributs en paramètres.
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param dA Dégats d'attaque.
     * @param pos Position du monstre.
     * @param ptPara Point de parade.
     * @param vivant Dit si le Loup est vivant.
     */
    public Loup(int pV, int pA, int pP, int dA, Point2D pos, int ptPara,boolean vivant) {
        super(pV, pA, pP, dA, pos, ptPara,vivant);
    }

    /**
     * Constructeur de recopie.
     * @param l Loup à recopier.
     */
    public Loup(Loup l) {
        super(l);
    }

    /**
     * Constructeur par défaut.
     */
    public Loup() {
    }
    
/**
     * Méthode combatre du loup
     *
     * @param c Cible de l'attaque
     */
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) == 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jetLoup = generateurAleatoire.nextInt(100);
            if (jetLoup <= this.getPourcentageAtt()) {
                
                int jetCreature = generateurAleatoire.nextInt(100);
                if (jetCreature<=c.getPtPar()){ // Si la créature pare le coup
                    System.out.println("La cible a paré le coup !");
                    int degat;
                    degat = Math.max(0,this.getDegAtt()-c.getPtPar());
                    c.setPtVie(c.getPtVie()-degat);
                }
                else{
                    System.out.println("La cible a pris un coup direct !");
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                }
                
            } else {
                System.out.println("L'attaque a échoué...");
            }
        } else {
            System.out.println("La cible est trop loin !");
        }
    }
}
