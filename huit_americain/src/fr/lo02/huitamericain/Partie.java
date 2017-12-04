package fr.lo02.huitamericain;

import java.util.Observable;

import fr.lo02.exceptions.WrongInputException;

/**
 * Représente une partie. Possède des méthodes relatives à la partie.
 * 
 * @author Lionel EA
 *
 */
public class Partie extends Observable {

	private int tour;
	private int sensJeu; // 1 = vers la droite; -1 = vers la gauche
	private Regle regles;
	private Joueur[] joueur;
	private Pioche pioche;
	private Talon talon;
	private Controleur controleur;
	private Joueur joueurActif;

	/**
	 * Initialisation de la partie en fonction d'une instance de règle.
	 * 
	 * @param regles
	 */
	public Partie(Regle regles) {
		// Initialisation de la partie
		this.regles = regles;
		this.talon = new Talon();
		this.sensJeu = 1;
		this.tour = 0;
		this.controleur = new Controleur(this);

		// Initialisation des différents joueurs
		joueur = new Joueur[regles.nbJoueurs];
		joueur[0] = new JoueurReel("Lionel");
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
		//On obtient une référence pour le joueur suivant au cas où on en a besoin.
		Joueur joueurSuivant = joueur[(joueurActuel.getId() + this.sensJeu) % this.regles.nbJoueurs];
		this.joueurActif = joueurActuel;
		
		if (joueurActuel instanceof JoueurVirtuel) {
			CartesJoueur mainJoueur = joueurActuel.getMainJoueur();

			for (int i = 0; i < mainJoueur.nbCartes(); i++) {
				if ((mainJoueur.getCarte(i)).posable(talon)) {
					joueurActuel.poserCarte(i, talon); 
					this.talon.getHead().effet();
				}
			}
		}

		if (joueurActuel instanceof JoueurReel) {
			String[] cmdAutorisees = {"piocher"};
			Object commande=null;
			
			setChanged();
			notifyObservers("debutTour");
			
			while(true) {
				try {
					commande = controleur.attendreValeur(cmdAutorisees, true, 1, joueurActuel.getMainJoueur().nbCartes());
					break;
				}catch(WrongInputException e) {
					setChanged();
					notifyObservers("inputError");
				}
			}
			
			if(commande instanceof String) {
				controleur.executer((String) commande);
				setChanged();
				notifyObservers("piocher");
			}
			if(commande instanceof Integer) {
				joueurActuel.poserCarte((int) commande - 1, talon);
				setChanged();
				notifyObservers("carteJouee");
			}
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
		this.sensJeu = this.sensJeu*(-1); // On inverse le signe.
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

	public Controleur getControleur() {
		return this.controleur;
	}
	
	public Joueur getJoueurActif() {
		return this.joueurActif;
	}
}
