package fr.lo02.huitamericain;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;

/**
 * Classe représentant un groupe de cartes.
 * La plupart des méthodes sont castées en ArrayDeque car c'est la collection utilisée par la Pioche et le Talon. La main du joueur utilise une ArrayList.
 * @author Lionel EA
 *
 */
public class GroupeCartes {
	
	/**
	 * Collection contenant les differentes cartes de type Carte du groupe de cartes.
	 */
	protected Collection<Carte> listeCartes;
	
	
	public Collection<Carte> getListeCartes() {
		return this.listeCartes;
	}
	
	/**
	 * AJoute une carte à la tête
	 * @param pCarte
	 */
	public void ajouterCarte(Carte pCarte) {
		((ArrayDeque<Carte>) this.listeCartes).addFirst(pCarte);
	}
	
	/**
	 * Retire la tête du groupe de cartes. Il peut renvoyer la carte. 
	 * Renvoie null si vide.
	 */
	public Carte retirerCarte() {
		return ((ArrayDeque<Carte>) this.listeCartes).poll();
	}
	
	/**
	 * Retourne la première carte du groupe de cartes. 
	 * Renvoie null si vide.
	 * @return
	 */
	public Carte getHead() {
		return (Carte) ((ArrayDeque) this.listeCartes).peekFirst();
	}
	
	/**
	 * Renvoie la taille du groupe de cartes.
	 * @return
	 */
	public int nbCartes() {
		return this.listeCartes.size();
	}
	
	/**
	 * Renvoie une chaîne de caractère, la liste de cartes du groupe.
	 */
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Contient " + this.nbCartes() + " cartes:\n\n");
		
		Iterator<Carte> iterateurCarte = this.listeCartes.iterator();
		
		while(iterateurCarte.hasNext()) {
			str.append(iterateurCarte.next().toString() + "\n");
		}
		
		return str.toString();
	}
}
