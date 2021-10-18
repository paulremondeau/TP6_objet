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
    
    public World chargerPartie() throws FileNotFoundException, IOException {
        World mondeCharge = new World();
        String ligne;
        try{
            this.fichier = new BufferedReader(new FileReader(this.source));
        }
        catch(FileNotFoundException e){
        }
        ligne = this.fichier.readLine();
        while (ligne != null) {
            System.out.println(ligne);
            ligne = this.fichier.readLine();
        }
        
        
        return mondeCharge;
    }
    
}
