/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe des nourritures du jeu.
 * @author remondeau
 */
public abstract class Nourriture extends Objet {
    
    private int puissance;
    private int duree;
    
    /**
     * 
     * @param pos Position de la nourriture.
     * @param duree Duree de l'effet de la nourriture.
     * @param puissance la valeur du bonus/valus que procure la nourriture
     */
    public Nourriture(Point2D pos,int puissance,int duree) {
        super(pos);
        this.puissance = puissance;
        this.duree = duree;
    }
    
    /**
     * Utilisation de la nourriture.
     * Lorsqu'un nourriture est utilisée, elle est ajouté à la liste de nourritures du personnage.
     * @param p Personnage cible
     */
    @Override
    public void utiliser(Personnage p){
        p.getListeNourriture().add(this);
    }
    
    /**
     * Méthode abstraite de fin d'une nourriture.
     * Lorsque la durée atteint 0, il faudra retirer les effets de la nourriture et la retiré de la liste des nourritures du personnage.
     * @param p Personnage cible
     */
    public abstract void fin(Personnage p);

    public int getPuissance() {
        return puissance;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    
}
