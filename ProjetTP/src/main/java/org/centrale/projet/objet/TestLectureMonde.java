/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.IOException;

/**
 *
 * @author bodet
 */
public class TestLectureMonde {
    public static void main(String[] args) throws IOException{
        ChargementPartie charge = new ChargementPartie("Sauvegarde-WoE.txt");
        World monde = charge.chargerPartie();
        monde.listeCreatures.get(0).affiche();
        monde.listeCreatures.get(1).affiche();
        monde.listeCreatures.get(2).affiche();
        monde.listeCreatures.get(3).affiche();
        monde.listeCreatures.get(4).affiche();
        monde.listeCreatures.get(5).affiche();
    }
}
