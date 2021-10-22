/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe de nourriture procurant un bonus de point de parade.
 * @author remondeau
 */
public class BonusPtPar extends Nourriture{ 
    
    /**
     * Constructeur de la nourriture.
     * @param puissance Valeur du bonus pour les points de parades.
     * @param duree Durée du bonus.
     */
    public BonusPtPar(Point2D pos,int puissance,int duree){
        super(pos,puissance,duree);
    }
    
    /**
     * Utilisation de la nourriture.
     * @param p Personnage cible.
     */
    @Override
    public void utiliser(Personnage p){
        super.utiliser(p);
        p.setPtPar(p.getPtPar()+this.getPuissance());
    }
    
    /**
     * Met fin aux effets de la nourriture.
     * @param p Personnage cible.
     */
    @Override
    public void fin(Personnage p){
        p.setPtPar(p.getPtPar()-this.getPuissance());
    }
    
}
