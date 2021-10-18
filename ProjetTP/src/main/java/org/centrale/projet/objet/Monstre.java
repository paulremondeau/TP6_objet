/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe des monstres. Sous-classe des créatures.
 * @author bodet
 */
public abstract class Monstre extends Creature{

    
    /**
     * Constructeur ayant tous les attributs en paramètres.
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param dA Dégats d'attaque.
     * @param pos Position du monstre.
     * @param ptPara Point de parade.
     * @param vivant Dit si le monstre est vivant. 
     */
    public Monstre(int pV, int pA, int pP, int dA, Point2D pos, int ptPara,boolean vivant) {
        super(pV, pA, pP, dA, pos, ptPara,vivant);
    }

    /**
     * Constructeur de recopie.
     * @param m Monstre à recopier.
     */
    public Monstre(Monstre m) {
        super(m);
    }
    
    public Monstre(Point2D pos){
        super(pos);
    }
    
    /**
     * Constructeur par défaut.
     */
    public Monstre() {
    super();
    }

    /**
     * Affiche les informations du monstre
     */
    @Override
    public void affiche(){
        System.out.println("Le monstre a "+ this.getPtVie()+" points de vie et se situe en " + this.getPos() + ".");
        System.out.println("Le monstre a un pourcentage d'attaque de "+ this.getPourcentageAtt() +"%.");
        System.out.println("Le monstre a un pourcentage de parade de "+ this.getPtPar() +"%.");
        System.out.println("Le monstre a "+ this.getDegAtt() +" points d'attaque.");
    }
}
