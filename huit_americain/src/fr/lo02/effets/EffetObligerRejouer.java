package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Oblige le joueur à rejouer
 * @author Lionel EA
 *
 */

public class EffetObligerRejouer extends AbstractEffet implements Effet{
	
	public EffetObligerRejouer(){
		this.nom="Effet qui oblige à rejouer";
	}

	public EffetObligerRejouer(Partie partie) {
		super(partie);
	}
	
	public void appliquer() {
		System.out.println("EFFET REJOUER");
	}

}
