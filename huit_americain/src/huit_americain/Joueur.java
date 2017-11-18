package huit_americain;

public abstract class Joueur { //On la d�clare abstraite parce que le joueur est soit r�el soit virtuel toute fa�on
	
	protected CartesJoueur mainJoueur;
	protected int score;
	protected int numeroJoueur;
	protected static int instancesJoueurs; 
	
	Joueur(){
		numeroJoueur = instancesJoueurs;
		instancesJoueurs++;
	}
	
	public void piocherCarte() {
		//D�finir corps de la m�thode. On retire une carte de la pioche, on la donne au joueur et on passe le tour
	}
	
	public abstract void jouerCarte(); //En fonction de si le joueur est r�el ou virtuel, il fera des choses diff�rentes (attendre que le joueur joue, ou jouer automatiquement.)
	
	/**
	 * Annoncer "CARTE" ou "CONTRE CARTE", si l'annonce n'est pas valable, le joueur qui a parl� pioche une carte.
	 * @param option : Dire "CARTE" ou dire "CONTRE-CARTE".
	 */
	public void parler(int option) {
		//A d�finir. Il faut que un autre thread check en permanence si cette m�thode a �t� appel�e ou non, si elle est appel�e on v�rifie
		//s'il est valable ou pas.
	}

}