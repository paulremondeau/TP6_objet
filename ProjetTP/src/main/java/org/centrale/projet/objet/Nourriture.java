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
    
    private int puissance;
    private int duree;
    private String carac;
    
    /**
     * 
     * @param carac la caractéristique qui sera modifiée
     * @param puissance la valeur du bonus/valus que procure la nourriture
     */
    public Nourriture(int puissance,int duree,String carac) {
        this.puissance = puissance;
        this.duree = duree;
        this.carac = carac;
    }
    
    public void utiliser(Personnage p){
        p.getListeNourriture().add(this);
    }
    
    
}
