package fr.lo02.huitamericain;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant un groupe de cartes.
 * @author Lionel EA
 *
 */
public class GroupeCartes {
	
	/**
	 * Liste chaînée contenant les differentes cartes de type Carte du groupe de cartes.
	 */
	protected LinkedList<Carte> listeCartes = new LinkedList<Carte>();
	
	
	public LinkedList<Carte> getListeCartes() {
		return this.listeCartes;
	}
	
	public Carte getCarte(int index) {
		return listeCartes.get(index);
	}
	
	/**
	 * AJoute une carte à la tête
	 * @param pCarte
	 */
	public void ajouterCarte(Carte pCarte) {
		listeCartes.addFirst(pCarte);
	}
	
	/**
	 * Retire une carte du groupe de cartes à un indice spécifié.
	 */
	public void retirerCarte(int position) {
		listeCartes.remove(position);
	}
	
	/**
	 * Surcharge. Retire la tête du groupe de cartes.
	 */
	public void retirerCarte() {
		listeCartes.removeFirst();
	}
	
	/**
	 * Retourne la première carte du groupe de cartes.
	 * @return
	 */
	public Carte getHead() {
		return this.listeCartes.getFirst();
	}
	
}
