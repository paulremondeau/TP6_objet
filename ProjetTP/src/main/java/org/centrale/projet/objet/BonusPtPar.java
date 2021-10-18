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
public class BonusPtPar extends Nourriture{
       
    public BonusPtPar(int puissance,int duree){
        super(puissance,duree);
    }
    
    public void utiliser(Personnage p){
        p.listeNourriture.add(this);
    }
}
