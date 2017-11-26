package fr.lo02.effets;

/**
 * Cet effet inverse le sens de jeu.
 * @author Lionel EA
 *
 */

public class EffetChangerSens extends AbstractEffet  implements Effet {
	
	public EffetChangerSens(){
		this.nom="Effet changement de sens";
	}
	
	public void appliquer() {
		System.out.println("CHANGEMENT DE SENS");
	}
}
