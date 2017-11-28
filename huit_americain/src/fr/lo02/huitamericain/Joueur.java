package fr.lo02.huitamericain;

public abstract class Joueur { //On la déclare abstraite parce que le joueur est soit réel soit virtuel toute facon
	
	protected CartesJoueur mainJoueur;
	protected int score;
	protected int id;
	protected static int instancesJoueurs=0;
	protected String nom;
	
	/**
	 * Constructeur du joueur.
	 * @param nom Nom du joueur
	 */
	public Joueur(String nom){
		id = instancesJoueurs; //Pour donner un identifiant à chaque joueur, le joueur 0 sera toujours le joueur réel
		instancesJoueurs++; 
		this.nom = nom;
		this.mainJoueur = new CartesJoueur();
	}
	
	/**
	 * Fait piocher le joueur. Cette méthode récupère la première carte de la pioche et la donne au joueur.
	 * @param pioche Prends en paramètre une pioche.
	 */
	public void piocherCarte(GroupeCartes pioche) {
		this.mainJoueur.ajouterCarte(pioche.retirerCarte());
	}
	
	public abstract void jouerCarte(int indice, Talon talon); //En fonction de si le joueur est réel ou virtuel, il fera des choses diff�rentes (attendre que le joueur joue, ou jouer automatiquement.)
	
	/**
	 * Annoncer "CARTE" ou "CONTRE CARTE", si l'annonce n'est pas valable, le joueur qui a parle pioche une carte.
	 * @param option : Dire "CARTE" ou dire "CONTRE-CARTE".
	 */
	public void parler(int option) {
		//A définir. Il faut que un autre thread check en permanence si cette méthode a été appelée ou non, si elle est appel�e on v�rifie
		//s'il est valable ou pas.
	}
	
	/**
	 * Affiche le nom du joueur.
	 */
	public String toString() {
		return this.nom;
	}
	
	/**
	 * Retourne le groupe de carte de la main du joueur.
	 * @return
	 */
	public CartesJoueur getMainJoueur() {
		return this.mainJoueur;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getId() {
		return this.id;
	}
	
}