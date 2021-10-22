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
     * @param puissance Puissance de la potion.
     * @param pos Position de l'objet.
     */
    public Potion(Point2D pos, int puissance) {
        super(pos);
        this.puissance = puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getPuissance() {
        return puissance;
    }
    
    /**
     * Affichage des informations des potions.
     */
    public void affiche(){
        super.affiche();
        System.out.println("Elle possède une puissance de "+this.puissance+".");
    }

}
