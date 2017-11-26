package fr.lo02.huitamericain;
/**
 * Représente une partie. Possède des méthodes relatives à la partie.
 * @author Lionel EA
 *
 */
public class Partie {

	private int tour;
	private boolean sensJeu; //true = vers la droite; false = vers la gauche
	private Regle regles;
	private Joueur[] joueur;
	private Pioche pioche;
	
	
	/**
	 * Initialisation de la partie en fonction d'une instance de règle.
	 * @param regles
	 */
	public Partie(Regle regles){
		//Initialisation de la partie
		this.regles = regles;
		
		//Initialisation des différents joueurs
		joueur[0] = new JoueurReel("Moi");
		for(int i=1; i < regles.getNbJoueurs(); i++) {
			joueur[i] = new JoueurVirtuel("Ordi " + i);
		}
		
		//Initialisation de la pioche.
		pioche = new Pioche(regles.getNbJeuxCartes(), regles.getEffetCartes(), regles.isJoker());
		
	}
	
	/**
	 * Lance la partie.
	 */
	public void demarrerPartie() {
		//A completer
	}
	
	/**
	 * Arrête la partie.
	 */
	public void arreterPartie() {
		//A completer
	}
	
	public void modifierRegles() {
		//Est-ce necessaire?
	}
	
	public void jouerTour() {
		
	}
	
	/**
	 * Fait passer au tour suivant.
	 */
	public void tourSuivant() {
		//A COMPLETER
	}
	
	/**
	 * Distribue les cartes aux différents joueurs. Le nombre de cartes distribué est déterminé dans les règles.
	 */
	public void distribuer() {
		
		System.out.println("DISTRIBUTION DES CARTES..");
		
		for(int i = 0; i < joueur.length; i++) {
			for(int j = 0; j < regles.getNbCartesDebut(); j++) {
				this.joueur[i].getMainJoueur().ajouterCarte(pioche.retirerCarte());
			}
		}
		
	}
	
	/**
	 * Change le sens du jeu.
	 */
	public void changerSens() {
		//A COMPLETER
	}
	
	
}
