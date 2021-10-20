/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Tests utilisés lors de la séance.
 * @author bodet
 */
public class TestSeance6 {

    public static void main(String[] args) {

        World monde = new World();
        monde.creeMondeAlea();
        
        
        Joueur moi = monde.creeJoueurAlea();
        System.out.println("Affichons le personnage du joueur :\n");
        moi.getPerso().affiche();
        
        monde.listeCreatures.get(0).affiche();
        System.out.println("Potion 0 en position : "+ monde.listeObjets.get(0).getPos());
        System.out.println("Nombre de créatures : "+ monde.listeCreatures.size());
        System.out.println("Nombre de potions : "+ monde.listeObjets.size());
        
        monde.tourDeJeu();
        
        
        
    }
}