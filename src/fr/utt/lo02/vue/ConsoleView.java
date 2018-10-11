package fr.utt.lo02.vue;

import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.effets.AbstractEffet;
import fr.utt.lo02.effets.EffetNormal;
import fr.utt.lo02.huitamericain.Carte;
import fr.utt.lo02.huitamericain.Evenement;
import fr.utt.lo02.huitamericain.Joueur;
import fr.utt.lo02.huitamericain.JoueurReel;
import fr.utt.lo02.huitamericain.JoueurVirtuel;
import fr.utt.lo02.huitamericain.Partie;

/**
 * Cette classe correspond à la vue dans une architecture <strong>Modèle-Vue-Contrôleur</strong>. Il possèdera toutes les méthodes d'affichage.
 *
 */
public class ConsoleView implements Observer, View{
	
	private ConsoleInput consoleInput;
//	private Object lastInput; //Correspond à la dernière entrée de l'utilisateur dans la console.
	private Partie partie;
	
	/**
	 * Constructeur de la vue console. 
	 * @param partie
	 */
	public ConsoleView(Observable partie) {
		this.partie = (Partie) partie;
		
		//On ajoute cet observeur pour la partie et chaque joueur de la partie.
		partie.addObserver(this);

		for(Joueur j : this.partie.getJoueurs()) {
			j.addObserver(this);
		}
		
		consoleInput = new ConsoleInput(this);
		this.initialiserInput();
	}
	
	/**
	 * L'affichage doit être mis à jour à chaque fois qu'un changement est effectué.
	 * @param obs Référence vers la classe qui envoie la notification.
	 * @param arg Argument en paramètre de la notification. 
	 */
	public synchronized void update(Observable obs, Object arg) {
		//Traite les entrées
		if (obs instanceof ConsoleInput) {
			System.out.println("ENTREE : >" + arg);
			this.setLastInput((String) arg);
			
		}
		//Traite les affichages
		if (obs instanceof Partie | obs instanceof Joueur) {
			this.affichage(obs, (Evenement) arg);
		}
	}
	
	/**
	 * Lance le thread qui lit les entrées dans la console.
	 */
	public void initialiserInput() {
		this.consoleInput.demarrer();
	}
	
	/**
	 * Permet de définir lastInput en tant qu'entier ou en tant que chaîne de caractère.
	 * @param arg La valeur par laquelle on souhaite remplacer.
	 */
	public void setLastInput(Object arg) {
		try { 
			this.partie.getControleur().setLastInput(Integer.parseInt((String)arg));
		} catch (NumberFormatException e) {
			this.partie.getControleur().setLastInput(arg);
		}
	}
	
	/**
	 * Méthode qui gère les différents affichages en fonction de l'événement.
	 * @param objet Objet qui envoie la notification.
	 * @param evenement Evenement appelé.
	 */
	public void affichage(Observable objet, Evenement evenement) {
		switch(evenement) {
		case inputError:
			this.afficherErreur();
			break;
		case posableError:
			this.afficherErreurPosable();
			break;
		case piocher:
			this.afficherPiocher((Joueur) objet);
			if((Joueur) objet instanceof JoueurVirtuel) {
				this.afficherCartes((Joueur) objet);
			}
			break;
		case carteJouee:
			this.afficherCarteJouee((Joueur) objet);
			break;
		case debutTour:
			this.afficherDebutTour();
			if(partie.getJoueurActif() instanceof JoueurVirtuel) {
				this.afficherCartes(partie.getJoueurActif());
				this.afficherTalon();
			}
			if(partie.getJoueurActif() instanceof JoueurReel) {
				this.afficherInfoJoueurs();
				this.afficherCartes(partie.getJoueurActif());
				this.afficherTalon();
				this.demanderCarte();
			}
			break;
		case afficherCarte:
			this.afficherCartes((Joueur) objet);
			break;
		case vulnerable:
			this.afficherVulnerable((Joueur) objet);
			break;
		case carteSucces:
			this.afficherDireCarte((Joueur) objet, true);
			break;
		case carteEchec:
			this.afficherDireCarte((Joueur) objet, false);
			break;
		case contreCarteSucces:
			this.afficherDireContreCarte((Joueur) objet, true);
			break;
		case contreCarteEchec:
			this.afficherDireContreCarte((Joueur) objet, false);
			break;
		case fin:
			this.afficherGagnant();
			break;
		
		}
	}
	
	/**
	 * Affichage des cartes du joueur en paramètre.
	 * @param joueur 
	 */
	public void afficherCartes(Joueur joueur) {
		if (joueur instanceof JoueurReel) {
			int compteur=0;
			System.out.println("\nVotre main :");
			System.out.print("---------------");
			Carte c;
			for(int i = 0; i < joueur.getMainJoueur().nbCartes(); i++) {
				c = joueur.getMainJoueur().getCarte(i);
				compteur = i+1;
				
				if(c.posable(this.partie.getTalon())) {
					System.out.print("\n►");
				}else {
					System.out.print("\n▷");
				}
				
				System.out.print(compteur + ". " + c);
				if(!(c.getEffet() instanceof EffetNormal)) {
					System.out.print(" (" + ((AbstractEffet) c.getEffet()).getNom() + ")");
				}
			}
			System.out.println("\n---------------");
		}
		if(joueur instanceof JoueurVirtuel) {
			System.out.println("→ " + joueur + " possède " + joueur.getMainJoueur().nbCartes() + " carte(s).");
		}
	}
	
