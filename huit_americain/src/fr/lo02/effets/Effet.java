package fr.lo02.effets;

/**
 * Interface correspondant � l'interface "Strategy" dans le patron de conception "strat�gie".
 * Elle est impl�ment�e dans toutes les classes comportant les effets.
 * @author Lionel EA
 *
 */

public interface Effet {
	
	/**
	 * M�thode qui va appliquer l'effet � la partie.
	 */
	public void appliquer();
}
