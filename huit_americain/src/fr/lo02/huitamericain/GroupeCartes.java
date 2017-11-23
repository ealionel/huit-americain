package fr.lo02.huitamericain;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe repr�sentant un groupe de cartes.
 * @author Lionel EA
 *
 */
public class GroupeCartes {
	
	/**
	 * Liste cha�n�e contenant les differentes cartes de type Carte du groupe de cartes.
	 */
	protected LinkedList<Carte> listeCartes = new LinkedList<Carte>();
	
	
	public LinkedList<Carte> getListeCartes() {
		return this.listeCartes;
	}
	
	public Carte getCarte(int index) {
		return listeCartes.get(index);
	}
	
	/**
	 * AJoute une carte � la t�te
	 * @param pCarte
	 */
	public void ajouterCarte(Carte pCarte) {
		listeCartes.addFirst(pCarte);
	}
	
	/**
	 * Retire une carte du groupe de cartes � un indice sp�cifi�.
	 */
	public void retirerCarte(int position) {
		listeCartes.remove(position);
	}
	
	/**
	 * Surcharge. Retire la t�te du groupe de cartes.
	 */
	public void retirerCarte() {
		listeCartes.removeFirst();
	}
	
	/**
	 * Retourne la premi�re carte du groupe de cartes.
	 * @return
	 */
	public Carte getHead() {
		return this.listeCartes.getFirst();
	}
	
}
