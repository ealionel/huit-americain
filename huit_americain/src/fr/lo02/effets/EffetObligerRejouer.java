package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Oblige le joueur à rejouer
 * @author Lionel EA
 *
 */

public class EffetObligerRejouer extends AbstractEffet implements Effet{
	
	public EffetObligerRejouer(){
		this.nom="Oblige à rejouer (piocher si plus que 1 carte en main)";
	}

//	public EffetObligerRejouer(Partie partie) {
//		super(partie);
//	}
	
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
