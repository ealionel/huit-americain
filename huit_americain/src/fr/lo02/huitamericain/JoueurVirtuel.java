package fr.lo02.huitamericain;

import fr.lo02.strategies.*;

/**
 * Représente le jouer virtuel.
 * @author Lionel EA
 *
 */
public class JoueurVirtuel extends Joueur {
	
	private Strategie strategie;
	
	public JoueurVirtuel(String nom, Strategie strategie){
		super(nom);
		this.strategie = strategie;
	}
	
	public int choisirCarte(Talon talon) {
		return this.strategie.choisirCarte(this, talon);
	}
	
	/**
	 * Retourne la stratégie du joueur virtuel.
	 * @return La stratégie
	 */
	public Strategie getStrategie() {
		return this.strategie;
	}
	
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

}
