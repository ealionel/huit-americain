package fr.lo02.effets;

/**
 * Effet standard d'une carte sans effet. Fait passer au tour suivant.
 * @author Lionel EA
 *
 */

public class EffetNormal extends AbstractEffet implements Effet{
	
	public EffetNormal(){
		this.nom="Effet normal";
	}
	
	public void appliquer() {
		System.out.println("EFFET NORMAL");
	}
}
