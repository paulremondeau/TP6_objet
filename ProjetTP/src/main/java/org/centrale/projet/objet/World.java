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
        this.listeCreatures = new ArrayList<>();
        this.listeJoueurs = new ArrayList<>();
        this.listeObjets = new ArrayList<>();
        this.largeur = 100;
        this.hauteur = 100;
    }

    /**
     * Implémente les tours de jeu.
     */
    public void tourDeJeu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Un nouveau tour commence !"); // Début du tour

        for (Joueur joueur : this.listeJoueurs) { // On vérifie si les buff de nourritures sont terminés

            Personnage perso = joueur.getPerso();
            for (Nourriture nourriture : perso.getListeNourriture()) {
                int duree = nourriture.getDuree();

                if (duree < 0) {
                    nourriture.fin(perso);
                } else {
                    nourriture.setDuree(nourriture.getDuree() - 1);
                }
            }

            perso.getListeNourriture().removeIf(n -> n.getDuree() < 0); // retire toutes les nourritures dont la durée est écoulée
        }

        for (Joueur joueur : this.listeJoueurs) {

            Personnage perso = joueur.getPerso();

            System.out.println("C'est votre tour " + perso.getNom() + " !\nVous êtes en " + perso.getPos() + "Que voulez-vous faire ?");
            String action;

            if (perso instanceof Combattant) {
                do {
                    action = keyboard.nextLine();
                } while (!action.equals("Combattre") && !action.equals("Deplacer") && !action.equals("Rien"));
            } else {
                action = "Deplacer";
            }

            switch (action) {

                case "Combattre":
                    System.out.println("Indiquez le numero de la cible à attaquer.\nListe des creatures : " );
                    visualiserPlateau();
                    int numeroCible = keyboard.nextInt();
                    Creature cible = this.listeCreatures.get(numeroCible);
                    ((Combattant) perso).combattre(cible);
                    break;
                case "Deplacer":
                    System.out.println("Veuillez vous déplacer.");
                    int dx;
                    int dy;
                    Point2D destination = new Point2D(perso.getPos());
                    do {
                        dx = keyboard.nextInt();
                        dy = keyboard.nextInt();
                        destination.setPosition(destination.getX() + dx, destination.getY() + dy);
                    } while (verifierPos(destination));

                    perso.deplacer(dx, dy);
                    for (Objet o : this.listeObjets) {
                        if (o.getPos().equals(perso.getPos())) {
                            o.utiliser(perso);
                            o.setUsed(true);
                        }
                    }
                    break;

                case "Rien":
                    System.out.println("Vous passez votre tour.");
                    break;

            }
        }

        Point2D nextPos = new Point2D();

        for (Creature c : this.listeCreatures) {

            for (Joueur joueur : this.listeJoueurs) {

                Personnage perso = joueur.getPerso();

                if (c instanceof Combattant) {
                    if (c.getPos().distance(perso.getPos()) == 1) {
                        ((Combattant) c).combattre(joueur.getPerso());
                    } else {
                        outer:
                        for (int x = -1; x < 2; x++) {
                            for (int y = -1; y < 2; y++) {
                                nextPos.setPosition(c.getPos().getX() + x, y + c.getPos().getY());
                                if (nextPos.distance(perso.getPos()) < c.getPos().distance(perso.getPos())) {
                                    if (verifierPos(nextPos)) {
                                        c.deplacer(x, y);
                                        break outer;
                                    }

                                }
                            }
                        }
                    }

                }
            }

        }
        this.listeObjets.removeIf(n -> n.isUsed() == true);
        System.out.println("Fin du tour de jeu !");
    }

    /**
     * Génère un monde aléatoirement. Ce monde contient un archer du nom de
     * Robin, un paysan du nom de Peon et deux lapins. Ces quatres éléments ne
     * peuvent pas se trouver à plus de cinq unités les uns des autres.
     */
    public void creeMondeAlea() {

        Random generateurAleatoire = new Random();
        Loup unLoup;

        Point2D pos;
        int pV;
        int pA;
        int pP;
        int dA;
        int ptPara;
        int longueurListe = ThreadLocalRandom.current().nextInt(50, 100);
        System.out.println(longueurListe);
        for (int i = 0; i < longueurListe; i++) {
            pos = creerPoint2DAlea();
            unLoup = new Loup(pos);
            listeCreatures.add(unLoup);
        }
        System.out.println("ok");
        listeObjets = new ArrayList();
        Soin poposoin;
        longueurListe = ThreadLocalRandom.current().nextInt(5, 10);
        for (int i = 0; i < longueurListe; i++) {
            pos = new Point2D(generateurAleatoire.nextInt(this.largeur), generateurAleatoire.nextInt(this.hauteur));
            poposoin = new Soin(pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(poposoin);
        }

        Mana popomana;
        for (int i = 0; i < longueurListe; i++) {
            pos = new Point2D(generateurAleatoire.nextInt(this.largeur), generateurAleatoire.nextInt(this.hauteur));
            popomana = new Mana(pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(popomana);
        }
    }

    /**
     * Crée un joueur aléatoirement dans le monde.
     *
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
     *
     * @return
     */
    public Point2D creerPoint2DAlea() {
        Random generateurAleatoire = new Random();
        int x;
        int y;
        Point2D pos;
        boolean estOccupee;

        do {
            estOccupee = false;
            x = generateurAleatoire.nextInt(this.largeur);
            y = generateurAleatoire.nextInt(this.hauteur);
            pos = new Point2D(x, y);
            estOccupee = verifierPos(pos);

        } while (estOccupee);

        return pos;
    }

    /**
     * Vérifie si une position est libre.
     *
     * @param pos La position en question
     * @return true si la position n'est pas occupée
     */
    public boolean verifierPos(Point2D pos) {
        boolean estOccupee = false;
        for (Creature o : listeCreatures) {
            if (o.getPos().equals(pos)) {
                estOccupee = true;
                break;
            }
        }
        return estOccupee;
    }
    
    public void visualiserPlateau(){
        for (Creature o : listeCreatures) {
            System.out.println(o);
        }
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
