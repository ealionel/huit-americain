package fr.utt.lo02.effets;

import fr.utt.lo02.huitamericain.Partie;

/**
 * Interface correspondant à l'interface "Strategy" dans le patron de conception "stratégie".
 * Elle est implémentée dans toutes les classes comportant les effets.
 * @author Lionel EA
 *
 */

public interface Effet {

	/**
	 * Méthode qui va appliquer l'effet à la partie.
	 * @param partie Partie sur lequel l'effet va être appliquée.
	 */
	public void appliquer(Partie partie);
}
