package fr.lo02.huitamericain;

/**
 * Représente le joueur réel. 
 * 
 *
 */
public class JoueurReel extends Joueur {

	public JoueurReel(String nom){
		super(nom);
	}
	
	
	/**
	 * Pose la carte sur le talon.
	 * @param indiceCarte
	 * @param talon
	 */
	public void poserCarte(int indiceCarte, Talon talon) {
		talon.ajouterCarte(this.mainJoueur.retirerCarte(indiceCarte));
		
	}


}
