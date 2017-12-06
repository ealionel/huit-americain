package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Cet effet inverse le sens de jeu.
 * @author Lionel EA
 *
 */

public class EffetChangerSens extends AbstractEffet  implements Effet {
	
	public EffetChangerSens(){
		
	}
	
	
	public EffetChangerSens(Partie partie) {
		super(partie);
		this.nom="Changement de sens";
	}
	
	public void appliquer() {
		System.out.println("CHANGEMENT DE SENS");
		this.partie.changerSens();
	}
}