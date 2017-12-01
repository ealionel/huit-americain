package fr.lo02.huitamericain;

import fr.lo02.effets.*;

/**
 * Classe représentant une carte du jeu.
 * 
 * @author Lionel
 *
 */
public class Carte {
	private ValeurCarte valeur;
	private CouleurCarte couleur;
	private Effet effet;

	/**
	 * Constructeur de la carte.
	 * 
	 * @param pValeur
	 *            Valeur de la carte
	 * @param pCouleur
	 *            Couleur de la carte
	 * @param pEffet
	 *            Effet de la carte, il est du type d'une classe qui implémente
	 *            l'interface Effet.
	 */
	public Carte(ValeurCarte pValeur, CouleurCarte pCouleur, Effet pEffet) {
		this.valeur = pValeur;
		this.couleur = pCouleur;
		this.effet = pEffet;

	}

	/**
	 * Surcharge du constructeur. L'effet par défaut est EffetNormal.
	 * 
	 * @param pValeur
	 * @param pCouleur
	 */
	public Carte(ValeurCarte pValeur, CouleurCarte pCouleur) {
		this.valeur = pValeur;
		this.couleur = pCouleur;
		this.effet = new EffetNormal();
	}

	public void setEffet(Effet effet) {
		this.effet = effet;
	}

	/**
	 * Applique l'effet de la carte.
	 */
	public void effet() {
		effet.appliquer();
	}

	public ValeurCarte getValeur() {
		return this.valeur;
	}

	public CouleurCarte getCouleur() {
		return this.couleur;
	}

	public boolean posable(Talon talon) {
		if (this.getValeur() == talon.getHead().getValeur() || this.getCouleur() == talon.getHead().getCouleur()
				|| this.getValeur() == ValeurCarte.joker) {
			return true;
		}
		return false;
		// A FAIRE : implémenter tous les effets possibles

	}

	/**
	 * @return Une chaîne de caractère pour identifier la carte sous la forme :
	 *         Valeur de Couleur.
	 */
	public String toString() {
		return (this.valeur.getName() + " de " + this.couleur.getName());
	}
}
