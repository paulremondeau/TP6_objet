/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe du nuage toxique
 * @author remondeau
 */
public class NuageToxique extends Object implements Deplacable, Combattant {

    Point2D pos;

    public NuageToxique(Point2D pos) {
        this.pos = pos;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    

    @Override
    public void combattre(Creature c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deplacer(int x, int y) {
        
        if(x>1 || x<-1 || y>1 || y<-1){
            System.out.println("Déplacement non valide");
        }
        else{
            this.pos.translate(x,y);
        }
    }
    
    
}
