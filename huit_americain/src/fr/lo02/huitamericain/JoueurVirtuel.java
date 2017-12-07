package fr.lo02.huitamericain;

import fr.lo02.strategieIA.*;

/**
 * Représente le jouer virtuel.
 * @author Lionel EA
 *
 */
public class JoueurVirtuel extends Joueur {
	
	private StrategieIA strategie;
	
	public JoueurVirtuel(String nom, StrategieIA strategie){
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
	public StrategieIA getStrategie() {
		return this.strategie;
	}
	
	public void setStrategie(StrategieIA strategie) {
		this.strategie = strategie;
	}

}
