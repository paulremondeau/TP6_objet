/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe des paysans. Sous-classe des personnages.
 * @author bodet
 */
public class Paysan extends Personnage {
    /**
     * Constuit un paysan en lui donnant ses attributs en paramètres.
     * @param nom Nom du paysan.
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param pM Pourcentage de magie.
     * @param rM Pourcentage de résistance magique.
     * @param dA Dégats d'attaque.
     * @param dM Dégats magiques.
     * @param distMax Distance maximale d'attaque.
     * @param pos Position du paysan.
     * @param ptPara Point de parade.
     * @param vivant Dit si le paysan est vivant.
     */
    public Paysan(String nom, int pV, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, Point2D pos, int ptPara,boolean vivant){
        super(nom, pV, 0, pA, pP, pM, rM, dA, dM, distMax, pos, ptPara,vivant);  // On sous-entend que le paysan a 0 points de mana
    }
    
    /**
     * Constructeur de recopie.
     * @param p 
     */
    public Paysan(Paysan p){
        super(p);
    }
    
    /**
     * Constructeur par défaut.
     */
    public Paysan(){
        super();
    }
}
