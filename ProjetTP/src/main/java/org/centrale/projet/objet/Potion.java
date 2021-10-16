/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe des potions.
 * @author remondeau
 */
public abstract class Potion extends Objet{
    
    /*
    Puissance de la potion
    */
    private int puissance;
    
    /**
     * Création d'une potion
     * @param nom Nom de la potion.
     * @param puissance Puissance de la potion.
     * @param pos Position de l'objet.
     */
    public Potion(String nom, Point2D pos, int puissance) {
        super(nom, pos);
        this.puissance = puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }
    
    @Override
    /**
     * Utiliser une potion.
     * @param p Personnage qui boit la potion
     */
    public void utiliser(Personnage p){
        super.utiliser(p);
        System.out.println("La potion est utilisée ! ");
    }

}
