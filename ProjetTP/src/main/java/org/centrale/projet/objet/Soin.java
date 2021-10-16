/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe des potions de soin.
 * @author remondeau
 */
public class Soin extends Potion{
    
    /**
     * Construction d'une potion de vie en spécifiant ses attributs 
     * @param nom Nom de la potion
     * @param puissance Valeur de point de vie gagnée lors de la consommation de la potion
     * @param pos Position de la potion.
     */
    public Soin(String nom, Point2D pos, int puissance) {
        super(nom, pos, puissance);
    }
    
    @Override
     /**
     * Utilisation de la potion de soin.
     * @param p Personnage qui boit la potion
     */
    public void utiliser(Personnage p){
        super.utiliser(p);
        p.setPtVie(p.getPtVie() + this.getPuissance());
    }
    
}
