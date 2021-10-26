/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe des guerriers. Sous-classe de Personnage.
 *
 * @author bodet
 */
public final class Guerrier extends Personnage implements Combattant{

    /**
     * Constructeur qui prend les attributs en paramètres.
     *
     * @param nom Nom du guerrier
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param rM Résistance magique.
     * @param dA Dégat d'attaque.
     * @param pos Position du guerrier.
     * @param ptPara Point de parade.
     * @param vivant Dit si le guerrier est vivant.
     */
    public Guerrier(String nom, int pV, int pA, int pP, int rM, int dA, Point2D pos, int ptPara,boolean vivant) {
        super(nom, pV, 0, pA, pP, 0, rM, dA, 0, 1, pos, ptPara,vivant);
    }

    /**
     * Constructeur de recopie
     *
     * @param g guerrier à recopier.
     */
    public Guerrier(Guerrier g) {
        super(g);
    }

    /**
     * Constructeur par défaut.
     */
    public Guerrier() {
    }

    /**
     * Constructeur aléatoire utilisé dans la classe Joueur
     * @param nom Nom du guerrier
     * @param pos Position du guerrier
     * @param vivant Indique si le guerrier est vivant
     */
    public Guerrier(String nom,Point2D pos, boolean vivant){
        super(nom,pos);
    
        this.setPtVie(ThreadLocalRandom.current().nextInt(80,100));
        this.setPourcentageAtt(ThreadLocalRandom.current().nextInt(70,80));
        this.setPourcentagePar(ThreadLocalRandom.current().nextInt(60,70));
        this.setPourcentageResistMag(ThreadLocalRandom.current().nextInt(60,70));
        this.setDistAttMax(1);
        this.setDegAtt(ThreadLocalRandom.current().nextInt(60,70));
        this.setPtPar(ThreadLocalRandom.current().nextInt(60,70));    
    }
    
    
    /**
     * Méthode combattre du guerrier.
     * Le guerrier ne peut combattre que au corps à corps.
     * @param c Cible de l'attaque
     */
    @Override
    public void combattre(Creature c) {

        if (this.getPos().distance(c.getPos()) == 1) { 
            Random generateurAleatoire = new Random();
            int jetGuerrier = generateurAleatoire.nextInt(100);
            if (jetGuerrier <= this.getPourcentageAtt()) {
                
                int jetCreature = generateurAleatoire.nextInt(100);
                if (jetCreature<=c.getPourcentagePar()){ // Si la créature pare le coup
                    int degat;
                    degat = Math.max(0,this.getDegAtt()-c.getPtPar());
                    c.setPtVie(c.getPtVie()-degat);
                    System.out.println("La cible a paré le coup !" + c.getClass().getSimpleName() + " a subi " + degat + " points de dégats.");
                }
                else{
                    System.out.println("La cible a pris un coup direct !"+ c.getClass().getSimpleName() + " a subi " + this.getDegAtt() + " points de dégats.");
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
