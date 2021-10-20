/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author remondeau
 */
public class TestSauvegardeMonde {
    
    public static void main(String[] args){
        
        World monde = new World();
        monde.creeMondeAlea();
        
        SauvegardePartie save = new SauvegardePartie();
        save.enregistrerMonder(monde);
        
    }
    
}
