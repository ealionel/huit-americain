package fr.lo02.huitamericain;

import java.util.Observable;

public abstract class Joueur extends Observable{ //On la déclare abstraite parce que le joueur est soit réel soit virtuel toute facon
	
	protected CartesJoueur mainJoueur;
	protected int score;
	protected int id;
	protected static int instancesJoueurs=0;
	protected String nom;
	protected boolean vulnerable; //Indique si le joueur est vulnerable ou non à la commande "contre carte"
//	private JoueurChecker joueurChecker;
	
	/**
	 * Constructeur du joueur.
	 * @param nom Nom du joueur
	 */
	public Joueur(String nom){
		id = instancesJoueurs; //Pour donner un identifiant à chaque joueur, le joueur 0 sera toujours le joueur réel
		instancesJoueurs++; 
		this.nom = nom;
		this.mainJoueur = new CartesJoueur();
		
//		this.joueurChecker = JoueurChecker.getInstance();
//		this.joueurChecker.ajouterJoueur(this);
//		this.addObserver(joueurChecker);
	}
	
	/**
	 * Fait piocher le joueur. Cette méthode récupère la première carte de la pioche et la donne au joueur.
	 * @param pioche Prends en paramètre une pioche.
	 */
	public void piocherCarte(GroupeCartes pioche) {
		this.mainJoueur.ajouterCarte(pioche.retirerCarte());
		this.verifierVulnerable();
		notifier(Evenement.piocher);
	}
	
	public void poserCarte(int indice, Talon talon) { //En fonction de si le joueur est réel ou virtuel, il fera des choses diff�rentes (attendre que le joueur joue, ou jouer automatiquement.)
		talon.ajouterCarte(this.mainJoueur.retirerCarte(indice));
		this.verifierVulnerable();
		notifier(Evenement.carteJouee);
	}
		
	/**
	 * Annoncer "CARTE" ou "CONTRE CARTE", si l'annonce n'est pas valable, le joueur qui a parle pioche une carte.
	 * @param direCarte Dire "CARTE" (true) ou dire "CONTRE-CARTE" (false).
	 * @param partie Référence vers la partie.
	 */
	public void parler(boolean direCarte, Partie partie) {
		if(direCarte) {
			if(this.getMainJoueur().nbCartes() == 1) {
				notifier(Evenement.carteSucces);
				this.setVulnerable(false);
			}
			else {
				notifier(Evenement.carteEchec);
				this.piocherCarte(partie.getPioche());
			}
		}
		if(!direCarte) {
			boolean hasWorked = false;
			
			for(Joueur joueurCible : partie.getJoueurs()) {
				if(joueurCible.isVulnerable() & joueurCible != this) {
					notifier(Evenement.contreCarteSucces);
					joueurCible.piocherCarte(partie.getPioche());
					hasWorked = true;
				}
			}
			if(!hasWorked) {
				notifier(Evenement.contreCarteEchec);
				this.piocherCarte(partie.getPioche());
			}
		}
	}
	
	/**
	 * Affiche le nom du joueur.
	 */
	public String toString() {
		return this.nom;
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
	 * Retourne le groupe de carte de la main du joueur.
	 * @return La main du joueur
	 */
	public CartesJoueur getMainJoueur() {
		return this.mainJoueur;
	}
	
	/**
	 * Retourne le nom du joueur
	 * @return nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Retourne l'identifiant du joueur (0 pour le joueur réel)
	 * @return
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Vérifie si le joueur est vulnérable ou non en vérifiant son nombre de cartes.
	 */
	public void verifierVulnerable() {
		if (this.getMainJoueur().nbCartes() == 1) {
			this.setVulnerable(true);
			notifier(Evenement.vulnerable);
		} else {
			this.setVulnerable(false);
			notifier(Evenement.pasVulnerable);
		}
	}
	
	/**
	 * Retourne la vulnérabilité du joueur.
	 * Un joueur est vulnérable par une commande "contre-carte" si le joueur possède une carte et n'a pas annoncé "carte"
	 * @return La vulnérabilité du jouer (true/false)
	 */
	public boolean isVulnerable() {
		return this.vulnerable;
	}
	
	/**
	 * Modifie la vulnérabilité du joueur.
	 * @param vulnerable
	 */
	public void setVulnerable(boolean vulnerable) {
		this.vulnerable = vulnerable;
	}
	
}