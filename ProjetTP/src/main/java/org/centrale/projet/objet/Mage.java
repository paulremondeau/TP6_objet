/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

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
     * @param pP Pourcentage de parade.
     * @param pM Pourcentage de magie.
     * @param rM Résistance magique.
     * @param dM Dégat magique.
     * @param distMax Distance d'attaque maximale.
     * @param pos Position du mage.
     * @param ptPara Point de parade.
     * @param vivant Dit si le mage est vivant.
     */

    public Mage(String nom, int pV, int ptM, int pP, int pM, int rM,  int dM, int distMax, Point2D pos, int ptPara,boolean vivant) {
        super(nom, pV, ptM, 0, pP, pM, rM, 0, dM, distMax, pos, ptPara,vivant);
    }

    
    /**
     * Constructeur de recopie
     * @param m Mage à recopier.
     */
    public Mage(Mage m) {
        super(m);
    }
    
    /**
     * Constructeur par défaut.
     */
    public Mage() {
    }

    /**
     * Méthode combatre du mage
     *
     * @param c Cible de l'attaque
     */
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) >= 1 && this.getPos().distance(c.getPos()) < this.getDistAttMax() && this.getPtMana() >= 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jetArcher = generateurAleatoire.nextInt(100);
            this.setPtMana(this.getPtMana() - 1); // On retire un point de mana
            if (jetArcher <= this.getPourcentageMag()) {
                    c.setPtVie(c.getPtVie() - this.getDegMag());
                }
            else {
                System.out.println("L'attaque a échoué...");
            }
        } else {
            System.out.println("La cible n'est pas à portée !");
        }
    }
}
