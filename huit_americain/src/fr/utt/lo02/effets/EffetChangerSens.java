package fr.utt.lo02.effets;

import fr.utt.lo02.huitamericain.Partie;

/**
 * Cet effet inverse le sens de jeu.
 * @author Lionel EA
 *
 */
public class EffetChangerSens extends AbstractEffet  implements Effet {
	
	public EffetChangerSens(){
		this.nom="Inverse le sens de jeu";
		
	}
	
	/**
	 * Inverse le sens de jeu.
	 */
	public void appliquer(Partie partie) {
		partie.changerSens();
		partie.setJoueurSuivant(); //Il faut modifier la référence vers le joueur suivant après avoir changé le sens.
		System.out.println("Inversion du sens de jeu...");
	}
}