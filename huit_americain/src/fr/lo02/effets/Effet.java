package fr.lo02.effets;

/**
 * Interface correspondant à l'interface "Strategy" dans le patron de conception "stratégie".
 * Elle est implémentée dans toutes les classes comportant les effets.
 * @author Lionel EA
 *
 */

public interface Effet {
	
	/**
	 * Méthode qui va appliquer l'effet à la partie.
	 */
	public void appliquer();
}
