package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

/**
 * Les cartes possédant cet effet peuvent être posées n'importe quand. Fait passer le tour quoi qu'il en soit.
 * @author Lionel EA
 *
 */

public class EffetCarteUniversel extends AbstractEffet implements Effet{
	
	public EffetCarteUniversel() {
		this.nom="Universel";
	}

	public EffetCarteUniversel(Partie partie) {
		super(partie);
	}

	
	public void appliquer() {
		System.out.println("EFFET UNIVERSEL");
	}
}
