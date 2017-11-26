package fr.lo02.huitamericain;

import java.util.ArrayList;

/**
 * Représente les cartes qui sont dans la main du joueur, hérite de la classe GroupeCartes.
 * @author Lionel EA
 *
 */
public class CartesJoueur extends GroupeCartes {

	/**
	 * Initialise la collection comme une instance de ArrayList.
	 * On choisit ArrayList car nous avons besoin de récupérer les cartes via leur position. 
	 */
	public CartesJoueur(){
		listeCartes = new ArrayList<Carte>();
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public Carte getCarte(int position) {
		return ((ArrayList<Carte>) this.listeCartes).get(position);
	}
	
	/**
	 * Redéfinition de ajouterCarte().
	 * La carte est placée en fin de collection car il s'agit d'un ArrayList, le placer en début de liste aurait pour conséquence d'avoir une complexité algorithmique O(n).
	 */
	public void ajouterCarte(Carte carte) {
		((ArrayList<Carte>)this.listeCartes).add(carte);
	}
	
	/**
	 * Retire une carte du groupe de cartes à un indice spécifié.
	 */
	public void retirerCarte(int position) {
		((ArrayList<Carte>) this.listeCartes).remove(position);
	}
	
	/**
	 * Redéfinition de la méthode retirerCarte() pour un ArrayList.
	 */
	public Carte retirerCarte() {
		Carte h = ((ArrayList<Carte>) this.listeCartes).get(0);
		((ArrayList<Carte>) this.listeCartes).remove(0);
		return h;
	}
	
}
