package fr.utt.lo02.effets;

import fr.utt.lo02.huitamericain.Partie;

/**
 * Effet qui bloque le tour du joueur suivant.
 * @author Lionel EA
 *
 */
public class EffetBloquerSuivant extends AbstractEffet implements Effet {

	public EffetBloquerSuivant() {
		this.nom = "Bloque le tour du joueur suivant";
	}
	
	/**
	 * Fait passer le tour du joueur suivant. 
	 */
	public void appliquer(Partie partie) {
		partie.setJoueurSuivant(partie.getJoueurSuivantRelatif(partie.getJoueurSuivant()));
		System.out.println("Blocage d'un joueur...");
	}
}
