package fr.lo02.huitamericain;
/**
 * Repr�sente une partie. Poss�de des m�thodes relatives � la partie.
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
	 * Initialisation de la partie en fonction d'une instance de r�gle.
	 * @param regles
	 */
	Partie(Regle regles){
		//Initialisation de la partie
		this.regles = regles;
		
		//Initialisation des diff�rents joueurs
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
	 * Arr�te la partie.
	 */
	public void arreterPartie() {
		//A completer
	}
	
	public void modifierRegles() {
		//Est-ce necessaire?
	}
	
	/**
	 * Fait passer au tour suivant.
	 */
	public void tourSuivant() {
		//A COMPLETER
	}
	
	/**
	 * Distribue les cartes aux diff�rents joueurs.
	 */
	public void distribuer() {
		
	}
	
	/**
	 * Change le sens du jeu.
	 */
	public void changerSens() {
		//A COMPLETER
	}
	
	
}
