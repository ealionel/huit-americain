package fr.lo02.effets;

import java.util.concurrent.ThreadLocalRandom;

import fr.lo02.exceptions.WrongInputException;
import fr.lo02.huitamericain.*;

/**
 * Cet effet change la couleur de la carte en tête du talon.
 * @author Lionel EA
 *
 */

public class EffetChangerCouleur extends AbstractEffet implements Effet{
	
	public EffetChangerCouleur(){
		this.nom="Change la couleur de la tête";
	}

	/**
	 * Change la couleur de la tête du talon.
	 */
	public void appliquer(Partie partie) {
		int choix = 0;
		
		 setChanged();
		 notifyObservers();
		
		if(partie.getJoueurActif() instanceof JoueurVirtuel) {
			choix = ThreadLocalRandom.current().nextInt(0, 4); //Choisit une valeur entre 0 et 3
		}
		if(partie.getJoueurActif() instanceof JoueurReel) {
			String[] motsAttendus = {"carreaux", "carreau", "pique", "coeur", "trefle", "trèfle"};
			String commande;
			while(true) {
				try {
					System.out.println("Changement de couleur. Choisir une couleur:");
					commande = (String) partie.getControleur().attendreValeur(motsAttendus);
					break;
				}catch(WrongInputException e){
					notifier("errorInput");
				}
			}
			
			switch(commande) {
			case "pique":
				choix = 0;
				break;
				
			case "trèfle":
			case "trefle":
				choix = 1;
				break;
				
			case "coeur":
				choix = 2;
				break;
				
			case "carreaux":
			case "carreau":
				choix = 3;
				break;
			
			}
		}
		
		System.out.println("Changement de couleur...");
		partie.getTalon().getHead().setCouleur(CouleurCarte.values()[choix]);	
	}
}
