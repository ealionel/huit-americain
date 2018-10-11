package fr.utt.lo02.effets;

import fr.utt.lo02.huitamericain.Partie;

/**
 * Effet standard d'une carte sans effet. Fait passer au tour suivant.
 * @author Lionel EA
 *
 */

public class EffetNormal extends AbstractEffet implements Effet{
	
	public EffetNormal(){
		this.nom="Normal";
	}
	
	public void appliquer(Partie partie) {
	}
}
