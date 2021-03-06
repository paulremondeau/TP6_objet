/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;

/**
 * Classe des personnages. Sous-classe des creatures.
 * @author bodet
*/

public abstract class Personnage extends Creature{
    private String nom;
    private int ptMana;
    private int pourcentageMag;
    private int pourcentageResistMag;
    private int degMag;
    private int distAttMax;
    
    private ArrayList<Nourriture> listeNourriture;

    
    /**
     * Constructeur qui prend les attributs en paramètres.
     * @param nom Nom du personnage
     * @param pV Points de vie.
     * @param ptM Points de magie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param pM Pourcentage de magie.
     * @param rM Résistance magique.
     * @param dA Dégat d'attaque.
     * @param dM Dégat magique.
     * @param distMax Distance d'attaque maximale.
     * @param pos Position du personnage.
     * @param ptPara Point de parade.
     * @param vivant Dit si le personnage est vivant.
     */
    public Personnage(String nom, int pV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, Point2D pos, int ptPara,boolean vivant) {
        super(pV, pA, pP, dA, pos, ptPara,vivant);
        this.nom = nom;
        this.ptMana = ptM;
        this.pourcentageMag = pM;
        this.pourcentageResistMag = rM;
        this.degMag = dM;
        this.distAttMax = distMax;
        this.listeNourriture = new ArrayList();
    }

    /**
     * Constructeur de recopie
     * @param perso Personnage à recopier.
     */
    public Personnage(Personnage perso) {
        super(perso);
        this.nom=perso.nom;
        this.ptMana=perso.ptMana;
        this.pourcentageMag=perso.pourcentageMag;
        this.pourcentageResistMag=perso.pourcentageResistMag;
        this.degMag=perso.degMag;
        this.distAttMax=perso.distAttMax;
        this.listeNourriture = new ArrayList();
    }
    
    /**
     * Constructeur aléatoire.
     * @param nom Nom du personnage.
     * @param pos Position du personnage.
     */
    public Personnage(String nom,Point2D pos){
        super(pos);
        this.nom = nom;
        this.listeNourriture = new ArrayList();
    }

    /**
     * Constructeur par défaut.
     */
    public Personnage() {
    super();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public void setPtMana(int value) {
        this.ptMana = value;
    }

    public int getPtMana() {
        return ptMana;
    }

    public void setPourcentageMag(int value) {
        this.pourcentageMag = value;
    }

    public int getPourcentageMag() {
        return this.pourcentageMag;
    }

    public void setPourcentageResistMag(int value) {
        this.pourcentageResistMag = value;
    }

    public int getPourcentageResistMag() {
        return pourcentageResistMag;
    }

    public void setDegMag(int value) {
        this.degMag = value;
    }

    public int getDegMag() {
        return this.degMag;
    }

    public void setDistAttMax(int value) {
        this.distAttMax = value;
    }

    public int getDistAttMax() {
        return this.distAttMax;
    }

    public ArrayList<Nourriture> getListeNourriture() {
        return this.listeNourriture;
    }

    public void setListeNourriture(ArrayList<Nourriture> listeNourriture) {
        this.listeNourriture = listeNourriture;
    }
    
    /**
     * Affiche les différentes informations du personnage.
     * 
     */
    @Override
    public void affiche() {
        System.out.println("Le personnage s'appelle "+this.nom + " et est situé à la position " + this.getPos() + ".");
        System.out.println(this.nom+" a "+ this.getPtVie()+" points de vie.");
        System.out.println(this.nom+" a "+ this.ptMana+" points de mana.");
        System.out.println(this.nom+" a "+ this.getDegAtt()+" points de dégats physique.");
        System.out.println(this.nom+" a "+ this.degMag+" points de dégats magique.");
        System.out.println(this.nom+" a une portée de "+ this.distAttMax+".");
        System.out.println(this.nom+" a un pourcentage d'attaque de "+ this.getPourcentageAtt() +"%.");
        System.out.println(this.nom+" a "+this.getPtPar() + "% de chance de parer." );
        System.out.println(this.nom+" a un pourcentage d'attaque magique de "+ this.pourcentageMag + "%.");
        System.out.println(this.nom+" a "+this.pourcentageResistMag + "% de résistance magique." );
    }
    
     /**
     * Affiche un résumé des informations de la créature pour tenir sur une ligne.
     * @return Les informations
     */
    @Override
    public String toString(){
        
        String res = "";
        res += "Le "+this.getClass().getSimpleName() + " " + this.getNom() + " est à la position " + this.getPos().toString() + " avec "+ this.getPtVie()+" ptVie";
        
        return res;
    }
    
}
