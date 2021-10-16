/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Interface des combattant.
 * @author remondeau
*/
public interface Combattant {
    /**
     * Méthode pour combattre une autre créature.
     * @param c Creature à combattre.
     */
    public void combattre(Creature c);

}
