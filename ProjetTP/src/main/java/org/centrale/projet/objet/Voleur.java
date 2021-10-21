/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe des voleurs, sous-classe des personnages.
 * @author bodet
 */
public class Voleur extends Personnage implements Combattant {

    /**
     * Constructeur qui prend les attributs en paramètres.
     *
     * @param nom Nom du voleur
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param rM Résistance magique.
     * @param dA Dégat d'attaque.
     * @param pos Position du voleur.
     * @param ptPara Point de parade.
     * @param vivant Dit si le voleur est vivant.
     */
    public Voleur(String nom, int pV, int pA, int pP, int rM, int dA, Point2D pos, int ptPara,boolean vivant) {
        super(nom, pV, 0, pA, pP, 0, rM, dA, 0, 1, pos, ptPara,vivant);
    }

    /**
     * Constructeur de recopie.
     * @param v Voleur à recopier.
     */
    public Voleur(Voleur v) {
        super(v);
    }
    
    /**
     * Constructeur par défaut.
     */
    public Voleur(){
        
    }

    /**
     * Constructeur aléatoire utilisé dans la classe Joueur
     * @param nom Nom du voleur
     * @param pos Position du voleur
     * @param vivant Indique si le voleur est vivant
     */
    public Voleur(String nom,Point2D pos, boolean vivant){
        super(nom,pos);
    
        this.setPtVie(ThreadLocalRandom.current().nextInt(80,100));
        this.setPourcentageAtt(ThreadLocalRandom.current().nextInt(50,70));
        this.setPourcentagePar(ThreadLocalRandom.current().nextInt(70,90));
        this.setPourcentageResistMag(ThreadLocalRandom.current().nextInt(20,30));
        this.setDegAtt(ThreadLocalRandom.current().nextInt(10,20));
        this.setPtPar(ThreadLocalRandom.current().nextInt(2,7));    
    }


    /**
     * Méthode combattre du voleur.
     * @param c Cible de l'attaque
     */
    @Override
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) == 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jetVoleur = generateurAleatoire.nextInt(100);
            if (jetVoleur <= this.getPourcentageAtt()) {
                
                int jetCreature = generateurAleatoire.nextInt(100);
                if (jetCreature<=c.getPtPar()){ // Si la créature pare le coup
                    System.out.println("La cible a paré le coup !");
                    int degat;
                    degat = Math.max(0,this.getDegAtt()-c.getPtPar());
                    c.setPtVie(c.getPtVie()-degat);
                }
                else{
                    System.out.println("La cible a pris un coup direct !");
                    c.setPtVie(c.getPtVie() - this.getDegAtt());
                }
                
            } else {
                System.out.println("L'attaque a échoué...");
            }
        } else {
            System.out.println("La cible est trop loin !");
        }
        
        if (c.getPtVie()<0){
            c.setVivant(false);
        }    
    }
    
    
    
    
    
}
