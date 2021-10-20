/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe pour sauvegarder une partie.
 * @author remondeau
 */
public class SauvegardePartie {
    
    private String source;
    private BufferedWriter fichier;
    
    public SauvegardePartie(){
        source = "temp.txt";
        try {
            fichier = new BufferedWriter(new FileWriter(source));
        } catch (IOException ex) {
            System.out.println("Ok");
            Logger.getLogger(SauvegardePartie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enregistrerMonder(World monde){
       
        try {
            this.fichier.write("Largeur "+monde.getLargeur());
            this.fichier.newLine();
            this.fichier.write("Hauteur "+monde.getHauteur());
            fichier.newLine();
            for (Creature p: monde.getListeCreatures()){
                
                switch(p.getClass().getSimpleName()){
                    case "Guerrier" -> {
                        this.fichier.write("Guerrier "+((Personnage)p).getNom() +" ");
                        fichier.write(((Personnage)p).getPtVie() +" " ); 
                        fichier.write(((Personnage)p).getPtMana() +" ");
                        fichier.write(((Personnage)p).getPourcentageAtt() +" ");
                        fichier.write(((Personnage)p).getPourcentagePar() +" ");
                        fichier.write(((Personnage)p).getPourcentageMag() +" ");
                        fichier.write(((Personnage)p).getPourcentageResistMag() +" ");
                        fichier.write(((Personnage)p).getDegAtt() +" ");
                        fichier.write(((Personnage)p).getDegMag() +" ");
                        fichier.write(((Personnage)p).getDistAttMax() +" ");
                        fichier.write(((Personnage)p).getPtPar() +" ");
                        fichier.write(((Personnage)p).getPos().getX() +" ");
                        fichier.write(((Personnage)p).getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "Archer" -> {
                        this.fichier.write("Archer "+((Personnage)p).getNom() +" ");
                        fichier.write(((Personnage)p).getPtVie() +" " ); 
                        fichier.write(((Personnage)p).getPtMana() +" ");
                        fichier.write(((Personnage)p).getPourcentageAtt() +" ");
                        fichier.write(((Personnage)p).getPourcentagePar() +" ");
                        fichier.write(((Personnage)p).getPourcentageMag() +" ");
                        fichier.write(((Personnage)p).getPourcentageResistMag() +" ");
                        fichier.write(((Personnage)p).getDegAtt() +" ");
                        fichier.write(((Personnage)p).getDegMag() +" ");
                        fichier.write(((Personnage)p).getDistAttMax() +" ");
                        fichier.write(((Personnage)p).getPtPar() +" ");
                        fichier.write(((Archer)p).getNbFleches() +" ");
                        fichier.write(((Personnage)p).getPos().getX() +" ");
                        fichier.write(((Personnage)p).getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "Mage" -> {
                        this.fichier.write("Mage "+((Personnage)p).getNom() +" ");
                        fichier.write(((Personnage)p).getPtVie() +" " ); 
                        fichier.write(((Personnage)p).getPtMana() +" ");
                        fichier.write(((Personnage)p).getPourcentageAtt() +" ");
                        fichier.write(((Personnage)p).getPourcentagePar() +" ");
                        fichier.write(((Personnage)p).getPourcentageMag() +" ");
                        fichier.write(((Personnage)p).getPourcentageResistMag() +" ");
                        fichier.write(((Personnage)p).getDegAtt() +" ");
                        fichier.write(((Personnage)p).getDegMag() +" ");
                        fichier.write(((Personnage)p).getDistAttMax() +" ");
                        fichier.write(((Personnage)p).getPtPar() +" ");
                        fichier.write(((Personnage)p).getPos().getX() +" ");
                        fichier.write(((Personnage)p).getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "Paysan" -> {
                        this.fichier.write("Paysan "+((Personnage)p).getNom() +" ");
                        fichier.write(((Personnage)p).getPtVie() +" " ); 
                        fichier.write(((Personnage)p).getPtMana() +" ");
                        fichier.write(((Personnage)p).getPourcentageAtt() +" ");
                        fichier.write(((Personnage)p).getPourcentagePar() +" ");
                        fichier.write(((Personnage)p).getPourcentageMag() +" ");
                        fichier.write(((Personnage)p).getPourcentageResistMag() +" ");
                        fichier.write(((Personnage)p).getDegAtt() +" ");
                        fichier.write(((Personnage)p).getDegMag() +" ");
                        fichier.write(((Personnage)p).getDistAttMax() +" ");
                        fichier.write(((Personnage)p).getPtPar() +" ");
                        fichier.write(((Personnage)p).getPos().getX() +" ");
                        fichier.write(((Personnage)p).getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "Loup" -> {
                        this.fichier.write("Loup ");
                        fichier.write(((Monstre)p).getPtVie() +" " ); 
                        fichier.write(((Monstre)p).getPourcentageAtt() +" ");
                        fichier.write(((Monstre)p).getPourcentagePar() +" ");
                        fichier.write(((Monstre)p).getPourcentagePar() +" ");
                        fichier.write(((Monstre)p).getDegAtt() +" ");
                        fichier.write(((Monstre)p).getPtPar() +" ");
                        fichier.write(((Monstre)p).getPos().getX() +" ");
                        fichier.write(((Monstre)p).getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "Lapin" -> {
                        this.fichier.write("Lapin ");
                        fichier.write(((Monstre)p).getPtVie() +" " ); 
                        fichier.write(((Monstre)p).getPourcentageAtt() +" ");
                        fichier.write(((Monstre)p).getPourcentagePar() +" ");
                        fichier.write(((Monstre)p).getPourcentagePar() +" ");
                        fichier.write(((Monstre)p).getDegAtt() +" ");
                        fichier.write(((Monstre)p).getPtPar() +" ");
                        fichier.write(((Monstre)p).getPos().getX() +" ");
                        fichier.write(((Monstre)p).getPos().getY());
                        fichier.newLine();
                        break;
                    }
                }
            }
            
            for (Objet o: monde.getListeObjets()){
                switch(o.getClass().getSimpleName()){
                    case "Soin" -> {
                        this.fichier.write("Soin "+((Potion)o).getPuissance() + " "+ o.getPos().getX() + o.getPos().getY());
                        break;
                    }
                    case "Mana"->{
                        this.fichier.write("Mana "+((Potion)o).getPuissance() + " "+ o.getPos().getX() + o.getPos().getY());
                        break;
                    }
                    case "NuageToxique" -> {
                        this.fichier.write("NuageToxique ");
                        fichier.write(((NuageToxique)o).getPourcentageAtt()+" ");
                        fichier.write(((NuageToxique)o).getPourcentagePar()+" ");
                        fichier.write(((NuageToxique)o).getDegAtt()+" ");
                        fichier.write(((NuageToxique)o).getPtPar()+" ");
                        fichier.write(((NuageToxique)o).getPos().getX()+" ");
                        fichier.write(((NuageToxique)o).getPos().getY());
                        break;
                    }
                }         
            }
            
            for (Joueur j: monde.getListeJoueurs()){
                Personnage perso = j.getPerso();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(SauvegardePartie.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (fichier != null) {
                try {
                    // je force l'écriture dans le fichier
                    fichier.flush();
                    // puis je le ferme
                    fichier.close();
                } catch (IOException ex) {
                    Logger.getLogger(SauvegardePartie.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        }
        
        
        
    }
}
