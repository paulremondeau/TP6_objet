/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Interface pour les déplacements.
 * @author remondeau
 */
public interface Deplacable{
    /**
     * Se déplace sur une case dont on spécifie les coordonnées.
     * @param x absisse où il faut se déplacer
     * @param y ordonnée où il faut se déplacer
     */
    public void deplacer(World monde,int x, int y) throws DeplacementIncorrectException;

}
