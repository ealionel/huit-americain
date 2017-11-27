package fr.lo02.huitamericain;

public class JoueurReel extends Joueur {

	public JoueurReel(String nom){
		super(nom);
	}
	
	
	/**
	 * Pose la carte sur le talon.
	 * @param indiceCarte
	 * @param talon
	 */
	public void jouerCarte(int indiceCarte, Talon talon) {
		talon.ajouterCarte(this.mainJoueur.getCarte(indiceCarte));
		this.mainJoueur.retirerCarte(indiceCarte);
	}


}
