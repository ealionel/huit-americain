package fr.lo02.huitamericain;

import java.util.Observable;
import java.util.Observer;
import fr.lo02.effets.*;

/**
 * Cette classe correspond à la vue dans une architecture <strong>Modèle-Vue-Contrôleur</strong>. Il possèdera toutes les méthodes d'affichage.
 *
 */
public class ConsoleView implements Observer, View{
	
	private ConsoleInput consoleInput;
	private Object lastInput; //Correspond à la dernière entrée de l'utilisateur dans la console.
	private Partie partie;
	
	public ConsoleView(Observable partie) {
		this.partie = (Partie) partie;
		
		//On ajoute cet observeur pour la partie et chaque joueur de la partie.
		partie.addObserver(this);

		for(Joueur j : this.partie.getJoueurs()) {
			j.addObserver(this);
		}
		
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
		if (obs instanceof Partie | obs instanceof Joueur) {
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
		case "posableError":
			this.afficherErreurPosable();
			break;
		case "piocher":
			this.afficherPiocher();
			break;
		case "carteJouee":
			this.afficherCarteJouee();
			break;
		case "debutTour":
			this.afficherDebutTour();
			if(partie.getJoueurActif() instanceof JoueurReel) {
				this.afficherInfoJoueurs();
				this.afficherCartes(partie.getJoueurActif());
				this.afficherTalon();
				this.demanderCarte();
			}
			break;
		case "fin":
			this.afficherGagnant();
			break;
		
		}
	}
	
	/**
	 * Affichage des cartes du joueur.
	 * @param joueur
	 */
	public void afficherCartes(Joueur joueur) {
		int compteur=0;
		System.out.println("\nVos cartes");
		System.out.print("---------------");
		Carte c;
		for(int i = 0; i < joueur.getMainJoueur().nbCartes(); i++) {
			c = joueur.getMainJoueur().getCarte(i);
			compteur = i+1;
			System.out.print("\n► "+ compteur + ". " + c);
			if(!(c.getEffet() instanceof EffetNormal)) {
				System.out.print(" (" + ((AbstractEffet) c.getEffet()).getNom() + ")");
			}
		}
		System.out.println("\n---------------");
	}
	
	/**
	 * Affiche le nombre de cartes que possède chaque ordinateur.
	 */
	public void afficherInfoJoueurs() {
		for(int i=1; i < this.partie.getJoueurs().length; i++) {
			System.out.println("→ " + this.partie.getJoueurs()[i] + " possède " + partie.getJoueurs()[i].getMainJoueur().nbCartes() + " carte(s).");
		}
	}
	
	public void afficherDebutTour() {
		System.out.println("―――――――――――――――――――――――――");
		System.out.println("★★★  Au tour de " + partie.getJoueurActif() + " de jouer. ★★★");
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
	 */
	public void demanderCarte() {
		System.out.println("Indiquer la carte que vous voulez poser : ");
	}
	
	public void afficherErreur() {
		System.out.println("ENTREE INCORRECTE");
	}
	
	public void afficherErreurPosable() {
		System.out.println("Cette carte n'est pas posable");
	}
	
	/**
	 * Demande à l'utilisateur la variante voulue.
	 */
	public void demanderVariante() {
		System.out.println("Quelle variante variante voulez-vous choisir?");
	}
	
	public void afficherTalon() {
		System.out.println("\n>>> La tête du talon est un " + this.partie.getTalon().getHead() + " <<<");
	}
	
	public void afficherGagnant() {
		System.out.println(" ----> LE GAGNANT DE LA MANCHE EST " + this.partie.getJoueurActif());
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
