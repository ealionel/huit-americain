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
		this.joueur = new Joueur[regles.nbJoueurs];
		this.joueur[0] = new JoueurReel("Lionel");
		for (int i = 1; i < regles.getNbJoueurs(); i++) {
			this.joueur[i] = new JoueurVirtuel("Ordi " + i);
		}

		// Initialisation de la pioche.
		this.pioche = new Pioche(this.regles.getNbJeuxCartes(), this.regles.getEffetCartes(), this.regles.isJoker());
		this.pioche.melanger();
		this.distribuer();
	}

	/**
	 * Lance la partie.
	 */
	public void demarrerPartie() {
		this.tourSuivant(this.jouerTour(joueur[0]));
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

	/**
	 * Fait jouer le joueur mis en paramètre.
	 * @param joueurActuel
	 */
	public Joueur jouerTour(Joueur joueurActuel) {
		//On obtient une référence pour le joueur suivant au cas où on en a besoin.
		Joueur joueurSuivant = joueur[(joueurActuel.getId() + this.sensJeu) % this.regles.nbJoueurs];
		this.joueurActif = joueurActuel;
		
		if (joueurActuel instanceof JoueurVirtuel) {
			CartesJoueur mainJoueur = joueurActuel.getMainJoueur();
			boolean posee = false;
			
			//Peut être à redéfinir dans la stratégie du joueur virtuel.
			for (int i = 0; i < mainJoueur.nbCartes() & !posee; i++) {
				if ((mainJoueur.getCarte(i)).posable(talon)) {
					joueurActuel.poserCarte(i, talon); 
					this.talon.getHead().effet();
					posee = true;
				}
			}
			if(!posee) {
				joueurActuel.piocherCarte(this.pioche);
			}
		}

		if (joueurActuel instanceof JoueurReel) {
			String[] cmdAutorisees = {"piocher"};
			Object commande = null;
			
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
			
			if(commande instanceof String) { 		//On execute la commande si chaine de caractère
				controleur.executer((String) commande);
			}
			if(commande instanceof Integer) {		//On pioche si l'entrée est bien une valeur.
				joueurActuel.poserCarte((int) commande - 1, talon);
			}
		}
		
		return joueurSuivant;
	}

	/**
	 * Fait passer au tour suivant.
	 */
	public void tourSuivant(Joueur joueurSuivant) {
		this.tourSuivant(this.jouerTour(joueurSuivant));
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
