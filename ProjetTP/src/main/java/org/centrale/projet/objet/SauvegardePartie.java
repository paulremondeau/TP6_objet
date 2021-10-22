/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe pour sauvegarder une partie.
 *
 * @author remondeau
 */
public class SauvegardePartie {

    private String source;
    private BufferedWriter fichier;

    /**
     * Constructeur de la classe, qui initialise le chemin du fichier et le
     * BufferedWriter.
     *
     * @param path Chemin du fichier de sauvegarde
     */
    public SauvegardePartie(String path) {
        this.source = path;
        try {
            fichier = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(source), StandardCharsets.UTF_8));

        } catch (IOException ex) {
            Logger.getLogger(SauvegardePartie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sauvegarde une partie dans un fichier text.
     *
     * @param monde Le monde que l'on sauvegarde
     */
    public void sauvegarderPartie(World monde) {

        try {
            this.fichier.write("Largeur " + monde.getLargeur());
            this.fichier.newLine();
            this.fichier.write("Hauteur " + monde.getHauteur());
            fichier.newLine();
            for (Creature p : monde.getListeCreatures()) {

                if (p instanceof Personnage) {
                    enregistrerPersonnage((Personnage) p);
                } else {
                    switch (p.getClass().getSimpleName()) {
                        case "Loup" -> {
                            this.fichier.write("Loup ");
                            fichier.write(((Monstre) p).getPtVie() + " ");
                            fichier.write(((Monstre) p).getPourcentageAtt() + " ");
                            fichier.write(((Monstre) p).getPourcentagePar() + " ");
                            fichier.write(((Monstre) p).getPourcentagePar() + " ");
                            fichier.write(((Monstre) p).getDegAtt() + " ");
                            fichier.write(((Monstre) p).getPtPar() + " ");
                            fichier.write(((Monstre) p).getPos().getX() + " ");
                            fichier.write(((Monstre) p).getPos().getY() + " ");
                            fichier.newLine();
                            break;
                        }
                        case "Lapin" -> {
                            this.fichier.write("Lapin ");
                            fichier.write(((Monstre) p).getPtVie() + " ");
                            fichier.write(((Monstre) p).getPourcentageAtt() + " ");
                            fichier.write(((Monstre) p).getPourcentagePar() + " ");
                            fichier.write(((Monstre) p).getPourcentagePar() + " ");
                            fichier.write(((Monstre) p).getDegAtt() + " ");
                            fichier.write(((Monstre) p).getPtPar() + " ");
                            fichier.write(((Monstre) p).getPos().getX() + " ");
                            fichier.write(((Monstre) p).getPos().getY() + " ");
                            fichier.newLine();
                            break;
                        }
                    }
                }

            }

            for (Objet o : monde.getListeObjets()) {
                switch (o.getClass().getSimpleName()) {
                    case "Soin" -> {
                        this.fichier.write("Soin " + ((Potion) o).getPuissance() + " " + o.getPos().getX() + " " + o.getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "Mana" -> {
                        this.fichier.write("Mana " + ((Potion) o).getPuissance() + " " + o.getPos().getX() + " " + o.getPos().getY());
                        fichier.newLine();
                        break;
                    }
                    case "NuageToxique" -> {
                        this.fichier.write("NuageToxique ");
                        fichier.write(((NuageToxique) o).getPourcentageAtt() + " ");
                        fichier.write(((NuageToxique) o).getPourcentagePar() + " ");
                        fichier.write(((NuageToxique) o).getDegAtt() + " ");
                        fichier.write(((NuageToxique) o).getPtPar() + " ");
                        fichier.write(((NuageToxique) o).getPos().getX() + " ");
                        fichier.write(((NuageToxique) o).getPos().getY() + " ");
                        fichier.newLine();
                        break;
                    }
                }
            }

            for (Joueur j : monde.getListeJoueurs()) {
                Personnage perso = j.getPerso();
                fichier.write("Joueur ");
                enregistrerPersonnage(perso);
            }

        } catch (IOException ex) {
            Logger.getLogger(SauvegardePartie.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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

    /**
     * Enregistre un personnage dans le fichier de sauvegarde
     *
     * @param p Le personnage que l'on enregistre.
     * @throws IOException
     */
    public void enregistrerPersonnage(Personnage p) throws IOException {
        switch (p.getClass().getSimpleName()) {
            case "Guerrier" -> {
                this.fichier.write("Guerrier " + p.getNom() + " ");
                fichier.write(p.getPtVie() + " ");
                fichier.write(p.getPtMana() + " ");
                fichier.write(p.getPourcentageAtt() + " ");
                fichier.write(p.getPourcentagePar() + " ");
                fichier.write(p.getPourcentageMag() + " ");
                fichier.write(p.getPourcentageResistMag() + " ");
                fichier.write(p.getDegAtt() + " ");
                fichier.write(p.getDegMag() + " ");
                fichier.write(p.getDistAttMax() + " ");
                fichier.write(p.getPtPar() + " ");
                fichier.write(p.getPos().getX() + " ");
                fichier.write(p.getPos().getY() + " ");
                fichier.newLine();
                break;
            }
            case "Archer" -> {
                this.fichier.write("Archer " + p.getNom() + " ");
                fichier.write(p.getPtVie() + " ");
                fichier.write(p.getPtMana() + " ");
                fichier.write(p.getPourcentageAtt() + " ");
                fichier.write(p.getPourcentagePar() + " ");
                fichier.write(p.getPourcentageMag() + " ");
                fichier.write(p.getPourcentageResistMag() + " ");
                fichier.write(p.getDegAtt() + " ");
                fichier.write(p.getDegMag() + " ");
                fichier.write(p.getDistAttMax() + " ");
                fichier.write(p.getPtPar() + " ");
                fichier.write(((Archer) p).getNbFleches() + " ");
                fichier.write(p.getPos().getX() + " ");
                fichier.write(p.getPos().getY() + " ");
                fichier.newLine();
                break;
            }
            case "Mage" -> {
                this.fichier.write("Mage " + p.getNom() + " ");
                fichier.write(p.getPtVie() + " ");
                fichier.write(p.getPtMana() + " ");
                fichier.write(p.getPourcentageAtt() + " ");
                fichier.write(p.getPourcentagePar() + " ");
                fichier.write(p.getPourcentageMag() + " ");
                fichier.write(p.getPourcentageResistMag() + " ");
                fichier.write(p.getDegAtt() + " ");
                fichier.write(p.getDegMag() + " ");
                fichier.write(p.getDistAttMax() + " ");
                fichier.write(p.getPtPar() + " ");
                fichier.write(p.getPos().getX() + " ");
                fichier.write(p.getPos().getY() + " ");
                fichier.newLine();
                break;
            }
            case "Paysan" -> {
                this.fichier.write("Paysan " + p.getNom() + " ");
                fichier.write(p.getPtVie() + " ");
                fichier.write(p.getPtMana() + " ");
                fichier.write(p.getPourcentageAtt() + " ");
                fichier.write(p.getPourcentagePar() + " ");
                fichier.write(p.getPourcentageMag() + " ");
                fichier.write(p.getPourcentageResistMag() + " ");
                fichier.write(p.getDegAtt() + " ");
                fichier.write(p.getDegMag() + " ");
                fichier.write(p.getDistAttMax() + " ");
                fichier.write(p.getPtPar() + " ");
                fichier.write(p.getPos().getX() + " ");
                fichier.write(p.getPos().getY() + " ");
                fichier.newLine();
                break;
            }
        }
    }
}
