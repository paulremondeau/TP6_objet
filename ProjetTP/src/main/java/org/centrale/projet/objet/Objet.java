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
    
    private String nom;
    private Point2D pos;

    /**
     * Constructeur d'un objet
     * @param nom Nom de l'objet.
     * @param pos Position de l'objet.
     */
    public Objet(String nom, Point2D pos) {
        this.nom = nom;
        this.pos = pos;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public Point2D getPos() {
        return this.pos;
    }

    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    public void utiliser(Personnage p){
        System.out.println("Utilisation de " + this.nom + " par " + p.getNom());
    }
}
