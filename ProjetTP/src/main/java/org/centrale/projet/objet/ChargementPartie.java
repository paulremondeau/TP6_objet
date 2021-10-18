/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Charge un fichier de sauvegarde et retourne un objet de type World.
 * @author bodet
 */
public class ChargementPartie {
    /**
     * Fichier à charger.
     */
    protected String source;
    protected BufferedReader fichier;
    
    
    public ChargementPartie(String nomFichier){
        this.source=nomFichier;
    }
    
    /**
     * 
     * @return Le monde qui a été créé.
     * @throws FileNotFoundException Le fichier n'existe pas.
     * @throws IOException Erreur de lecture.
     */
    public World chargerPartie() throws FileNotFoundException, IOException {
        World mondeCharge = new World();
        String ligne;
        ArrayList<String> ligneListe;
        
        try{
            this.fichier = new BufferedReader(new FileReader(this.source));
        }
        catch(FileNotFoundException e){
        }
        
        ligne = this.fichier.readLine();
        while (ligne != null) {
            ligneListe = ligneAListe(ligne);
            creerElementJeu(ligneListe, mondeCharge);
            ligne = this.fichier.readLine();
        }
        fichier.close();
        
        return mondeCharge;
    }
    
    /**
     * Traite une ligne lors de la lecture d'un fichier.
     * @param ligneListe Ligne traitée sous la forme d'une liste.
     * @param monde Monde à modifier.
     */
    public void creerElementJeu(ArrayList<String> ligneListe, World monde){
        
        switch(ligneListe.get(0)){
            case "Largeur" -> {
                int i = Integer.parseInt(ligneListe.get(1));
                monde.setLargeur(i);
            }
            case "Hauteur" -> {
                int i = Integer.parseInt(ligneListe.get(1));
                monde.setHauteur(i); 
            }
            case "Loup" -> {
                int pV = Integer.parseInt(ligneListe.get(1));
                int pA = Integer.parseInt(ligneListe.get(2));
                int pP = Integer.parseInt(ligneListe.get(3));
                int dA = Integer.parseInt(ligneListe.get(4));
                int ptPara = Integer.parseInt(ligneListe.get(5));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(6)), Integer.parseInt(ligneListe.get(7)));
                Loup unLoup = new Loup(pV, pA, pP, dA, pos, ptPara, true);
                monde.listeCreatures.add(unLoup);
            }
            case "Lapin" -> {
                int pV = Integer.parseInt(ligneListe.get(1));
                int pA = Integer.parseInt(ligneListe.get(2));
                int pP = Integer.parseInt(ligneListe.get(3));
                int dA = Integer.parseInt(ligneListe.get(4));
                int ptPara = Integer.parseInt(ligneListe.get(5));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(6)), Integer.parseInt(ligneListe.get(7)));
                Lapin unLapin = new Lapin(pV, pA, pP, dA, pos, ptPara, true);
                monde.listeCreatures.add(unLapin);
            }
            case "Soin" -> {
                int puissance = Integer.parseInt(ligneListe.get(1));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(2)), Integer.parseInt(ligneListe.get(3)));
                Soin unePotionSoin = new Soin(pos, puissance);
                monde.listeObjets.add(unePotionSoin);
            }
            case "Mana" -> {
                int puissance = Integer.parseInt(ligneListe.get(1));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(2)), Integer.parseInt(ligneListe.get(3)));
                Mana unePotionMana = new Mana(pos, puissance);
                monde.listeObjets.add(unePotionMana);
            }
            case "NuageToxique" -> {
                
            }
            
        }
        
        
    }
    
    /**
     * Transforme la ligne de strings en liste de strings.
     * @param ligne Ligne à transformer.
     * @return Liste transformée.
     */
    public ArrayList<String> ligneAListe(String ligne){
        String delimiteurs = " ,.;";        
        StringTokenizer tokenizer = new StringTokenizer(ligne, delimiteurs);
        ArrayList<String> liste = new ArrayList<>();
        String mot;
        
        while(tokenizer.hasMoreTokens()) {
            // nextToken() retourne la prochaine unite lexicale decoupee par les delimiteurs
            mot = tokenizer.nextToken();
            liste.add(mot);
        }
        return liste;
    }
    
}