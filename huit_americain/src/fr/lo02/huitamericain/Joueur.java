package fr.lo02.huitamericain;

public abstract class Joueur { //On la d�clare abstraite parce que le joueur est soit réel soit virtuel toute fa�on
	
	protected CartesJoueur mainJoueur;
	protected int score;
	protected int numeroJoueur;
	protected static int instancesJoueurs;
	protected String nom;
	
	Joueur(String nom){
		numeroJoueur = instancesJoueurs; //Pour donner un identifiant à chaque joueur, le joueur 0 sera toujours le joueur réel
		instancesJoueurs++; 
		this.nom = nom;
	}
	
	/**
	 * Fait piocher le joueur. Cette méthode récupère la première carte de la pioche et la donne au joueur.
	 * @param pioche
	 */
	public void piocherCarte(GroupeCartes pioche) {
		//D�finir corps de la m�thode. On retire une carte de la pioche, on la donne au joueur et on passe le tour
		this.mainJoueur.ajouterCarte(pioche.getHead());
		pioche.retirerCarte();
	}
	
	/**
	 * Pose la carte sur le talon.
	 * @param indiceCarte
	 * @param talon
	 */
	public void jouerCarte(int indiceCarte, Talon talon) {
		talon.ajouterCarte(this.mainJoueur.getCarte(indiceCarte));
		this.mainJoueur.retirerCarte(indiceCarte);
	}
	
	public abstract void jouerCarte(); //En fonction de si le joueur est r�el ou virtuel, il fera des choses diff�rentes (attendre que le joueur joue, ou jouer automatiquement.)
	
	/**
	 * Annoncer "CARTE" ou "CONTRE CARTE", si l'annonce n'est pas valable, le joueur qui a parle pioche une carte.
	 * @param option : Dire "CARTE" ou dire "CONTRE-CARTE".
	 */
	public void parler(int option) {
		//A d�finir. Il faut que un autre thread check en permanence si cette m�thode a été appelée ou non, si elle est appel�e on v�rifie
		//s'il est valable ou pas.
	}
}