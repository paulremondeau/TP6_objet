/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe des objets
 * @author remondeau
 */
public abstract class Objet extends ElementDeJeu {

    private Point2D pos;
    private boolean used;
    

    /**
     * Constructeur d'un objet
     * @param pos Position de l'objet.
     */
    public Objet(Point2D pos) {
        this.pos = pos;
        this.used = false;
    }

    public Point2D getPos() {
        return this.pos;
    }

    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    public void utiliser(Personnage p){
        System.out.println("Utilisation de "+ this.getClass().getSimpleName() + "par " + p.getNom());
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
    /**
     * Affichage de l'objet
     */
    public void affiche(){
        System.out.println("L'objet " + this.getClass().getSimpleName()+ " est situé en " + this.pos + ".");
    }
}
