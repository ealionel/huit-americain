package fr.lo02.strategies;

import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.Talon;

/**
 * Dans la stratégie naïve, l'ordinateur choisit la première qu'il peut poser.
 * 
 * @author Lionel EA
 *
 */
public class StrategieNaive implements Strategie {

	/**
	 * Choisit la première carte posable * @param joueur Joueur qui joue.
	 * 
	 * @param joueur
	 *            Joueur qui joue.
	 * @param talon
	 *            Talon sur lequel le joueur va poser une carte.
	 * 
	 */
	public int choisirCarte(Joueur joueur, Talon talon) {
		for (int i = 0; i < joueur.getMainJoueur().nbCartes(); i++) {
			if (joueur.getMainJoueur().getCarte(i).posable(talon)) {
				return i;
			}
		}
		return -1; // -1 signifie que aucune carte n'est posable.
	}

}
