package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Effet standard d'une carte sans effet. Fait passer au tour suivant.
 * @author Lionel EA
 *
 */

public class EffetNormal extends AbstractEffet implements Effet{
	
	public EffetNormal(){
		this.nom="Normal";
	}
	
	public EffetNormal(Partie partie) {
		super(partie);
	}
	
	public void appliquer() {
		System.out.println("EFFET NORMAL");
	}
}
