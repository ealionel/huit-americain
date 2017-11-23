package fr.lo02.effets;

/**
 * Les cartes possédant cet effet peuvent être posées n'importe quand. Fait passer le tour quoi qu'il en soit.
 * @author Lionel EA
 *
 */

public class EffetCarteUniversel implements Effet{
	public void appliquer() {
		System.out.println("EFFET UNIVERSEL");
	}
}
