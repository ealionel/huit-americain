package fr.lo02.huitamericain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	 * Permet de retourner une carte de la main du joueur à une position donnée.
	 * @param position
	 * @return Retourne la carte à la positon donnée
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
	 * @param position Position de la carte à retirer
	 * @return Retourne la carte à la position donnée en paramètre.
	 */
	public Carte retirerCarte(int position) {
		Carte c = ((ArrayList<Carte>) this.listeCartes).get(position);
		((ArrayList<Carte>) this.listeCartes).remove(position);
		return c;
	}
	
	/**
	 * Redéfinition de la méthode retirerCarte() pour un ArrayList.
	 * @return Retourne la tête de la liste.
	 */
	public Carte retirerCarte() {
		Carte h = ((ArrayList<Carte>) this.listeCartes).get(0);
		((ArrayList<Carte>) this.listeCartes).remove(0);
		return h;
	}
	
	public void melanger() {
		Collections.shuffle((ArrayList<Carte>) this.listeCartes);
	}
	
}
