/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;
import java.util.Scanner;

/**
 *
 * @author remondeau
 */
public class Joueur {
    /**
     * Personnage du joueur.
     */
    private Personnage perso;

    public Joueur(Personnage perso){
        this.perso = perso;
    }
    
    /**
     * Constructeur du joueur
     * @param pos Position du joueur à la création de son personnage.
     */
    public Joueur(Point2D pos){
        
        String classe;
        
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Bienvenue dans World of ECN !\nQuelle classe souhaitait-vous incarner ?");
        do{
            System.out.println("Veuillez choisir entre Guerrier, Mage, Archer et Paysan.");
            classe= keyboard.nextLine();
        }while (!classe.equals("Mage") && !classe.equals("Guerrier")  && !classe.equals("Archer")  && !classe.equals("Paysan") );
        
        System.out.println("\nFormidable ! Comment s'appelera votre personnage ?");
        String nom = keyboard.nextLine();
        
        System.out.println("\nC'est noté. Génération de votre personnage...");
        
        boolean vivant = true;
        
        switch(classe){  // On génère des valeurs différentes selon la classe
            case "Paysan": 
                perso = new Paysan(nom,pos,vivant);
                break;
            case "Archer":
                perso = new Archer(nom,pos,vivant);
                break;
            case "Guerrier":
                perso = new Guerrier(nom,pos,vivant);
                break;
            case "Mage": 
                perso = new Mage(nom,pos,vivant);
                break;
            default:
                
         }
        
       System.out.println("\n"+nom+" est créé !");
        
    }
    
    public Personnage getPerso() {
        return perso;
    }
    
    
}


/*ByteArrayOutputStream result = new ByteArrayOutputStream();
 byte[] buffer = new byte[1024];
 for (int length; (length = inputStream.read(buffer)) != -1; ) {
     result.write(buffer, 0, length);
 }
 // StandardCharsets.UTF_8.name() > JDK 7
 return result.toString("UTF-8");*/