/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;

/**
 *
 * @author remondeau
 */
public class TestBis {
    public static void main(String[] args) {
        
        Archer aaa = new Archer();
        Loup loulou = new Loup();
        ArrayList<Personnage> listePersonnages = new ArrayList<>();
        
        listePersonnages.add(aaa);
        Personnage test = listePersonnages.get(0);
        
        ((Combattant)test).combattre(loulou); 
         
        
        //(Combattant) test.combattre(loulou);
        
        //Joueur moi = new Joueur(100, listeCreature);
        //System.out.println("Affichons le personnage du joueur :\n");
        //moi.getPerso().affiche();
    }
}
