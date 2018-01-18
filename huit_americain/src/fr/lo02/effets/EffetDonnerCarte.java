package fr.lo02.effets;

import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.Partie;

/**
 * Cet effet donne un certain nombre de cartes à un certain joueur.
 * 
 * @author Lionel EA
 *
 */
public class EffetDonnerCarte extends AbstractEffet implements Effet {
	
	public EffetDonnerCarte(){
		this.nom="Donne des cartes";
	}

	private Joueur cible;
	private int nbCartes;

	public void appliquer(Partie partie) {
		System.out.println("DONNE DES CARTES");
	}
}
