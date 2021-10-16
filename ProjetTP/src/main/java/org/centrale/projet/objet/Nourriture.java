/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe des nourritures du jeu.
 * @author remondeau
 */
public abstract class Nourriture extends Object {
    
    String carac;
    int puissance;

    /**
     * 
     * @param carac la caractéristique qui sera modifiée
     * @param puissance la valeur du bonus/valus que procure la nourriture
     */
    public Nourriture(String carac, int puissance) {
        this.carac = carac;
        this.puissance = puissance;
    }
    
    
    
}