	/**
	 * Affiche le nombre de cartes que possède chaque ordinateur.
	 */
	public void afficherInfoJoueurs() {
		for(int i=1; i < this.partie.getJoueurs().length; i++) {
			System.out.println("→ " + this.partie.getJoueurs()[i] + " possède " + partie.getJoueurs()[i].getMainJoueur().nbCartes() + " carte(s).");
		}
	}
	
	/**
	 * Affiche les informations de début de tour.
	 */
	public void afficherDebutTour() {
		System.out.println("―――――――――――――――――――――――――");
		System.out.println("★★★  Au tour de " + partie.getJoueurActif() + " de jouer. ★★★");
	}
	
	/**
	 * Affiche la carte jouée par le joueur actif.
	 */
	public void afficherCarteJouee(Joueur joueur) {
		System.out.println(joueur + " joue le " + this.partie.getTalon().getHead());
	}
	
	/**
	 * Affiche quel joueur pioche.
	 * @param joueur
	 */
	public void afficherPiocher(Joueur joueur) {
		if(joueur instanceof JoueurReel) {
			System.out.println("Vous avez pioché le " + joueur.getMainJoueur().getCarte(joueur.getMainJoueur().nbCartes()-1));
		}
		if(joueur instanceof JoueurVirtuel) {
			System.out.println(joueur + " pioche une carte.");
		}
		
	}
	
	/**
	 * Demande le numéro de la carte du joueur dans sa main et retourne la carte associée.
	 * On ne fait que renvoyer la valeur, le contrôleur se chargera de gerer la valeur entrée.
	 */
	public void demanderCarte() {
		System.out.println("Indiquer la carte que vous voulez poser : ");
	}
	
	/**
	 * Affiche que la valeur entrée ne correspond pas à ce qui est attendu.
	 */
	public void afficherErreur() {
		System.out.println("ENTREE INCORRECTE");
	}
	
	/**
	 * Affiche que la carte n'est pas posable.
	 */
	public void afficherErreurPosable() {
		System.out.println("Cette carte n'est pas posable");
	}
	
	/**
	 * Demande à l'utilisateur la variante voulue.
	 */
	public void demanderVariante() {
		System.out.println("Quelle variante variante voulez-vous choisir?");
	}
	
	/**
	 * Affiche la tête du talon.
	 */
	public void afficherTalon() {
		System.out.println("\n>>> La tête du talon est un " + this.partie.getTalon().getHead() + " <<<");
	}
	
	/**
	 * Affiche le gagnant de la partie.
	 */
	public void afficherGagnant() {
		System.out.println(" ----> LE GAGNANT DE LA PARTIE EST " + this.partie.getJoueurActif());
		System.out.println("La partie s'est finie après " + this.partie.getTour() + " tours.");
	}
	
	/**
	 * Affiche si le joueur en paramètre est vulnérable ou non.
	 * @param joueur
	 */
	public void afficherVulnerable(Joueur joueur) {
		if(joueur instanceof JoueurReel) {
			System.out.println("Vous êtes vulnérable !!");
		}
		if(joueur instanceof JoueurVirtuel) {
			System.out.println(joueur + " est vulnerable !");
		}
	}
	
	/**
	 * Affiche les informations correspondant à si l'appel "CARTE" a été un succès ou un echec.
	 * @param joueur Pour différencier le joueur virtuel du joueur réel.
	 * @param succes Boolean, pour afficher si c'est réussi ou raté.
	 */
	public void afficherDireCarte(Joueur joueur, boolean succes) {
		if(joueur instanceof JoueurReel) {
			if(succes) {
				System.out.println("Vous n'êtes plus vulnerable.");
			}
			else {
				System.out.println("Vous avez plus d'une carte...");
			}
		}
		if(joueur instanceof JoueurVirtuel) {
			if(succes) {
				System.out.println(joueur + " annonce CARTE. Il n'est plus vulnérable.");
			}
			else {
				System.out.println(joueur + " annonce CARTE mais s'est trompé...");
			}
		}
	}
	
	/**
	 * Affiche les informations correspondant à si l'appel "CONTRE CARTE" a été un succès ou un echec.
	 * @param joueur Pour différencier le joueur virtuel du joueur réel.
	 * @param succes Boolean, pour afficher si c'est réussi ou raté.
	 */
	public void afficherDireContreCarte(Joueur joueur, boolean succes) {
		if(joueur instanceof JoueurReel) {
			if(succes) {
				System.out.println("Bravo, vous avez contré des joueurs.");
			}
			else {
				System.out.println("Aucun joueur n'a qu'une carte...");
			}
		}
		if(joueur instanceof JoueurVirtuel) {
			if(succes) {
				System.out.println(joueur + " annonce CONTRE-CARTE et c'est efficace.");
			}
			else {
				System.out.println(joueur + " annonce CONTRE-CARTE mais s'est trompé.");
			}
		}
	}
	
	
}
