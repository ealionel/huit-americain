package fr.lo02.strategieIA;

import fr.lo02.huitamericain.*;

/**
 * Cette interface correspond à "Strategie" dans le patron de conception Strategy. Il implémente les différentes méthodes qui peuvent potentiellement avoir des variations dans les stratégies.
 * @author Lionel EA
 *
 */
public interface StrategieIA {

	/**
	 * Détermine la façon dont l'IA chosit la carte qu'il veut poser sur le talon.
	 * @param joueur Le joueur concerné
	 * @param talon Le talon de la partie
	 * @return La position de la carte dans la main du joueur.
	 */
	public int choisirCarte(Joueur joueur, Talon talon);
	
}
