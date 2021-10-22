/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * La classe du nuage toxique
 * @author remondeau
 */
public class NuageToxique extends Objet implements Deplacable, Combattant {

    private int pourcentageAtt;
    private int pourcentagePar;
    private int degAtt;
    private int ptPar;
    
    /**
     * Génére un nuage toxique avec des statistiques aléatoires.
     * @param pos Position du nuage
     */
    public NuageToxique(Point2D pos) {
        super(pos);

        this.setPourcentageAtt(ThreadLocalRandom.current().nextInt(70,80));
        this.setPourcentagePar(ThreadLocalRandom.current().nextInt(60,70));
        this.setDegAtt(ThreadLocalRandom.current().nextInt(60,70));
        this.setPtPar(ThreadLocalRandom.current().nextInt(60,70));    
    }
    /**
     * Génère un nuage toxique en spécifiant tous ses attributs
     * @param pos Position du nuage
     * @param pourcentageAtt Pourcentage d'attaque du nuage
     * @param pourcentagePar Pourcentage de parade du nuage
     * @param degAtt Dégats d'attaque du nuage
     * @param ptPar Points de parade du nuage
     */
    public NuageToxique(Point2D pos, int pourcentageAtt, int pourcentagePar, int degAtt, int ptPar) {
        super(pos);
        this.pourcentageAtt = pourcentageAtt;
        this.pourcentagePar = pourcentagePar;
        this.degAtt = degAtt;
        this.ptPar = ptPar;
    }

    public int getPourcentageAtt() {
        return pourcentageAtt;
    }

    public int getPourcentagePar() {
        return pourcentagePar;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPourcentageAtt(int pourcentageAtt) {
        this.pourcentageAtt = pourcentageAtt;
    }

    public void setPourcentagePar(int pourcentagePar) {
        this.pourcentagePar = pourcentagePar;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }
    
    /**
     * Méthode combattre du nuage toxique.
     * @param c Créature ciblée
     */
    @Override
    public void combattre(Creature c) {
        if (this.getPos().distance(c.getPos()) == 1) { // Si la cible est au cac et qu'on a au moins un point de mana
            Random generateurAleatoire = new Random();
            int jet = generateurAleatoire.nextInt(100);
            if (jet <= this.getPourcentageAtt()) {
                
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
    /**
     * Déplacement du nuage
     * @param x
     * @param y 
     */
    @Override
    public void deplacer(World monde,int x, int y) throws DeplacementIncorrectException{
        
        if(x>1 || x<-1 || y>1 || y<-1 || getPos().getX()-x==0 || getPos().getX()+x==monde.getLargeur() || getPos().getY()-y==0 || getPos().getY()+y==monde.getHauteur()){
            throw new DeplacementIncorrectException("Déplacement non valide");
        }
        else{  
            getPos().translate(x,y);
        }
        
    }
    
    /**
     * Affichage des informations concernant le nuage toxique.
     */
    public void affiche(){
        super.affiche();
        System.out.println("Le nuage toxique a un pourcentage d'attaque de "+ this.pourcentageAtt +"%.");
        System.out.println("La nuage toxique a "+ this.degAtt +" points d'attaque et "+this.ptPar+" points de parade.");
    }
    
}
