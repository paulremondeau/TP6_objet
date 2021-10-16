/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe des lapins. Sous-classe des monstres.
 * @author bodet
 */
public class Lapin extends Monstre {
    /**
     * Construit un lapin en donnant tous les attributs en paramètres.
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param dA Dégât d'attaque.
     * @param pos Position du lapin.
     * @param ptPara Point de parade.
     * @param vivant Dit si le lapin est vivant.
     */
    public Lapin(int pV, int pA, int pP, int dA, Point2D pos, int ptPara,boolean vivant) {
        super(pV, pA, pP, dA, pos, ptPara,vivant);
    }
    
    /**
     * Constructeur de recopie
     * @param m Lapin à recopier.
     */
    public Lapin(Monstre m) {
        super(m);
    }
    
    /**
     * Construit un lapin en utilisant les valeurs par défaut.
     */
    public Lapin() {
        super();
    }
    
        
    
}
