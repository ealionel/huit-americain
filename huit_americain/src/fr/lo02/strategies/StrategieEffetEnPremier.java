package fr.lo02.strategies;

import fr.lo02.effets.EffetNormal;
import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.Talon;

/**
 * Stratégie qui chosit de jouer les cartes à effet en premier.
 * 
 * @author Lionel EA
 *
 */
public class StrategieEffetEnPremier implements Strategie {

	/**
	 * Choisit la première carte à effet posable. Sinon prend la première carte
	 * normale posable.
	 * 
	 * @param joueur
	 *            Joueur qui joue.
	 * @param talon
	 *            Talon sur lequel le joueur va poser une carte.
	 */
	public int choisirCarte(Joueur joueur, Talon talon) {
		int indiceCarteNormale = -1;
		for (int i = 0; i < joueur.getMainJoueur().nbCartes(); i++) {
			if (joueur.getMainJoueur().getCarte(i).posable(talon)) {
				if (!(joueur.getMainJoueur().getCarte(i).getEffet() instanceof EffetNormal)) {
					return i;
				} else if (indiceCarteNormale == -1) {
					indiceCarteNormale = i;
				}
			}
		}
		return indiceCarteNormale;
	}
}