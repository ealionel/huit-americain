package fr.lo02.huitamericain;

public class JoueurVirtuel extends Joueur {
	
	public JoueurVirtuel(String nom){
		super(nom);
	}
	
	public void jouerCarte(int indiceCarte, Talon talon) {
		talon.ajouterCarte(this.mainJoueur.getCarte(indiceCarte));
		this.mainJoueur.retirerCarte(indiceCarte);
	}



}
