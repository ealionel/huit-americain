package fr.lo02.strategies;

import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.Talon;

/**
 * Dans la stratégie idiote, l'ordinateur choisit 
 * @author Lionel EA
 *
 */
public class StrategieNaive implements Strategie {

	/**
	 * Choisit la première carte posable
	 */
	public int choisirCarte(Joueur joueur, Talon talon) {
		for(int i = 0; i < joueur.getMainJoueur().nbCartes(); i++) {
			if (joueur.getMainJoueur().getCarte(i).posable(talon)) {
				return i;
			}
		}
		return -1; //-1 signifie que aucune carte n'est posable.
	}
	
}
