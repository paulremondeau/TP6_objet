/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;


/**
 * Classe des créatures.
 * @author bodet
 */
public abstract class Creature extends ElementDeJeu implements Deplacable{
    
    private boolean vivant;
    private int ptVie;
    private int pourcentageAtt;
    private int pourcentagePar;
    private int degAtt;
    private Point2D pos;
    private int ptPar;

    /**
     * Constructeur d'une créature avec tous les attributs.
     * @param pV Points de vie.
     * @param pA Pourcentage d'attaque.
     * @param pP Pourcentage de parade.
     * @param dA Dégats d'attaque.
     * @param pos Position de la créature.
     * @param ptPara Point de parade.
     * @param vivant Dit si la créature est vivante.
     */
    public Creature(int pV, int pA, int pP, int dA, Point2D pos, int ptPara,boolean vivant) {
        this.ptVie = pV;
        this.pourcentageAtt = pA;
        this.pourcentagePar = pP; 
        this.degAtt = dA;
        this.pos = new Point2D(pos);
        this.ptPar=ptPara;
        this.vivant = vivant;
    }

    /** 
     * Constructeur de recopie.
     * @param c créature copié.
     */
    public Creature(Creature c) {
        this.ptVie = c.ptVie;
        this.pourcentageAtt = c.pourcentageAtt;
        this.degAtt = c.degAtt;
        this.pos = new Point2D(c.pos);
        this.pourcentageAtt=c.pourcentagePar;
        this.ptPar=c.ptPar;
        this.vivant = c.vivant;
    }
    
    
    /**
     * Constructeur aléatoire.
     * @param pos Position de la créature créée. 
     */
    public Creature(Point2D pos){
        this.pos = pos;
        this.vivant=true;
    }
    
    /**
     * Construit une créature avec les valeurs par défaut.
     */
    public Creature() {
        this.pos=new Point2D(); // les autres attributs sont initialisés à 0, ce sont des entiers
    }
    
    

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int value) {
        this.ptVie = value;
        if (this.ptVie<=0){
            this.vivant = false;
        }
    }

    public int getPourcentageAtt() {
        return pourcentageAtt;
    }

    public void setPourcentageAtt(int value) {
        this.pourcentageAtt = value;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int value) {
        this.degAtt = value;
    }
    
    public Point2D getPos() {
        return this.pos;
    }

    public void setPos(Point2D pos) {
        this.pos = new Point2D(pos);
    }

    public int getPourcentagePar() {
        return pourcentagePar;
    }

    public void setPourcentagePar(int value) {
        this.pourcentagePar = value;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int value) {
        this.ptPar = value;
    }

    public void setVivant(boolean vivant) {
        this.vivant = vivant;
    }

    public boolean isVivant() {
        return vivant;
    }
    
    
    
    /**
     * Déplace le monstre sur une des cases adjacentes en utilisant +1, 0 ou -1 dans x et y. Il est possible d'avoir un déplacement nul.
     * Si on essaye de donner des valeurs supérieures à 1 ou inférieures à -1 on affiche un message d'erreur.
     * 
     * @param monde Le monde où le déplacement est effectué
     * @param x le déplacement voulu selon l'axe X
     * @param y le déplacement voulu selon l'axe Y
     * @throws org.centrale.projet.objet.DeplacementIncorrectException
     */    
    @Override
    public void deplacer(World monde,int x, int y) throws DeplacementIncorrectException{
        
        if(x>1 || x<-1 || y>1 || y<-1 || pos.getX()+x<0 || pos.getX()+x>monde.getLargeur() || pos.getY()+y<0 || pos.getY()+y>monde.getHauteur()){
            throw new DeplacementIncorrectException("Déplacement non valide");
        }
        else{  
            this.pos.translate(x,y);
        }
        
    }
        
    /**
     * Affiche les informations concernant la créature.
     */
    public void affiche(){
        System.out.println("La créature a " + this.ptVie+ " points de vie et se situe en " + this.pos + ".");
        System.out.println("La créature a un pourcentage d'attaque de "+ this.pourcentageAtt +"%.");
        System.out.println("La créature a "+ this.degAtt +" points d'attaque.");
    }
    
    /**
     * Affiche un résumé des informations de la créature pour tenir sur une ligne.
     * @return Les informations
     */
    @Override
    public String toString(){
        
        String res = "";
        res += this.getClass().getSimpleName() + " est à la position " + this.pos.toString() + " avec "+ this.ptVie+" ptVie";
        
        return res;
    }
}
