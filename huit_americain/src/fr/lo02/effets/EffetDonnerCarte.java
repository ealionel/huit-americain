package fr.lo02.effets;

import fr.lo02.huitamericain.Joueur;

/**
 *Cet effet donne un certain nombre de cartes à un certain joueur. 
 * @author Lionel EA
 *
 */
public class EffetDonnerCarte implements Effet{
	
	private Joueur cible;
	private int nbCartes;
	
	
	EffetDonnerCarte(){}
	
	EffetDonnerCarte(int nbCartes, Joueur joueur){
		
	}
	
	public void appliquer() {
		System.out.println("DONNE DES CARTES");
	}
}
