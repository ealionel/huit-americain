package effets;

import huit_americain.Joueur;

/**
 * Fait piocher à un joueur un certain nombre de cartes.
 * @author Lionel EA
 *
 */

public class EffetPiocherCarte implements Effet {
	
	private Joueur joueur;
	private int nbCartes;
	
	public EffetPiocherCarte(){
		
	}
	
	public EffetPiocherCarte(int nbCartes, Joueur joueur){
		this.joueur = joueur;
		this.nbCartes = nbCartes;
	}
	
	public void appliquer() {
		System.out.println("PIOCHER UNE CARTE");
	}
}
