package fr.lo02.effets;

/**
 * Cet effet change la couleur de la carte en tête du talon.
 * @author Lionel EA
 *
 */

public class EffetChangerCouleur extends AbstractEffet implements Effet{
	
	public EffetChangerCouleur(){
		this.nom="Effet changement de couleur";
	}
	
	public void appliquer() {
		System.out.println("CHANGEMENT DE COULEUR");
	}
}
