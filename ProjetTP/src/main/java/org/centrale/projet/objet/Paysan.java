/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe des paysans. Sous-classe des personnages.
 * @author bodet
 */
public class Paysan extends Personnage {
    /**
     * Constuit un paysan en lui donnant ses attributs en paramètres.
     * @param nom Nom du paysan.
     * @param pV Points de vie.
     * @param pP Pourcentage de parade.
     * @param distMax Distance maximale d'attaque.
     * @param pos Position du paysan.
     * @param ptPara Point de parade.
     * @param vivant Dit si le paysan est vivant.
     */
    public Paysan(String nom, int pV, int pP, int distMax, Point2D pos, int ptPara,boolean vivant){
        super(nom, pV, 0, 0, pP, 0, 0, 0, 0, distMax, pos, ptPara,vivant);  // On sous-entend que le paysan a 0 points de mana
    }
    
    /**
     * Constructeur aléatoire utilisé dans la classe Joueur
     * @param nom Nom du paysan
     * @param pos Position du paysan
     * @param vivant Indique si le paysan est vivant
     */
    public Paysan(String nom,Point2D pos, boolean vivant){
        super(nom,pos,vivant);
    
        this.setPtVie(ThreadLocalRandom.current().nextInt(10,30));
        this.setPourcentageAtt(ThreadLocalRandom.current().nextInt(5,10));
        this.setPourcentagePar(ThreadLocalRandom.current().nextInt(1,5));
        this.setPourcentageMag(ThreadLocalRandom.current().nextInt(1,5));
        this.setPourcentageResistMag(ThreadLocalRandom.current().nextInt(10,30));
        this.setDegAtt(ThreadLocalRandom.current().nextInt(1,5));
        this.setDegMag(ThreadLocalRandom.current().nextInt(1,5));
        this.setDistAttMax(ThreadLocalRandom.current().nextInt(3,6));
        this.setPtPar(ThreadLocalRandom.current().nextInt(20,30));    
    }
        
    
    /**
     * Constructeur de recopie.
     * @param p Paysan à recopier
     */
    public Paysan(Paysan p){
        super(p);
    }
    
    /**
     * Constructeur par défaut.
     */
    public Paysan(){
        super();
    }
}
