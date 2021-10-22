/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Exception d'erreur de déplacement
 * @author remondeau
 */
public class DeplacementIncorrectException extends Exception{
    
    /**
     * Constructeur de l'objet.
     * @param errorMessage 
     */
    public DeplacementIncorrectException(String errorMessage) {
        super(errorMessage);
    }
}
