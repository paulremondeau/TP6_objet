/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * La classe des mages.
 * @author remondeau
 */
public class Mage extends Personnage implements Combattant{

    /**
     * Constructeur qui prend les attributs en paramètres.
     * @param nom Nom du mage
     * @param pV Points de vie.
     * @param ptM Points de magie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param pM Pourcentage de magie.
     * @param rM Résistance magique.
     * @param dA Dégat d'attaque.
     * @param dM Dégat magique.
     * @param distMax Distance d'attaque maximale.
     * @param pos Position du mage.
     * @param ptPara Point de parade.
     * @param vivant Dit si le mage est vivant.
     */

    public Mage(String nom, int pV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, Point2D pos, int ptPara,boolean vivant) {
        super(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, pos, ptPara,vivant);
    }

    
    /**
     * Constructeur de recopie
     * @param m Mage à recopier.
     */
    public Mage(Mage m) {
        super(m);
    }
    
    /**
     * Constructeur aléatoire utilisé dans la classe Joueur
     * @param nom Nom du mage
     * @param pos Position du mage
     * @param vivant Indique si le mage est vivant
     */
    public Mage(String nom,Point2D pos, boolean vivant){
        super(nom,pos);
    
        this.setPtVie(ThreadLocalRandom.current().nextInt(25,30));
        this.setPtMana(ThreadLocalRandom.current().nextInt(70,80));
        this.setPourcentagePar(ThreadLocalRandom.current().nextInt(10,15));
        this.setPourcentageMag(ThreadLocalRandom.current().nextInt(80,90));
        this.setPourcentageResistMag(ThreadLocalRandom.current().nextInt(70,80));
        this.setDegMag(ThreadLocalRandom.current().nextInt(60,70));
        this.setDistAttMax(ThreadLocalRandom.current().nextInt(10,15));
        this.setPtPar(ThreadLocalRandom.current().nextInt(20,30));    
    }
    
    /**
     * Constructeur par défaut.
     */
    public Mage() {
    }

    /**
     * Méthode combattre du mage.
     * Le mage ne peut pas attaquer au delà de sa distance d'attaque. 
     * Il a également besoin d'au moins un point de mana.
     * @param c Cible de l'attaque
     */
    @Override
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) >= 1 && this.getPos().distance(c.getPos()) <= this.getDistAttMax() && this.getPtMana() >= 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jetMage = generateurAleatoire.nextInt(100);
            this.setPtMana(this.getPtMana() - 1); // On retire un point de mana
            if (jetMage <= this.getPourcentageMag()) {
                    c.setPtVie(c.getPtVie() - this.getDegMag());
                    System.out.println("L'attaque a réussi ! " + c.getClass().getSimpleName() + " a subi " + this.getDegAtt() + " points de dégats.");
                }
            else {
                System.out.println("L'attaque a échoué...");
            }
        } else {
            if (this.getPtMana() < 1) {
                System.out.println("Pas assez de mana !");
            } else {
                System.out.println("La cible n'est pas à portée !");
            }
        }
        
        if (c.getPtVie()<0){
            c.setVivant(false);
        }    
    }
}
