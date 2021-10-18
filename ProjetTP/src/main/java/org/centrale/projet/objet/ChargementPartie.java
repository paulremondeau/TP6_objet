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
    
    /**
     * Constructeur du chargement de la partie
     * @param nomFichier Nom du fichier à charger
     */
    public ChargementPartie(String nomFichier){
        this.source=nomFichier;
    }
    
    /**
     * Méthode pour charger un monde depuis la source.
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
        // Parcours des lignes
        while (ligne != null) {
            ligneListe = ligneAListe(ligne); // Transforme la ligne en liste
            creerElementJeu(ligneListe, mondeCharge); // Ajoute l'élément de jeu
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
        // Switch sur le premier mot --> classe de l'element de jeu
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
                int pourcentageAtt = Integer.parseInt(ligneListe.get(1));
                int pourcentagePar = Integer.parseInt(ligneListe.get(2));
                int degAtt = Integer.parseInt(ligneListe.get(3));
                int ptPar = Integer.parseInt(ligneListe.get(4));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(5)), Integer.parseInt(ligneListe.get(6)));
                
                NuageToxique unNuageToxique = new NuageToxique(pos, pourcentageAtt, pourcentagePar, degAtt, ptPar);
                monde.listeObjets.add(unNuageToxique);
            }
            case "Guerrier" -> {
                String nom = ligneListe.get(1);
                int pV = Integer.parseInt(ligneListe.get(2));
                int pA = Integer.parseInt(ligneListe.get(4));
                int pP = Integer.parseInt(ligneListe.get(5));
                int rM = Integer.parseInt(ligneListe.get(7));
                int dA = Integer.parseInt(ligneListe.get(8));
                int ptPara = Integer.parseInt(ligneListe.get(11));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(12)), Integer.parseInt(ligneListe.get(13)));
                 
                Guerrier unGuerrier = new Guerrier(nom, pV, pA, pP, rM, dA, pos, ptPara, true);
                monde.listeCreatures.add(unGuerrier);
            }
            case "Mage" -> {
                String nom = ligneListe.get(1);
                int pV = Integer.parseInt(ligneListe.get(2));
                int ptM = Integer.parseInt(ligneListe.get(3));
                int pA = Integer.parseInt(ligneListe.get(4));
                int pP = Integer.parseInt(ligneListe.get(5));
                int pM = Integer.parseInt(ligneListe.get(6));
                int rM = Integer.parseInt(ligneListe.get(7));
                int dA = Integer.parseInt(ligneListe.get(8));
                int dM = Integer.parseInt(ligneListe.get(9));
                int distMax = Integer.parseInt(ligneListe.get(10));
                int ptPara = Integer.parseInt(ligneListe.get(11));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(12)), Integer.parseInt(ligneListe.get(13)));
                 
                Mage unmage = new Mage(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, pos, ptPara, true);
                monde.listeCreatures.add(unmage);
            }
            case "Voleur" -> {
                String nom = ligneListe.get(1);
                int pV = Integer.parseInt(ligneListe.get(2));
                int pA = Integer.parseInt(ligneListe.get(4));
                int pP = Integer.parseInt(ligneListe.get(5));
                int rM = Integer.parseInt(ligneListe.get(7));
                int dA = Integer.parseInt(ligneListe.get(8));
                int ptPara = Integer.parseInt(ligneListe.get(11));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(12)), Integer.parseInt(ligneListe.get(13)));
                 
                Voleur unVoleur = new Voleur(nom, pV, pA, pP, rM, dA, pos, ptPara, true);
                monde.listeCreatures.add(unVoleur);
            }
            case "Archer" -> {
                String nom = ligneListe.get(1);
                int pV = Integer.parseInt(ligneListe.get(2));
                int pA = Integer.parseInt(ligneListe.get(4));
                int pP = Integer.parseInt(ligneListe.get(5));
                int rM = Integer.parseInt(ligneListe.get(7));
                int dA = Integer.parseInt(ligneListe.get(8));
                int distMax = Integer.parseInt(ligneListe.get(10));
                int ptPara = Integer.parseInt(ligneListe.get(11));
                int nbF = Integer.parseInt(ligneListe.get(12));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(13)), Integer.parseInt(ligneListe.get(14)));
                
                Archer unArcher = new Archer(nom, pV, pA, pP, rM, dA, distMax, pos, nbF, ptPara, true);  
                monde.listeCreatures.add(unArcher);
            }
            case "Paysan" -> {
                String nom = ligneListe.get(1);
                int pV = Integer.parseInt(ligneListe.get(2));
                int pP = Integer.parseInt(ligneListe.get(5));
                int distMax = Integer.parseInt(ligneListe.get(10));
                int ptPara = Integer.parseInt(ligneListe.get(11));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(12)), Integer.parseInt(ligneListe.get(13)));
                 
                Paysan unPaysan = new Paysan(nom, pV, pP, distMax, pos, ptPara, true);
                monde.listeCreatures.add(unPaysan);
            }
            case "Joueur" -> {
                String nom = ligneListe.get(2);
                int pV = Integer.parseInt(ligneListe.get(3));
                int ptM = Integer.parseInt(ligneListe.get(4));
                int pA = Integer.parseInt(ligneListe.get(5));
                int pP = Integer.parseInt(ligneListe.get(6));
                int pM = Integer.parseInt(ligneListe.get(7));
                int rM = Integer.parseInt(ligneListe.get(8));
                int dA = Integer.parseInt(ligneListe.get(9));
                int dM = Integer.parseInt(ligneListe.get(10));
                int distMax = Integer.parseInt(ligneListe.get(11));
                int ptPara = Integer.parseInt(ligneListe.get(12));
                int nbF = Integer.parseInt(ligneListe.get(13));
                Point2D pos = new Point2D(Integer.parseInt(ligneListe.get(14)), Integer.parseInt(ligneListe.get(15)));
                Joueur leJoueur = null;
                
                // Switch sur la classe du personnage du joueur
                switch(ligneListe.get(1)){
                    case "Guerrier" -> {
                        Guerrier unGuerrier = new Guerrier(nom, pV, pA, pP, rM, dA, pos, ptPara, true);
                        leJoueur = new Joueur(unGuerrier);
                    }
                    case "Mage" -> {
                        Mage unMage = new Mage(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, pos, ptPara, true);
                        leJoueur = new Joueur(unMage);
                    }
                    case "Voleur" -> {
                        Voleur unVoleur = new Voleur(nom, pV, pA, pP, rM, dA, pos, ptPara, true);
                        leJoueur = new Joueur(unVoleur);
                    }
                    case "Archer" -> {
                        Archer unArcher = new Archer(nom, pV, pA, pP, rM, dA, distMax, pos, nbF, ptPara, true);
                        leJoueur = new Joueur(unArcher);
                    }
                    case "Paysan" -> {
                        Paysan unPaysan = new Paysan(nom, pV, pP, distMax, pos, ptPara, true);
                        leJoueur = new Joueur(unPaysan);
                    }
                }
                monde.listeJoueurs.add(leJoueur);
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
