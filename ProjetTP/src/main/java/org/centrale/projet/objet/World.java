/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe du monde généré.
 *
 * @author bodet
 */
public class World {

    /**
     * Largeur du monde.
     */
    private int largeur;
    
    /**
     * Hauteur du monde.
     */
    private int hauteur;

    /**
     * Liste de créatures contrôlées automatiquement
     */
    public ArrayList<Creature> listeCreatures;

    /**
     * Liste de personnages joueurs
     */
    public ArrayList<Joueur> listeJoueurs;

    /**
     * Liste de potions
     */
    public ArrayList<Objet> listeObjets;

    public World() {

    }

    /**
     * Implémente les tours de jeu.
     */
    public void tourDeJeu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Un nouveau tour commence !");
        for (Joueur joueur : listeJoueurs) {

            System.out.println("C'est votre tour " + joueur.getPerso().getNom() + " !\nVous êtes en " + joueur.getPerso().getPos() + "Que voulez-vous faire ?");
            String action;
            do {
                action = keyboard.nextLine();
            } while (!action.equals("Combattre") && !action.equals("Déplacer") && !action.equals("Rien"));

            switch (action) {
                case "Combattre":
                    System.out.println("Indiquez le numero de la cible à attaquer.\nListe des creatures : " + Arrays.toString(listeCreatures.toArray()));
                    int numeroCible = keyboard.nextInt();
                    //joueur.getPerso().combattre(listeCreatures[numeroCible]);
                    break;

                case "Deplacer":
                    System.out.println("Veuillez vous déplacer.");
                    int dx = keyboard.nextInt();
                    int dy = keyboard.nextInt();

                    joueur.getPerso().deplacer(dx, dy);
                    break;

                case "Rien":
                    System.out.println("Vous passez votre tour.");
                    break;

            }
        }
        System.out.println("Fin du tour de jeu !");
    }

    /**
     * Génère un monde aléatoirement. Ce monde contient un archer du nom de
     * Robin, un paysan du nom de Peon et deux lapins. Ces quatres éléments ne
     * peuvent pas se trouver à plus de cinq unités les uns des autres.
     */
    public void creeMondeAlea() {
        this.largeur=100;
        this.hauteur=100;
        
        Random generateurAleatoire = new Random();
        Loup unLoup;
        listeCreatures = new ArrayList<>();
        Point2D pos;
        int pV;
        int pA;
        int pP;
        int dA;
        int ptPara;
        int longueurListe = ThreadLocalRandom.current().nextInt(50, 100);
        for (int i = 0; i < longueurListe; i++) {
            pV = ThreadLocalRandom.current().nextInt(5, 10);
            pA = ThreadLocalRandom.current().nextInt(1, 5);
            pP = ThreadLocalRandom.current().nextInt(0, 1);
            dA = ThreadLocalRandom.current().nextInt(1, 3);
            ptPara = ThreadLocalRandom.current().nextInt(5, 15);
            pos = creerPoint2DAlea();
            unLoup = new Loup(pV, pA, pP, dA, pos, ptPara, true);
            listeCreatures.add(unLoup);
            break;
        }

        listeObjets = new ArrayList();
        Soin poposoin;
        longueurListe = ThreadLocalRandom.current().nextInt(5, 10);
        for (int i = 0; i < longueurListe; i++) {
            pos = new Point2D(generateurAleatoire.nextInt(this.largeur), generateurAleatoire.nextInt(this.hauteur));
            poposoin = new Soin("Popo de soin", pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(poposoin);
        }

        Mana popomana;
        for (int i = 0; i < longueurListe; i++) {
            pos = new Point2D(generateurAleatoire.nextInt(this.largeur), generateurAleatoire.nextInt(this.hauteur));
            popomana = new Mana("Popo de mana", pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(popomana);
        }
    }

    /**
     * Crée un joueur aléatoirement dans le monde.
     * @return Donne le joueur qui a été créé.
     */
    public Joueur creeJoueurAlea() {

        Point2D pos = creerPoint2DAlea();
        
        Joueur joueur = new Joueur(pos);
        listeJoueurs.add(joueur);
        return joueur;
    }
    /**
     * Créer un point2d aléatoirement dans le monde.
     * @return 
     */
    public Point2D creerPoint2DAlea(){
        Random generateurAleatoire = new Random();
        int x;
        int y;
        Point2D pos;
        boolean estLibre;

        do {
            estLibre = true;
            x = generateurAleatoire.nextInt(this.largeur);
            y = generateurAleatoire.nextInt(this.hauteur);
            pos = new Point2D(x, y);
            for (Creature o : listeCreatures) {
                if (o.getPos().equals(pos)) {
                    estLibre = false;
                    break;
                }
            }
        } while (estLibre);
        
        return pos;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
