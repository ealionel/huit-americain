package fr.lo02.effets;

import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.Partie;

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
		this.nom = "Fait piocher des cartes";
	}

	public EffetPiocherCarte(Partie partie, int nbCartes) {
		super(partie);
		this.nbCartes = nbCartes;
		this.partie.getJoueurSuivant().piocherCarte(this.partie.getPioche());
	}

	public void appliquer() {
		System.out.println("PIOCHER UNE CARTE");
	}
}
