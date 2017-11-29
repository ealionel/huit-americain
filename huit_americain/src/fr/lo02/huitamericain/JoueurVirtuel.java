package fr.lo02.huitamericain;

/**
 * Représente le jouer virtuel.
 * @author Lionel EA
 *
 */
public class JoueurVirtuel extends Joueur {
	
	public JoueurVirtuel(String nom){
		super(nom);
	}
	
	public void poserCarte(int indiceCarte, Talon talon) {
		talon.ajouterCarte(this.mainJoueur.retirerCarte(indiceCarte));
	}



}
