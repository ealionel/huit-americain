package huit_americain;

import effets.*;

/**
 * Classe repr�sentant une carte du jeu.
 * @author Lionel
 *
 */
public class Carte {
	private ValeurCarte valeur;
	private CouleurCarte couleur;
	private Effet effet;
	/**
	 * Constructeur de la carte.
	 * @param pValeur : Valeur de la carte
	 * @param pCouleur : Couleur de la carte
	 */
	Carte(ValeurCarte pValeur, CouleurCarte pCouleur, Effet pEffet){
		this.valeur = pValeur;
		this.couleur = pCouleur;
		this.effet = pEffet;
	}
	
	/**
	 * Surcharge du constructeur. L'effet par d�faut est EffetNormal.
	 * @param pValeur
	 * @param pCouleur
	 */
	Carte(ValeurCarte pValeur, CouleurCarte pCouleur){
		this.valeur = pValeur;
		this.couleur = pCouleur;
		this.effet = new EffetNormal();
	}
	
	public void setEffet(Effet effet) {
		this.effet = effet;
	}
	
	public void effet() {
		effet.appliquer();
	}
	
	public ValeurCarte getValeur() {
		return this.valeur;
	}
	
	public CouleurCarte getCouleur() {
		return this.couleur;
	}
	
	/**
	 * @return Une cha�ne de caract�re pour identifier la carte sous la forme : <valeur> de <couleur>.
	 */
	public String toString() {
		return (this.valeur.getName() + " de " + this.couleur.getName());
	}
}
