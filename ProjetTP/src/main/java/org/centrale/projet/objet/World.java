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
     * Taille du monde.
     */
    private int TAILLE = 100;

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
        System.out.println("Un nouveau tour commence !"); // Début du tour
        
        for (Joueur joueur : listeJoueurs) { // On vérifie si les buff de nourritures sont terminés

                Personnage perso = joueur.getPerso();
                for (Nourriture nourriture : perso.getListeNourriture()){
                    int duree = nourriture.getDuree();
                    
                    if (duree<0){
                        nourriture.fin(perso);
                    } else {
                        nourriture.setDuree(nourriture.getDuree()-1);
                    }
                }

                perso.getListeNourriture().removeIf(n -> n.getDuree()<0); // retire toutes les nourritures dont la durée est écoulée
            }
        
        for (Joueur joueur : listeJoueurs) {
            
            Personnage perso = joueur.getPerso();
            Class classePerso = joueur.getPerso().getClass();
            

            System.out.println("C'est votre tour " + joueur.getPerso().getNom() + " !\nVous êtes en " + joueur.getPerso().getPos() + "Que voulez-vous faire ?");
            String action;
            
            if (classePerso.getSimpleName()=="Paysan"){
                action = "Déplacer";
            } else{
                do {
                action = keyboard.nextLine();
                } while (!action.equals("Combattre") && !action.equals("Déplacer") && !action.equals("Rien"));
            }   
           
            

            switch (action) {
                case "Combattre":
                    System.out.println("Indiquez le numero de la cible à attaquer.\nListe des creatures : " + Arrays.toString(listeCreatures.toArray()));
                    int numeroCible = keyboard.nextInt();
                    //joueur.getPerso().combattre(listeCreatures[numeroCible]);
                    break;

                case "Deplacer":
                    System.out.println("Veuillez vous déplacer.");
                    int dx;
                    int dy;
                    Point2D destination = new Point2D();
                    do{
                        dx = keyboard.nextInt();
                        dy = keyboard.nextInt();
                        destination.setPosition(destination.getX()+dx,destination.getY()+dy);
                    } while(verifierPos(destination));
                    
                    joueur.getPerso().deplacer(dx, dy);
                    break;

                case "Rien":
                    System.out.println("Vous passez votre tour.");
                    break;

            }
        }
        
        for (Creature c : listeCreatures){
            
        }
        
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
            pos = new Point2D(generateurAleatoire.nextInt(TAILLE), generateurAleatoire.nextInt(TAILLE));
            poposoin = new Soin("Popo de soin", pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(poposoin);
        }

        Mana popomana;
        for (int i = 0; i < longueurListe; i++) {
            pos = new Point2D(generateurAleatoire.nextInt(TAILLE), generateurAleatoire.nextInt(TAILLE));
            popomana = new Mana("Popo de mana", pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(popomana);
        }
    }

    /**
     * Crée un joueur aléatoirement dans le monde.
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
            x = generateurAleatoire.nextInt(TAILLE);
            y = generateurAleatoire.nextInt(TAILLE);
            pos = new Point2D(x, y);
            estLibre = verifierPos(pos);
            
        } while (estLibre);
        
        return pos;
    }
    
    /**
     * Vérifie si une position est libre.
     * @param pos La position en question
     * @return true si la position n'est pas occupée
     */
    public boolean verifierPos(Point2D pos){
        boolean estLibre = true;
        for (Creature o : listeCreatures) {
                if (o.getPos().equals(pos)) {
                    estLibre = false;
                    break;
                }
        }
        return estLibre;
    }
}
