package fr.lo02.huitamericain;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import fr.lo02.exceptions.WrongInputException;
import fr.lo02.strategies.*;

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
	private Joueur joueurSuivant; //JOUEUR SUIVANT QUI EST REDEFINI A CHAQUE TOUR OU MANUELLEMENT.
	private Checker checker; //C'est ce qui va permettre de vérifier ce que l'utilisateur rentre dans la console à n'importe quel moment du jeu.
	
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

		// Initialisation des différents joueurs
		this.joueur = new Joueur[regles.nbJoueurs];
		this.joueur[0] = new JoueurReel("Lionel");
		for (int i = 1; i < regles.getNbJoueurs(); i++) {
			this.joueur[i] = new JoueurVirtuel("Ordi " + i, new StrategieNaive());
		}

		
	  
		
		// Initialisation de la pioche.
		this.pioche = new Pioche(this.regles.getNbJeuxCartes(), this.regles.getEffetCartes(), this.regles.isJoker());
		this.pioche.melanger();
		this.distribuer();
		
		//Initialisation du talon
		this.talon.ajouterCarte(this.pioche.retirerCarte()); // On pose une carte dans le talon depuis la pioche.
		
//		this.controleur = new Controleur(this, false);
		this.controleur = new Controleur(this, true);
		this.checker = new Checker(this);
		
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
		// Inutile en fait
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
		this.joueurActif = joueurActuel;
		this.setJoueurSuivant();
		
		boolean posee = false;
		
		notifier(Evenement.debutTour);
		
		if (joueurActuel instanceof JoueurVirtuel) {
			
			this.attendre(1000, 2000); //Attendre entre une et deux secondes
			int indiceCarte = ((JoueurVirtuel) joueurActuel).choisirCarte(this.talon);
			
			if(indiceCarte != -1) {
				joueurActuel.poserCarte(indiceCarte, this.talon);
				this.talon.getHead().appliquerEffet(this);
			}
			else {
				joueurActuel.piocherCarte(this.pioche);
			}
		}

		if (joueurActuel instanceof JoueurReel) {
			String[] cmdAutorisees = {"piocher", "p", "carte", "contre carte", "c", "cc", "main", "m"};
			Object commande = null;
			
			boolean sortir = false;
			
			while(!posee & !sortir) {
				try {
					
					commande = controleur.attendreValeur(cmdAutorisees, true, 1, joueurActuel.getMainJoueur().nbCartes());
					
					if (commande instanceof Integer) {
						if(joueurActuel.getMainJoueur().getCarte((int) commande - 1).posable(talon)) { 	
							joueurActuel.poserCarte((int) commande - 1, talon);
							this.talon.getHead().appliquerEffet(this);
							posee = true;
						}
						else{
							notifier(Evenement.posableError);
						}
					}
					if(commande instanceof String) { 		//On execute la commande si chaine de caractère
						if(((String) commande).equalsIgnoreCase("piocher") | ((String) commande).equalsIgnoreCase("p")) {
							controleur.executer((String) commande);
							sortir = true;
						}
					}
				}catch(WrongInputException e) {
					notifier(Evenement.inputError);
				}
			}
			
		}
		
		return this.joueurSuivant;
	}

	/**
	 * Fait passer au tour suivant. Correspond à la boucle principale.
	 * @param joueurSuivant Référence vers le joueur suivant.
	 */
	public void tourSuivant(Joueur joueurSuivant) {
		this.tour++;
		if(!this.estFini()) {
			this.tourSuivant(this.jouerTour(joueurSuivant));
		}
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
	 * Retourne si un joueur n'a plus de carte, c'est à dire que la partie est finie.
	 * @return
	 */
	public boolean estFini() {
		for(Joueur j : this.joueur) {
			if (j.getMainJoueur().estVide()) {
				notifier(Evenement.fin);
				return true;
			}
		}
		return false;
	}

	/**
	 * Change le sens du jeu.
	 */
	public void changerSens() {
		this.sensJeu = this.sensJeu*(-1); // On inverse le signe.
	}
	
	/**
	 * Envoie une notification aux observeurs.
	 * @param commande
	 */
	public void notifier(Evenement nomEvenement) {
		setChanged();
		notifyObservers(nomEvenement);
	}
	
	/**
	 * Endort le thread pendant un temps donné.
	 * @param ms Temps en millisecondes de le pause.
	 */
	public void attendre(int temps) {
		try {
			Thread.sleep(temps);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Surcharge. Endort le thread pour une durée aléatoire entre min et max
	 * @param min Durée minimale
	 * @param max Durée maximale
	 */
	public void attendre(int min, int max) {
		int tempsAleatoire = ThreadLocalRandom.current().nextInt(min, max + 1);
		this.attendre(tempsAleatoire);
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
		return this.joueur;
	}

	public void setRegles(Regle regles) {
		this.regles = regles;
	}

	/**
	 * Retourne le controleur de la partie.
	 * @return
	 */
	public Controleur getControleur() {
		return this.controleur;
	}
	
	public Joueur getJoueurActif() {
		return this.joueurActif;
	}
	
	public int getTour() {
		return this.tour;
	}
	
	/**
	 * Retourne la référence vers le joueur suivant du tour.
	 * @return
	 */
	public Joueur getJoueurSuivant() {
		return this.joueurSuivant;
	}
	
	/**
	 * Retourne le joueur suivant par rapport au joueur mis en paramètre de la méthode.
	 * @param joueurRelatif Le joueur référence
	 * @return Le joueur suivant par rapport au joueur référence.
	 */
	public Joueur getJoueurSuivantRelatif(Joueur joueurRelatif) {
		return this.joueur[Math.floorMod(joueurRelatif.getId() + this.sensJeu , this.regles.getNbJoueurs())];
	}
	
	/**
	 * Modifie la référence vers le joueur suivant.
	 * @param joueur Une référence vers un joueur.
	 */
	public void setJoueurSuivant(Joueur joueur) {
		this.joueurSuivant = joueur;
	}
	
	/**
	 * Surcharge. Met le joueur suivant normalement.
	 */
	public void setJoueurSuivant() {
		this.setJoueurSuivant(this.joueur[Math.floorMod(this.joueurActif.getId() + this.sensJeu , this.regles.nbJoueurs)]);
	}
	
}
