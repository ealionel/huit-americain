package fr.lo02.huitamericain;

import fr.lo02.effets.*;

/**
 * Classe qui contient les differentes r�gles du jeu.
 * @author Lionel EA
 *
 */

public class Regle {

	protected int nbJoueurs;
	protected int nbJeuxCartes;	//Nombre de jeux de cartes, peut être choisi en fonction du nombre de joueurs.
	protected int modePoints; 	//Pour choisir si le nombre de points est à compte positif ou négatif
	protected boolean joker; 	//Si on met un joker ou non dans le deque
	protected int nbCartesDebut;//Nombre distribué à chaque joueur au début d'une partie
	protected Effet[] effetCartes = new Effet[14]; //Liste qui va contenir la liste des effets pour chaque valeur de cartes, genre {new EffetNormal, new EffetChangerCouleur, new EffetNormal, ...}, effetCartes[0] correspond � l'effet de l'As, etc.

	/**
	 * Regles par défaut du jeu : version minimale.
	 */
	public Regle(int nbJoueurs, int nbJeuxCartes, int modePoints, boolean joker, Effet[] effetCartes) {

		this.nbJoueurs = nbJoueurs;
		this.nbJeuxCartes = nbJeuxCartes;
		this.modePoints = modePoints;
		this.joker = joker;
		this.nbCartesDebut = 8; //A modifier dynamiquement

		for(int i = 0; i < effetCartes.length; i++) {
			this.effetCartes[i] = effetCartes[i];
		}
	}

	/**
	 * Surcharge du constructeur. On peut séléctionner les règles en faisant appel à la méthode setVariante() via le constructeur.
	 * @param variante
	 */
	public Regle(int variante) {
		this.setVariante(variante);
	}
	
	/**
	 * Permet de séléctionner les variantes.
	 */
	public void setVariante(int numeroVariante) {
		switch(numeroVariante) {
			case 1:
				
		}
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public int getNbJeuxCartes() {
		return nbJeuxCartes;
	}

	public int getModePoints() {
		return modePoints;
	}

	public boolean isJoker() {
		return joker;
	}

	public int getNbCartesDebut() {
		return nbCartesDebut;
	}

	public Effet[] getEffetCartes() {
		return effetCartes;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public void setNbJeuxCartes(int nbJeuxCartes) {
		this.nbJeuxCartes = nbJeuxCartes;
	}

	public void setModePoints(int modePoints) {
		this.modePoints = modePoints;
	}

	public void setJoker(boolean hasJoker) {
		this.joker = hasJoker;
	}

	public void setNbCartesDebut(int nbCartesDebut) {
		this.nbCartesDebut = nbCartesDebut;
	}

	public void setEffetCartes(Effet[] effetCartes) {
		this.effetCartes = effetCartes;
	}
}