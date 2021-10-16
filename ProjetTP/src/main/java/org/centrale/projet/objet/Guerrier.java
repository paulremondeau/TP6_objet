/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * Classe des guerriers. Sous-classe de Personnage.
 *
 * @author bodet
 */
public class Guerrier extends Personnage implements Combattant{

    /**
     * Constructeur qui prend les attributs en paramètres.
     *
     * @param nom Nom du guerrier
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param rM Résistance magique.
     * @param dA Dégat d'attaque.
     * @param pos Position du guerrier.
     * @param ptPara Point de parade.
     * @param vivant Dit si le guerrier est vivant.
     */
    public Guerrier(String nom, int pV, int pA, int pP, int rM, int dA, Point2D pos, int ptPara,boolean vivant) {
        super(nom, pV, 0, pA, pP, 0, rM, dA, 0, 1, pos, ptPara,vivant);
    }

    /**
     * Constructeur de recopier
     *
     * @param g guerrier à recopier.
     */
    public Guerrier(Guerrier g) {
        super(g);
    }

    /**
     * Constructeur par défaut.
     */
    public Guerrier() {
    }

    /**
     * Méthode combatre du guerrier
     *
     * @param c Cible de l'attaque
     */
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) == 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jetGuerrier = generateurAleatoire.nextInt(100);
            if (jetGuerrier <= this.getPourcentageAtt()) {
                
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
