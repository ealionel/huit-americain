package fr.lo02.huitamericain;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Cette classe correspond à la vue dans une architecture <strong>Modèle-Vue-Contrôleur</strong>. Il possèdera toutes les méthodes d'affichage.
 *
 */
public class ConsoleView implements Observer, View{
	
	private ConsoleInput consoleInput;
	private String lastInput; //Correspond à la dernière entrée de l'utilisateur dans la console.
	
	public ConsoleView(Observable partie) {
		System.out.println("Initialisation de la vue.");
		
		partie.addObserver(this);
		consoleInput = new ConsoleInput(this);
	}
	
	/**
	 * L'affichage doit être mis à jour à chaque fois qu'un changement est effectué.
	 */
	public void update(Observable obs, Object arg) {
		if (obs instanceof ConsoleInput) {
			System.out.println("ENTREE : >" + arg);
			this.lastInput = (String) arg;
		}
		if (obs instanceof Partie) {
			//A COMPLETER
		}
	}
	
	public void initialiserInput() {
		this.consoleInput.demarrer();
	}
	
	/**
	 * Affichage des cartes du joueur.
	 * @param joueur
	 */
	public void afficherCartes(Joueur joueur) {
		int compteur=0;
		System.out.println("Vos cartes");
		System.out.println("-----------------");
		for(int i = 0; i < joueur.getMainJoueur().nbCartes(); i++) {
			compteur = i+1;
			System.out.println("> "+ compteur + ". " + joueur.getMainJoueur().getCarte(i));
		}
	}
	
	/**
	 * Affiche le nombre de cartes que possède chaque ordinateur.
	 * @param partie
	 */
	public void afficherInfoJoueurs(Partie partie) {
		for(int i=1; i < partie.getJoueurs().length; i++) {
			System.out.println(partie.getJoueurs()[i] + " possède " + partie.getJoueurs()[i].getMainJoueur().nbCartes() + " carte(s).");
		}
	}
	
	
	
	/**
	 * Affiche toutes les informations relatives au tour.
	 */
	public void afficherInfoTour() {
		
	}
	
	/**
	 * Demande le numéro de la carte du joueur dans sa main et retourne la carte associée.
	 * On ne fait que renvoyer la valeur, le contrôleur se chargera de gerer la valeur entrée.
	 * @return La carte associée à l'indice donné.
	 */
	public void demanderCarte() {
		System.out.println("Quelle carte voulez-vous poser? ");
		
//		return choix; //-1 Parce que la carte en position 0 est la carte 1 dans l'interface console.
	}
	
	/**
	 * Demande à l'utilisateur la variante voulue.
	 */
	public void demanderVariante() {
		System.out.println("Quelle variante variante voulez-vous choisir?");
	}
	
	public String getLastInput() {
		return this.lastInput;
	}
}
