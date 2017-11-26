package fr.lo02.huitamericain;

import java.util.ArrayDeque;

import fr.lo02.effets.Effet;

public class Pioche extends GroupeCartes {

	/**
	 * Initialise la pioche en créant une carte de chaque couleur et de chaque
	 * valeur et les stocke dans l'ArrayList listeCartes
	 * 
	 * @param nbJeux
	 *            : Nombre de jeux voulu.
	 * @param listeEffets : liste des effets pour chaque valeur de cartes
	 * @param joker
	 *            : Booléen spécifiant si un jeu de carte contient une carte JOKER
	 *            ou pas.
	 * 
	 */
	public Pioche(int nbJeux, Effet[] listeEffets, boolean joker) {
		listeCartes = new ArrayDeque<Carte>();
		
		for (int iNbJeux = 0; iNbJeux < nbJeux; iNbJeux++) {
			for (int iVal = 0; iVal < 13; iVal++) {
				for (int iCouleur = 0; iCouleur < 4; iCouleur++) {
					this.listeCartes.add(
							new Carte(ValeurCarte.values()[iVal], CouleurCarte.values()[iCouleur], listeEffets[iVal]));
				}
			}

			if (joker) {
				this.listeCartes.add(new Carte(ValeurCarte.joker, CouleurCarte.neutre, listeEffets[13]));
			}
		}
	}

}
