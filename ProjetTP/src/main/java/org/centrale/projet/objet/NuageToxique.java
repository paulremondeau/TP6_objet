/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * La classe du nuage toxique
 * @author remondeau
 */
public class NuageToxique extends Objet implements Deplacable, Combattant {

    private int pourcentageAtt;
    private int pourcentagePar;
    private int degAtt;
    private int ptPar;
    
    public NuageToxique(Point2D pos) {
        super(pos);
    }

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

    @Override
    public void combattre(Creature c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deplacer(int x, int y) {
        
        if(x>1 || x<-1 || y>1 || y<-1){
            System.out.println("Déplacement non valide");
        }
        else{
            this.getPos().translate(x,y);
        }
    }
    
    
}
