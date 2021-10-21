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
public class DeplacementIncorrectException extends Exception{
    
    public DeplacementIncorrectException(String errorMessage) {
        super(errorMessage);
    }
}
