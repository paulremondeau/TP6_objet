/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.File;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.largeur = 15;
        this.hauteur = 15;
    }

    /**
     * Implémente les tours de jeu. 
     * L'objectif est d'éliminer toutes els créatures sans se faire tuer.
     */
    public int tourDeJeu() {
        int indicator = 0; // Si égal à 1, met fin à la partie.
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

            try {
                Personnage perso = joueur.getPerso();
                
                System.out.println("\nC'est votre tour " + perso.getNom() + " !\nVous êtes en " + perso.getPos()+" avec une portée de "+perso.getDistAttMax()+" et "+perso.getPtVie()+" ptVie. Liste des creatures : ");
                visualiserPlateau(joueur);
                System.out.println("Que voulez-vous faire ? Combattre/Deplacer/Sauvegarder");
                String action;
                
                do {
                    action = keyboard.nextLine();
                } while (!action.equals("Combattre") && !action.equals("Deplacer") && !action.equals("Rien") && !action.equals("Sauvegarder"));
                
                switch (action) {
                    
                    case "Combattre":
                        System.out.println("\nIndiquez le numero de la cible à attaquer.\n");
                        int numeroCible = keyboard.nextInt();
                        Creature cible = this.listeCreatures.get(numeroCible);
                        if (perso instanceof Combattant) {
                            ((Combattant) perso).combattre(cible);
                        }
                        this.listeCreatures.removeIf(n -> n.isVivant() == false);
                        break;
                    case "Deplacer":
                        System.out.println("Veuillez vous déplacer.");
                        int dx;
                        int dy;
                        Point2D destination = new Point2D(perso.getPos());
                        do {
                            System.out.println("x ?");
                            dx = keyboard.nextInt();
                            System.out.println("y ?");
                            dy = keyboard.nextInt();
                            destination.setPosition(destination.getX() + dx, destination.getY() + dy);
                        } while (estLibre(destination));
                        
                        ((Creature)perso).deplacer(this,dx, dy);
                        for (Objet o : this.listeObjets) {
                            if (o.getPos().equals(perso.getPos())) {
                                o.utiliser(perso);
                                o.setUsed(true);
                            }
                        }
                        break;
                        
                    case "Sauvegarder":
                        indicator =1; // On met fin à la partie après la sauvegarde
                        String path;
                        System.out.println("Enregistrement de la partie...\n Souhaitez vous nommer la fichier de sauvegarde ?");
                        String answer;
                        File f;
                        do {
                            System.out.println("y/n");
                            answer = keyboard.nextLine();
                        } while (!answer.equals("y") && !answer.equals("n"));
                        
                        if (answer.equals("y")) {
                            path = keyboard.nextLine();
                            f = new File(path);
                            while (f.isFile()) {
                                
                                System.out.println("Attention le fichier existe déjà ! Voulez-vous changer le nom du fichier à enregistrer ?");
                                do {
                                    System.out.println("y/n");
                                    answer = keyboard.nextLine();
                                } while (!answer.equals("y") && !answer.equals("n"));
                                
                                if (answer.equals("y")) {
                                    path = keyboard.nextLine();
                                    f = new File(path);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            path = "Sauvegarde-WoE.txt";
                            f = new File(path);
                            int i = 0;
                            while (f.isFile()) { // On s'assure que le fichier n'existe pas
                                path = "Sauvegarde-WoE_" + valueOf(i) + ".txt";
                                i += 1;
                            }
                        }
                        SauvegardePartie save = new SauvegardePartie(path);
                        save.sauvegarderPartie(this);
                        break;
                        
                    case "Rien":
                        System.out.println("Vous passez votre tour.");
                        break;
                        
                }
            } catch (DeplacementIncorrectException ex) {
                
            }
        }

        Point2D nextPos = new Point2D();

        for (Creature c : this.listeCreatures) {

            for (Joueur joueur : this.listeJoueurs) {

                Personnage perso = joueur.getPerso();

                if (c instanceof Combattant) { // Les combatants peuvent joueur, les Lapin ne feront rien
                    if (c.getPos().distance(perso.getPos()) == 1) {
                        System.out.println(c.getClass().getSimpleName()+" vous attaque !!");
                        ((Combattant) c).combattre(joueur.getPerso());
                    } else {
                        outer:
                        for (int x = -1; x < 2; x++) {
                            for (int y = -1; y < 2; y++) {
                                nextPos.setPosition(c.getPos().getX() + x, y + c.getPos().getY());
                                if (nextPos.distance(perso.getPos()) < c.getPos().distance(perso.getPos())) {
                                    if (estLibre(nextPos)) {
                                        try {
                                            c.deplacer(this,x, y);
                                            System.out.println(c.getClass().getSimpleName()+" se déplace vers vous");
                                            break outer;
                                        } catch (DeplacementIncorrectException ex) {
                                            
                                        }
                                    }else{System.out.println("La case n'est pas libre...");}

                                }
                            }
                        }
                    }

                }
            }

        }
        
        this.listeObjets.removeIf(n -> n.isUsed() == true);
        System.out.println("Fin du tour de jeu !");
        if (listeCreatures.isEmpty()){
            System.out.println("Vous avez gagné ! ");
            indicator = 1;
        }
        if (!listeJoueurs.get(0).getPerso().isVivant()) {
            System.out.println("Vous avez succombé...");
            indicator = 1;
        }

        return indicator;
    }

    /**
     * Génère un monde aléatoirement. Ce monde contient un archer du nom de
     * Robin, un paysan du nom de Peon et deux lapins. Ces quatres éléments ne
     * peuvent pas se trouver à plus de cinq unités les uns des autres.
     */
    public void creeMondeAlea() {
        Loup unLoup;

        Point2D pos;
        int pV;
        int pA;
        int pP;
        int dA;
        int ptPara;
        int longueurListe = ThreadLocalRandom.current().nextInt(2, 4);
        System.out.println(longueurListe);
        for (int i = 0; i < longueurListe; i++) { // Ajout de loups
            pos = creerPoint2DAlea();
            unLoup = new Loup(pos);
            listeCreatures.add(unLoup);
        }
        listeObjets = new ArrayList();
        Soin poposoin;
        longueurListe = ThreadLocalRandom.current().nextInt(1, 3);
        for (int i = 0; i < longueurListe; i++) { // Ajout des potions de soins
            pos = creerPoint2DAlea();
            poposoin = new Soin(pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(poposoin);
        }

        Mana popomana;
        for (int i = 0; i < longueurListe; i++) { // Ajout des potions de mana
            pos = creerPoint2DAlea();
            popomana = new Mana(pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(popomana);
        }

        NuageToxique nuage;
        for (int i = 0; i < longueurListe; i++) { // Ajout des nuages toxiques
            pos = creerPoint2DAlea();
            nuage = new NuageToxique(pos);
            listeObjets.add(nuage);
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
     * Créer un point2d aléatoirement dans le monde sur une case disponible.
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
            estOccupee = !estLibre(pos);

        } while (estOccupee);

        return pos;
    }

    /**
     * Vérifie si une position est libre.
     *
     * @param pos La position en question
     * @return true si la position est libre
     */
    public boolean estLibre(Point2D pos) {
        boolean res = true;
        for (Creature o : listeCreatures) {
            if (o.getPos().equals(pos)) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * Renvoie une liste des créatures et leur positions de façon jolie et
     * lisible.
     */
    public void visualiserPlateau(Joueur j) {
        for (Creature o : listeCreatures) {
            System.out.println(o+" et à une distance de "+j.getPerso().getPos().distance(o.getPos())+".");
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

    public ArrayList<Creature> getListeCreatures() {
        return listeCreatures;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public ArrayList<Objet> getListeObjets() {
        return listeObjets;
    }

}
