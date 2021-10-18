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
    
    @Override
    public void utiliser(Personnage p){
        super.utiliser(p);
        p.setPtPar(p.getPtPar()+this.getPuissance());
    }
    
    @Override
    public void fin(Personnage p){
        p.setPtPar(p.getPtPar()-this.getPuissance());
    }
    
}
