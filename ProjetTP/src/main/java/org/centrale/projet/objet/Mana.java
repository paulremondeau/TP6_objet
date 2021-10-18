/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe des potions de mana.
 * @author remondeau
 */
public class Mana extends Potion{
    
    /**
     * Construction d'une potion de mana en spécifiant ses attributs
     * @param puissance Valeur de point de mana gagnée lors de la consommation de la potion
     * @param pos Position de la potion.
     */
    public Mana(Point2D pos, int puissance ) {
        super(pos, puissance);
    }
    
    @Override
    /**
     * Utilisation de la potion de mana.
     * @param p Personnage qui boit la potion
     */
    public void utiliser(Personnage p){
        super.utiliser(p);
        p.setPtMana(p.getPtMana() + this.getPuissance());
    }
}
