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
import static java.lang.Integer.parseInt;
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
                int pV = parseInt(ligneListe.get(1));
                
                Loup unLoup = new Loup(int pV, int pA, int pP, int dA, Point2D pos, int ptPara,boolean vivant);
                monde.listeCreatures.add(unLoup);
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
