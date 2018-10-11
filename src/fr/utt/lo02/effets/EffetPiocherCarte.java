package fr.utt.lo02.effets;

import fr.utt.lo02.huitamericain.Joueur;
import fr.utt.lo02.huitamericain.Partie;

/**
 * Fait piocher à un joueur un certain nombre de cartes.
 * 
 * @author Lionel EA
 *
 */

public class EffetPiocherCarte extends AbstractEffet implements Effet {

	private Joueur joueur;
	private int nbCartes;

	public EffetPiocherCarte() {
		this.nom = "Fait piocher des cartes au joueur suivant";
	}

	public EffetPiocherCarte(int nbCartes) {
		this.nbCartes = nbCartes;
		this.nom = "Fait piocher des cartes au joueur suivant";
	}

	public void appliquer(Partie partie) {
		System.out.println("Le joueur va piocher " + this.nbCartes + " carte(s).");
		for(int i = 0; i < this.nbCartes; i++) {
			partie.getJoueurSuivant().piocherCarte(partie.getPioche());
		}
		
	}
}
