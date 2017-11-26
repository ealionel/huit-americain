package fr.lo02.effets;

import fr.lo02.huitamericain.Joueur;

/**
 * Fait piocher à un joueur un certain nombre de cartes.
 * @author Lionel EA
 *
 */

public class EffetPiocherCarte extends AbstractEffet implements Effet {
	
	private Joueur joueur;
	private int nbCartes;

	public EffetPiocherCarte(){
		this.nom="Effet qui fait piocher des cartes";
	}
	
	public EffetPiocherCarte(int nbCartes, Joueur joueur){
		this.joueur = joueur;
		this.nbCartes = nbCartes;
	}
	
	public void appliquer() {
		System.out.println("PIOCHER UNE CARTE");
	}
}
