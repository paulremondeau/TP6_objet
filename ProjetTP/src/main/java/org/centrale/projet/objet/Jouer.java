/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author remondeau
 */
public class Jouer {

    public static void main(String[] args) {

        // On commence par générer le monde.
        World monde = new World();
        Joueur joueur;
        System.out.println("Bienvenue dans le monde de World of ECN !\n");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@'~~~     ~~~`@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@@@@@@@'                     `@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@@@@'                           `@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@@'                               `@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@'                                   `@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@'                                     `@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@'                                       `@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@                                         @@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@'                                         `@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@                                           @@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@                                           @@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@                       n,                  @@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@                     _/ | _                @@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@                    /'  `'/                @@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@a                 <~    .'                a@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@                 .'    |                 @@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@a              _/      |                a@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@a           _/      `.`.              a@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@a     ____/ '   \\__ | |______       a@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@@a__/___/      /__\\ \\ \\     \\___.a@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@@/  (___.'\\_______)\\_|_|        \\@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                           "@@@@@@@@@@@@@@@@@@|\\________                       ~~~~~\\@@@@@@@@@@@@@@@@@@\n" +
                           "~~~\\@@@@@@@@@@@@@@||       |\\___________________________/|@/~~~~~~~~~~~\\@@@\n" +
                           "    |~~~~\\@@@@@@@/ |  |    | | by: ECN                 | ||\\____________|@@\n" +
                           "\n" +
                           "------------------------------------------------\n");
        System.out.println("Souhaitez-vous reprendre une partie en cours ? y/n");
        Scanner keyboard = new Scanner(System.in);
        String answer;
        File file;
        do {
            answer = keyboard.nextLine();
        } while (!answer.equals("y") && !answer.equals("n"));

        if (answer.equals("y")) {
            System.out.println("\nVeuillez entrer le nom du fichier de sauvegarde.");
            String path = keyboard.nextLine();
            file = new File(path);
            while (!file.isFile()){
                System.out.println("Le fichier n'existe pas. Veuillez entrer un nom de fichier.");
                path = keyboard.nextLine();
                file = new File(path);
            }

            ChargementPartie loaderPartie = new ChargementPartie(path);
            monde = loaderPartie.chargerPartie();
        } else {
            System.out.println("Combien de joueurs vont prendre part à l'aventure ?");
            int nJoueurs = 0;
            do {
                try {
                    nJoueurs = keyboard.nextInt();
                    if (nJoueurs < 1) {
                        System.out.println("Il faut au moins qu'une personne joue...");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Veuillez entrer un entier.");
                    keyboard.next();
                }
            } while (nJoueurs < 1);

            monde.creeMondeAlea(nJoueurs);
        }
        
        int statutJeu;
        
        do{
            statutJeu = monde.tourDeJeu();
        }while(statutJeu==0);
    }
}