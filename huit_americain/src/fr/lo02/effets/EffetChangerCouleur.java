package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Cet effet change la couleur de la carte en tête du talon.
 * @author Lionel EA
 *
 */

public class EffetChangerCouleur extends AbstractEffet implements Effet{
	
	public EffetChangerCouleur(){
		this.nom="Changement de couleur";
	}
	
	public EffetChangerCouleur(Partie partie) {
		super(partie);
	}
	
	public void appliquer() {
		System.out.println("CHANGEMENT DE COULEUR");
	}
}
