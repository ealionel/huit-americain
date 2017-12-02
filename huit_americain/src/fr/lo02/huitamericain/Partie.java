package fr.lo02.huitamericain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * Représente une partie. Possède des méthodes relatives à la partie.
 * 
 * @author Lionel EA
 *
 */
public class Partie extends Observable {

	private int tour;
	private boolean sensJeu; // true = vers la droite; false = vers la gauche
	private Regle regles;
	private Joueur[] joueur;
	private Pioche pioche;
	private Talon talon;
	private Controleur controleur;

	/**
	 * Initialisation de la partie en fonction d'une instance de règle.
	 * 
	 * @param regles
	 */
	public Partie(Regle regles) {
		// Initialisation de la partie
		this.regles = regles;
		this.talon = new Talon();
		this.sensJeu = true;
		this.tour = 0;

		// Initialisation des différents joueurs
		joueur = new Joueur[regles.nbJoueurs];
		joueur[0] = new JoueurReel("Moi");
		for (int i = 1; i < regles.getNbJoueurs(); i++) {
			joueur[i] = new JoueurVirtuel("Ordi " + i);
		}

		// Initialisation de la pioche.
		pioche = new Pioche(this.regles.getNbJeuxCartes(), this.regles.getEffetCartes(), this.regles.isJoker());
		pioche.melanger();

	}

	/**
	 * Lance la partie.
	 */
	public void demarrerPartie() {
		// A completer
	}

	/**
	 * Arrête la partie.
	 */
	public void arreterPartie() {
		// A completer
	}

	public void modifierRegles() {
		// Est-ce necessaire?
	}

	public void jouerTour(Joueur joueurActuel) {

		if (joueurActuel instanceof JoueurVirtuel) {
			CartesJoueur mainJoueur = joueurActuel.getMainJoueur();
			for (int i = 0; i < mainJoueur.nbCartes(); i++) {
				if ((mainJoueur.getCarte(i)).posable(talon)) {
					joueurActuel.poserCarte(i, talon); 
//					Implémentation sans effets A RAJOUTER
				}
			}
		}

		if (joueurActuel instanceof JoueurReel) {
//			A VOIR AVEC LIONEL POUR LE CHOIX DU JOUEUR
		}
	}

	/**
	 * Fait passer au tour suivant.
	 */
	public void tourSuivant() {
		// A COMPLETER
	}

	/**
	 * Distribue les cartes aux différents joueurs. Le nombre de cartes distribué
	 * est déterminé dans les règles.
	 */
	public void distribuer() {

		for (int i = 0; i < joueur.length; i++) {
			for (int j = 0; j < regles.getNbCartesDebut(); j++) {
				this.joueur[i].getMainJoueur().ajouterCarte(pioche.retirerCarte());
			}
		}

	}

	/**
	 * Change le sens du jeu.
	 */
	public void changerSens() {
		this.sensJeu = !this.sensJeu; // On inverse le booléen.
	}

	public Talon getTalon() {
		return talon;
	}

	public void setTalon(Talon talon) {
		this.talon = talon;
	}

	public Pioche getPioche() {
		return pioche;
	}

	public void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}

	/**
	 * Retourne les règles du jeu
	 * 
	 * @return L'objet règle
	 */
	public Regle getRegles() {
		return regles;
	}

	/**
	 * Retourne la liste des joueurs.
	 * 
	 * @return La liste de joueurs.
	 */
	public Joueur[] getJoueurs() {
		return joueur;
	}

	public void setRegles(Regle regles) {
		this.regles = regles;
	}

}
