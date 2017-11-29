package fr.lo02.huitamericain;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Cet classe correspond à la vue dans une architecture <strong>Modèle-Vue-Contrôleur</strong>. Il possèdera toutes les méthodes d'affichage.
 *
 *
 */
public class ConsoleView implements Observer {
	
	public ConsoleView(Observable partie) {
		System.out.println("Initialisation de la vue.");
		partie.addObserver(this);
	}
	
	/**
	 * L'affichage doit être mis à jour à chaque fois qu'un changement est effectué.
	 */
	public void update(Observable obs, Object o) {
		
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
	public int demanderCarte() {
		int choix;
		System.out.println("Veuillez choisir le numéro de la carte associée :");
		
		Scanner sc = new Scanner(System.in);
		choix = sc.nextInt();
		sc.close();
		
		return choix-1; //-1 Parce que la carte en position 0 est la carte 1 dans l'interface console.
	}
	
}
