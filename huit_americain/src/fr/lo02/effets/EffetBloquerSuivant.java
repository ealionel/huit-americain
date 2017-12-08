package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

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
