package fr.lo02.effets;

/**
 * Oblige le joueur à rejouer
 * @author Lionel EA
 *
 */

public class EffetObligerRejouer extends AbstractEffet implements Effet{
	
	public EffetObligerRejouer(){
		this.nom="Effet qui oblige à rejouer";
	}

	public void appliquer() {
		System.out.println("EFFET REJOUER");
	}

}
