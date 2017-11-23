package fr.lo02.huitamericain;

public class Pioche extends GroupeCartes {
	
	/**
	 * Initialise la pioche en créant une carte de chaque couleur et de chaque valeur et les stocke dans l'ArrayList listeCartes
	 * @param nbJeux : Nombre de jeux voulu.
	 * @param nbJoker : Booléen spécifiant si un jeu de carte contient une carte JOKER ou pas.
	 */
	Pioche(int nbJeux, boolean joker){
	
		for(int iNbJeux = 0; iNbJeux < nbJeux; iNbJeux++) {
			for (int iVal = 0; iVal < 13; iVal++) {
				for(int iCouleur = 0; iCouleur < 4; iCouleur++) {
					this.listeCartes.add(new Carte( ValeurCarte.values()[iVal], CouleurCarte.values()[iCouleur] ));
				}
			}

			if (joker) {
				this.listeCartes.add(new Carte(ValeurCarte.joker, CouleurCarte.neutre));
			}
		}
	}
	
	/**
	 * Surcharge du constructeur, par défaut on met un joker
	 * @param nbJeux
	 */
	Pioche(int nbJeux){
		this(nbJeux, true);
	}
	
	
	
}
