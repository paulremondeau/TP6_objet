/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe de nourriture procurant un malus de dégats d'attaque.
 * @author remondeau
 */
public final class MalusDegAtt extends Nourriture{
    
    /**
     * Constructeur de la nourriture.
     * @param pos Position de la nourriture.
     * @param puissance Valeur du malus pour les degat d'attaque.
     * @param duree Durée du malus.
     */
    public MalusDegAtt(Point2D pos,int puissance, int duree) {
        super(pos,puissance, duree);
    }
    
    /**
     * Utilisation de la nourriture.
     * @param p Personnage cible.
     */
    @Override
    public void utiliser(Personnage p){
        super.utiliser(p);
        p.setDegAtt(p.getDegAtt()-this.getPuissance());
    }

    /**
     * Met fin aux effets de la nourriture.
     * @param p Personnage cible.
     */
    @Override
    public void fin(Personnage p) {
        p.setDegAtt(p.getDegAtt()+this.getPuissance());
    }


}
