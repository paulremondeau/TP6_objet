/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author remondeau
 */
public class Joueur {
    /**
     * Personnage du joueur.
     */
    private Personnage perso;
    
    public Personnage getPerso() {
        return perso;
    }
    
    /**
     * Constructeur du personnage
     * @param listeCreatures Liste de créatures qui doivent avoir des positions différentes.
     */
    public Joueur(ArrayList<Creature> listeCreatures){
        
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
        
        int pV;
        int pA;
        int pP;
        int pM;
        int rM;
        int dA;
        int dM;
        int distMax;
        Point2D pos = new Point2D(listeCreatures);  // Génère une position non utilisée
        int ptPara;
        boolean vivant = true;
        
        switch(classe){  // On génère des valeurs différentes selon la classe
   
            case "Paysan": 
                pV = ThreadLocalRandom.current().nextInt(10,30);
                pA = ThreadLocalRandom.current().nextInt(5,10);
                pP = ThreadLocalRandom.current().nextInt(1,5);
                pM = ThreadLocalRandom.current().nextInt(1,5);
                rM = ThreadLocalRandom.current().nextInt(10,30);
                dA = ThreadLocalRandom.current().nextInt(1,5);
                dM = ThreadLocalRandom.current().nextInt(1,5);
                distMax = ThreadLocalRandom.current().nextInt(3,6);
                ptPara = ThreadLocalRandom.current().nextInt(20,30);

                perso = new Paysan(nom,pV,pA,pP,pM,rM,dA,dM,distMax,pos,ptPara,vivant);
                break;

            case "Archer":
                int nbF = ThreadLocalRandom.current().nextInt(20,30);
                pV = ThreadLocalRandom.current().nextInt(40,50);
                pA = ThreadLocalRandom.current().nextInt(30,40);
                pP = ThreadLocalRandom.current().nextInt(20,30);
                pM = ThreadLocalRandom.current().nextInt(10,20);
                rM = ThreadLocalRandom.current().nextInt(30,35);
                dA = ThreadLocalRandom.current().nextInt(15,25);
                dM = ThreadLocalRandom.current().nextInt(1,5);
                distMax = ThreadLocalRandom.current().nextInt(20,30);
                ptPara = ThreadLocalRandom.current().nextInt(20,30);
                perso = new Archer(nom,pV,pA,pP,pM,rM,dA,dM,distMax,pos,nbF,ptPara,vivant);
                break;

            case "Guerrier":
                pV = ThreadLocalRandom.current().nextInt(80,100);
                pA = ThreadLocalRandom.current().nextInt(70,80);
                pP = ThreadLocalRandom.current().nextInt(60,70);
                rM = ThreadLocalRandom.current().nextInt(60,70);
                dA = ThreadLocalRandom.current().nextInt(60,70);
                ptPara = ThreadLocalRandom.current().nextInt(60,70);
                perso = new Guerrier(nom,pV,pA,pP,rM,dA,pos,ptPara,vivant);
                break;

            case "Mage": 
            default:
                pV = ThreadLocalRandom.current().nextInt(25,30);
                int ptM = ThreadLocalRandom.current().nextInt(70,80);
                pP = ThreadLocalRandom.current().nextInt(10,15);
                pM = ThreadLocalRandom.current().nextInt(50,65);
                rM = ThreadLocalRandom.current().nextInt(70,80);
                dM = ThreadLocalRandom.current().nextInt(60,70);
                distMax = ThreadLocalRandom.current().nextInt(10,15);
                ptPara = ThreadLocalRandom.current().nextInt(20,30);
                perso = new Mage(nom,pV,ptM,pP,pM,rM,dM,distMax,pos,ptPara,vivant);
                break;
         }
        
       System.out.println("\n"+nom+" est créé !");
        
    }
    
    
    
}


/*ByteArrayOutputStream result = new ByteArrayOutputStream();
 byte[] buffer = new byte[1024];
 for (int length; (length = inputStream.read(buffer)) != -1; ) {
     result.write(buffer, 0, length);
 }
 // StandardCharsets.UTF_8.name() > JDK 7
 return result.toString("UTF-8");*/