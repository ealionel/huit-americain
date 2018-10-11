package fr.utt.lo02.effets;

import fr.utt.lo02.huitamericain.Partie;

/**
 * Oblige le joueur à rejouer
 * @author Lionel EA
 *
 */

public class EffetObligerRejouer extends AbstractEffet implements Effet{
	
	public EffetObligerRejouer(){
		this.nom="Oblige à rejouer, vous piochez si une carte en main";
	}
	
	/**
	 * Le joueur est obligé de rejouer. S'il ne possède qu'une carte, il pioche une carte.
	 */
	public void appliquer(Partie partie) {
		if(partie.getJoueurActif().getMainJoueur().nbCartes() != 1) {
			partie.setJoueurSuivant(partie.getJoueurActif()); //On définit le joueur suivant comme étant le joueur actif.		
		}
		else {
			partie.getJoueurActif().piocherCarte(partie.getPioche());
		}
	}

}
