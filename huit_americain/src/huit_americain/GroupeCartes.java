package huit_americain;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe repr�sentant un groupe de cartes.
 * @author Lionel EA
 *
 */
public class GroupeCartes {
	
	/**
	 * Liste des differentes cartes de type Carte du groupe de cartes.
	 */
	protected ArrayList<Carte> listeCartes = new ArrayList<Carte>();
	
	/**
	 * Ajoute une carte � la collection.
	 */
	public ArrayList<Carte> getListeCartes() {
		return this.listeCartes;
	}
	
	public Carte getCarte(int index) {
		return listeCartes.get(index);
	}
	
	public void ajouterCarte(Carte pCarte) {
		listeCartes.add(pCarte);
	}
	
	/**
	 * Retire une carte � la collection.
	 */
	public void retirerCarte(Carte pCarte) {
		
	}
}
