/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.Random;
import static org.centrale.projet.objet.World.TAILLE;


/**
 * Position dans une grille en deux dimensions. 
 * @author bodet
 */
public class Point2D {

    
    private int x;
    private int y;
   
    /**
     * Construit un point en [0;0]
     */
    public Point2D() {
        this.x=0;
        this.y=0;
    }
    /**
     * Construit un point en définissant ses coordonnées.
     * @param x Abscisse du point
     * @param y Ordonnée du point
     */
    public Point2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructeur de recopie.
     * @param p Point que l'on copie
     */
    public Point2D(Point2D p){
        this.x = p.x;
        this.y = p.y;
    }
    
    /**
     * Crée une position aléatoire dans le monde, différente des positions des créatures en paramètre.
     * @param liste Liste des créatures avec des positions.
     */
    public Point2D(ArrayList<Creature> liste){
        Random generateurAleatoire = new Random();
        boolean estDifferente=false;
        boolean estLibre;
        while (estDifferente!=true){
            this.x=generateurAleatoire.nextInt(TAILLE);
            this.y=generateurAleatoire.nextInt(TAILLE);
            estLibre=true;
            for (Creature o : liste){
                if (o.getPos().equals(new Point2D(this.x,this.y))){
                    estLibre=false;
                    break;
                }
            }
            if (estLibre){ // Sort de la boucle while si on a trouvé une position acceptable
                estDifferente=true;
            }
        }
    }
    
    
    public void setX(int value){
        this.x = value;
    }
    
    public void setY(int value){
        this.y = value;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void translate(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    public void affiche(){
        System.out.println("[" +  this.x + ";" + this.y + "]");
    }
    
    public String toString(){
        String res = "[" +  this.x + ";" + this.y + "]";
        return res;
    }
    
    public boolean equals(Point2D p){
        
        return ( (this.x == p.getX()) && this.y == p.getY());
        
    }
    
    /**
     * Calcule la distance entre ce point et un autre point p.
     * @param p Le point dont on évalue la distance
     * @return La distance 
     */
    public double distance(Point2D p){
        
        double result = Math.sqrt(Math.pow((p.x - this.x),2) + Math.pow((p.y - this.y),2));
        
        return result;
        
    }
    

    
    
}
