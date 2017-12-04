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
	private Object lastInput; //Correspond à la dernière entrée de l'utilisateur dans la console.
	private Partie partie;
	
	public ConsoleView(Observable partie) {
		System.out.println("Initialisation de la vue.");
		
		this.partie = (Partie) partie;
		partie.addObserver(this);
		consoleInput = new ConsoleInput(this);
	}
	
	/**
	 * L'affichage doit être mis à jour à chaque fois qu'un changement est effectué.
	 */
	public synchronized void update(Observable obs, Object arg) {
		if (obs instanceof ConsoleInput) {
			System.out.println("ENTREE : >" + arg);
			this.setLastInput((String) arg);
			
			notify(); //Reveille le thread principal potentiellement en attente.
			
		}
		if (obs instanceof Partie) {
			this.affichage((String) arg);
		}
	}
	
	/**
	 * Lance le thread qui lit les entrées dans la console.
	 */
	public void initialiserInput() {
		this.consoleInput.demarrer();
	}
	
	public void affichage(String commande) {
		switch(commande) {
		case "inputError":
			this.afficherErreur();
			break;
		case "piocher":
			this.afficherPiocher();
			break;
		case "carteJouee":
			this.afficherCarteJouee();
			break;
		case "debutTour":
			this.afficherCartes(partie.getJoueurActif());
			this.demanderCarte();
			break;
		}
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
	 * Affiche la carte jouée par le joueur actif.
	 */
	public void afficherCarteJouee() {
		System.out.println(this.partie.getJoueurActif() + " joue le " + this.partie.getTalon().getHead());
	}
	
	/**
	 * Affiche quel joueur pioche.
	 */
	public void afficherPiocher() {
		if(partie.getJoueurActif() instanceof JoueurReel) {
			System.out.println("Vous avez pioché le " + partie.getJoueurActif().getMainJoueur().getCarte(partie.getJoueurActif().getMainJoueur().nbCartes()-1));
		}
		if(partie.getJoueurActif() instanceof JoueurVirtuel) {
			System.out.println(this.partie.getJoueurActif() + " pioche une carte.");
		}
	}
	
	
	
	/**
	 * Demande le numéro de la carte du joueur dans sa main et retourne la carte associée.
	 * On ne fait que renvoyer la valeur, le contrôleur se chargera de gerer la valeur entrée.
	 * @return La carte associée à l'indice donné.
	 */
	public void demanderCarte() {
		System.out.println("Indiquer la carte que vous voulez poser : ");
	}
	
	public void afficherErreur() {
		System.out.println("ENTREE INCORRECTE");
	}
	
	/**
	 * Demande à l'utilisateur la variante voulue.
	 */
	public void demanderVariante() {
		System.out.println("Quelle variante variante voulez-vous choisir?");
	}
	
	public void afficherCartePosee() {
		System.out.println();
	}
	
	/**
	 * Permet de définir lastInput en tant qu'entier ou en tant que chaîne de caractère.
	 * @param arg
	 */
	public void setLastInput(String arg) {
		if(arg.matches("-?\\d+(\\.\\d+)?")) {
			this.lastInput = Integer.parseInt(arg);
		}
		else {
			this.lastInput = arg;
		}
	}
	
	/**
	 * Renvoie la dernière entrée que l'utilisateur a fait dans la console.
	 * @return
	 */
	public Object getLastInput() {
		return this.lastInput;
	}
}
