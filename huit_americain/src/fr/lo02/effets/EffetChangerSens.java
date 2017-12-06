package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Cet effet inverse le sens de jeu.
 * @author Lionel EA
 *
 */

public class EffetChangerSens extends AbstractEffet  implements Effet {
	
	public EffetChangerSens(){
		this.nom="Effet changement de sens";
	}
	
	public EffetChangerSens(Partie partie) {
		super(partie);
	}
	
	public void appliquer() {
		System.out.println("CHANGEMENT DE SENS");
		this.partie.changerSens();
	}
}
