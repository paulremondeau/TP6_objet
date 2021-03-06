/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.File;
import java.io.IOException;
import static java.lang.String.valueOf;
import java.util.ArrayList;
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
    private ArrayList<Creature> listeCreatures;

    /**
     * Liste de personnages joueurs
     */
    private ArrayList<Joueur> listeJoueurs;

    /**
     * Liste de potions
     */
    private ArrayList<Objet> listeObjets;

    public World() {
        this.listeCreatures = new ArrayList<>();
        this.listeJoueurs = new ArrayList<>();
        this.listeObjets = new ArrayList<>();
        this.largeur = 15;
        this.hauteur = 15;
    }

    /**
     * Implémente les tours de jeu. L'objectif est d'éliminer toutes les
     * créatures sans se faire tuer.
     *
     * @return si 1, met fin à la partie
     */
    @SuppressWarnings("empty-statement")
    public int tourDeJeu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nUn nouveau tour commence !"); // Début du tour

        for (Joueur joueur : this.listeJoueurs) { // On vérifie si les buff de nourritures sont terminés

            Personnage perso = joueur.getPerso();
            for (Nourriture nourriture : perso.getListeNourriture()) {
                int duree = nourriture.getDuree();
                if (duree <= 0) {
                    nourriture.fin(perso); 
                }
                nourriture.setDuree(nourriture.getDuree() - 1);
            }
            perso.getListeNourriture().removeIf(n -> n.getDuree() < 0); // retire toutes les nourritures dont la durée est écoulée
        }

        for (Joueur joueur : this.listeJoueurs) {
            int nCreatures = listeCreatures.size();
            try {
                Personnage perso = joueur.getPerso();
                System.out.println("\nC'est votre tour " + perso.getNom() + " !\nVous êtes en " + perso.getPos() + " avec une portée de " + perso.getDistAttMax() + " et " + perso.getPtVie() + " ptVie.");
                visualiserPlateau(joueur);
                String action;
                if (perso instanceof Combattant){
                    System.out.println("Que voulez-vous faire ? Combattre/Deplacer/Rien");
                    action = keyboard.nextLine();
                    while (!action.equals("Combattre") && !action.equals("Deplacer") && !action.equals("Rien")){
                        System.out.println("Veuillez entrer une action valide.");
                        action = keyboard.nextLine();
                    }
                }
                else{
                    System.out.println("Que voulez-vous faire ? Deplacer/Rien");
                    action = keyboard.nextLine();
                    while (!action.equals("Deplacer") && !action.equals("Rien")){
                        System.out.println("Veuillez entrer une action valide.");
                        action = keyboard.nextLine();
                    }
                }

                switch (action) {

                    case "Combattre":
                        System.out.println("\nIndiquez le numero de la cible à attaquer.");
                        int numeroCible = keyboard.nextInt();
                        while (numeroCible < 1 || numeroCible > nCreatures) {
                            System.out.println("Indiquer un numero de cible >=1 et <= au nombre de créatures.");
                            numeroCible = keyboard.nextInt();
                        }
                        keyboard.nextLine(); // Pour consommer le /n après le nextInt()
                        numeroCible -= 1;
                        Creature cible = this.listeCreatures.get(numeroCible);
                        if (perso instanceof Combattant) {
                            System.out.println("\n");
                            ((Combattant) perso).combattre(cible);
                        }
                        this.listeCreatures.removeIf(n -> n.isVivant() == false);
                        break;
                    case "Deplacer":
                        System.out.println("\nVeuillez vous déplacer.");
                        int dx;
                        int dy;
                        Point2D destination = new Point2D(perso.getPos());
                        System.out.println("x ?");
                        dx = keyboard.nextInt();
                        System.out.println("y ?");
                        dy = keyboard.nextInt();
                        keyboard.nextLine(); // Pour consommer le /n après le nextInt()
                        destination.setPosition(destination.getX() + dx, destination.getY() + dy);
                        
                        while (!estLibre(destination)){
                            destination = new Point2D(perso.getPos());
                            System.out.println("La destination est occupée. Ne rien faire ou réessayer. Rien/Deplacer");
                            action = keyboard.nextLine();
                            while (!action.equals("Deplacer") && !action.equals("Rien")){
                                System.out.println("Veuillez entrer une action valide.");
                                action = keyboard.nextLine();
                            }
                            if (action.equals("Deplacer")){
                                System.out.println("x ?");
                                dx = keyboard.nextInt();
                                System.out.println("y ?");
                                dy = keyboard.nextInt();
                                keyboard.nextLine(); // Pour consommer le /n après le nextInt()
                                destination.setPosition(destination.getX() + dx, destination.getY() + dy);
                            }
                            else{
                                System.out.println("\nVous passez votre tour.");
                                break;
                            }
                            
                        }

                        if (action.equals("Deplacer")){
                            ((Creature) perso).deplacer(this, dx, dy);
                            // Utilisation des objets
                            for (Objet o : this.listeObjets) {
                                if (o.getPos().equals(perso.getPos())) {
                                    o.utiliser(perso);
                                    o.setUsed(true);
                                }
                            }
                        }
                        
                        break;

                    case "Rien":
                        System.out.println("\nVous passez votre tour.");
                        break;

                }
            } catch (DeplacementIncorrectException ex) {
                // On pourrait faire un truc, à réfléchir
            }
        }

        Point2D nextPos = new Point2D();

        for (Creature c : this.listeCreatures) {
            if (c instanceof Combattant) {
                Personnage proche= listeJoueurs.get(0).getPerso();
                Personnage perso;
                for (Joueur joueur : this.listeJoueurs) {
                    perso = joueur.getPerso();
                    if (perso.getPos().distance(c.getPos()) < proche.getPos().distance(c.getPos())){
                        proche = perso;
                    }
                }
                // Les combattants peuvent jouer, les Lapin ne feront rien
                if (c.getPos().distance(proche.getPos()) == 1) {
                    System.out.println("    -" + c.getClass().getSimpleName() + " vous attaque !!");
                    ((Combattant) c).combattre(proche);
                } 
                // Si le personnage a une distance d'attaque supérieure à 1, il peut attaquer à distance
                else if (c instanceof Personnage && proche.getPos().distance(c.getPos())<=((Personnage) c).getDistAttMax()){
                    System.out.println("    -" + c.getClass().getSimpleName() + " vous attaque !!");
                    ((Combattant) c).combattre(proche);
                }
                else {
                    outer:
                    for (int x = -1; x < 2; x++) {
                        for (int y = -1; y < 2; y++) {
                            nextPos.setPosition(c.getPos().getX() + x, y + c.getPos().getY());
                            if (nextPos.distance(proche.getPos()) < c.getPos().distance(proche.getPos())) {
                                if (estLibre(nextPos)) {
                                    try {
                                        c.deplacer(this, x, y);
                                        System.out.println("    -" + c.getClass().getSimpleName() + " se déplace vers vous");
                                        break outer;
                                    } catch (DeplacementIncorrectException ex) {
                                        //System.out.println("Erreur soulevée.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (Objet o : this.listeObjets){
            if (o instanceof Combattant){
                for (Joueur joueur : this.listeJoueurs) {
                    Personnage perso = joueur.getPerso();
                    if (o.getPos().distance(perso.getPos()) == 1 || o.getPos().equals(perso.getPos())){
                        System.out.println("    -" + o.getClass().getSimpleName() + " vous attaque !!");
                        ((Combattant) o).combattre(joueur.getPerso());
                    }
                }
                for (Creature c : this.listeCreatures){
                    if (o.getPos().distance(c.getPos()) == 1 || o.getPos().equals(c.getPos())){
                        System.out.println("    -" + o.getClass().getSimpleName() + " attaque "+c.getClass().getSimpleName()+" !!");
                        ((Combattant) o).combattre(c);
                    }
                }
                this.listeCreatures.removeIf(n -> n.isVivant() == false);
            }
        }
        
        this.listeObjets.removeIf(n -> n.isUsed() == true); // On retire les objets qui ont été utilisé.
        System.out.println("Fin du tour de jeu !");
        if (listeCreatures.isEmpty()) {
            System.out.println("Vous avez gagné ! ");
            return 1;
        }
        if (!listeJoueurs.get(0).getPerso().isVivant()) {
            System.out.println("Vous avez succombé...");
            return 1;
        }
        System.out.println("\nSauvegarder la partie ? y/n");
        String answer;

        // Sauvegarde de la partie
        do {
            answer = keyboard.nextLine();
        } while (!answer.equals("y") && !answer.equals("n"));

        if (answer.equals("y")) {
            String path;
            System.out.println("Enregistrement de la partie... Souhaitez vous nommer la fichier de sauvegarde ? y/n");
            File file;
            do {
                answer = keyboard.nextLine();
            } while (!answer.equals("y") && !answer.equals("n"));

            if (answer.equals("y")) {
                System.out.println("Choisissez le nom de votre fichier. Veuillez finir par .txt !");
                path = keyboard.nextLine();
                file = new File(path);
                while (file.isFile() && (!".txt".equals(path.substring(path.length() - 4, path.length())))) {

                    System.out.println("Attention le fichier existe déjà ! Voulez-vous changer le nom du fichier à enregistrer ? y/n");
                    do {
                        answer = keyboard.nextLine();
                    } while (!answer.equals("y") && !answer.equals("n"));

                    if (answer.equals("y")) {
                        path = keyboard.nextLine();
                        file = new File(path);
                    } else {
                        break;
                    }
                }
            } else {
                path = "Sauvegarde-WoE.txt";
                file = new File(path);
                int i = 0;

                try {
                    while (!file.createNewFile()) { // On s'assure que le fichier n'existe pas
                        path = "Sauvegarde-WoE_" + valueOf(i) + ".txt";
                        file = new File(path);
                        i += 1;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            SauvegardePartie save = new SauvegardePartie(path);
            System.out.println("Partie sauvegardée dans le fichier " + path);
            save.sauvegarderPartie(this);
        }
        return 0;
    }

    /**
     * Génère un monde aléatoirement.
     * @param nJoueurs Nombre de joueurs dans le monde aléatoire.
     */
    public void creeMondeAlea(int nJoueurs) {
        System.out.println("\nGénération d'un monde aléatoire...");
        Loup unLoup;

        Point2D pos;
        int longueurListe = ThreadLocalRandom.current().nextInt(2, 6);
        for (int i = 0; i < longueurListe; i++) { // Ajout de loups
            pos = creerPoint2DAlea();
            unLoup = new Loup(pos);
            listeCreatures.add(unLoup);
        }
        listeObjets = new ArrayList();
        Soin poposoin;
        longueurListe = ThreadLocalRandom.current().nextInt(2, 4);
        for (int i = 0; i < longueurListe; i++) { // Ajout des potions de soins
            pos = creerPoint2DAlea();
            poposoin = new Soin(pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(poposoin);
        }

        Mana popomana;
        longueurListe = ThreadLocalRandom.current().nextInt(2, 4);
        for (int i = 0; i < longueurListe; i++) { // Ajout des potions de mana
            pos = creerPoint2DAlea();
            popomana = new Mana(pos, ThreadLocalRandom.current().nextInt(10, 15));
            listeObjets.add(popomana);
        }

        NuageToxique nuage;
        longueurListe = ThreadLocalRandom.current().nextInt(2, 4);
        for (int i = 0; i < longueurListe; i++) { // Ajout des nuages toxiques
            pos = creerPoint2DAlea();
            nuage = new NuageToxique(pos);
            listeObjets.add(nuage);
        }

        pos = creerPoint2DAlea();
        BonusPtPar bonusPtPar = new BonusPtPar(pos, 10, 3);

        pos = creerPoint2DAlea();
        MalusDegAtt malusDegAtt = new MalusDegAtt(pos, 10, 3);

        listeObjets.add(bonusPtPar);
        listeObjets.add(malusDegAtt);

        System.out.println("Monde aléatoire généré !");
        
        System.out.println("\nBienvenue dans l'interface de création des personnages.");
        if (nJoueurs == 1) {
            System.out.println("Création de votre personnage.");
            creeJoueurAlea();
        } else {
            for (int i = 0; i < nJoueurs; i++) {
                System.out.println("\nCréation du personnage numero " + (i + 1) + ".");
                creeJoueurAlea();
            }
        }

        String message = "Les aventures de ";
        for (Joueur j : listeJoueurs) {
            message += j.getPerso().getNom() + ", ";
        }
        message = message.substring(0, message.length() - 2);
        message += " vont pouvoir débuter !";
        System.out.println(message);
    }

    /**
     * Crée un joueur aléatoirement dans le monde.
     *
     */
    public void creeJoueurAlea() {
        Point2D pos = creerPoint2DAlea();
        Joueur joueur = new Joueur(pos);
        listeJoueurs.add(joueur);
    }

    /**
     * Créer un point2d aléatoirement dans le monde sur une case disponible.
     *
     * @return Le Point2D crée.
     */
    public Point2D creerPoint2DAlea() {
        Random generateurAleatoire = new Random();
        int x;
        int y;
        Point2D pos;
        boolean estOccupee;

        do {
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
        for (Joueur j : listeJoueurs) {
            if (j.getPerso().getPos().equals(pos)) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * Affiche de manière lisible le plateau de jeu
     *
     * @param j Joueur par rapport auquel on affiche les distances.
     */
    public void visualiserPlateau(Joueur j) {

        System.out.println("Liste des créatures :");
        visualiserPlateauCreature(j);

        System.out.println("Liste des objets :");
        visualiserPlateauObjets(j);
        
        if (listeJoueurs.size()>1){
            System.out.println("Liste des joueurs :");
            visualiserPlateauJoueurs(j);
        }
    }

    /**
     * Affiche de manière lisible la liste des objets du monde.
     *
     * @param j Joueur par rapport auquel on affiche les distances.
     */
    public void visualiserPlateauObjets(Joueur j) {
        for (Objet o : listeObjets) {
            System.out.println("    - " + o);
        }
    }

    /**
     * Affiche de manière lisible la liste des créatures du monde.
     *
     * @param j Joueur par rapport auquel on affiche les distances.
     */
    public void visualiserPlateauCreature(Joueur j) {
        int i = 1;
        for (Creature c : listeCreatures) {
            System.out.println("    - " + i + ": " + c + " et à une distance de " + j.getPerso().getPos().distance(c.getPos()) + ".");
            i += 1;
        }
    }

    
    /**
     * Affiche de manière lisible la liste des autres joueurs du monde.
     * @param j Joueurs qui n'est pas affiché, dont c'est le tour.
     */
    public void visualiserPlateauJoueurs(Joueur j) {
        for (Joueur jou : listeJoueurs) {
            if (!jou.equals(j)){
                Personnage perso=jou.getPerso();
                System.out.println("    - "+ perso);
            }
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

/**
 * if (c instanceof Personnage) { if (c.getPos().distance(perso.getPos()) <=
 * ((Personnage)c).getDistAttMax()) {
 * System.out.println(c.getClass().getSimpleName() + " vous attaque !!");
 * ((Combattant) c).combattre(joueur.getPerso()); } }
 */
