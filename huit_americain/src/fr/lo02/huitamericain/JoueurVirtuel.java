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
	
	/**
	 * Cette méthode peut être modifiée dynamiquement via le <strong>patron de conception Strategie</strong>.
	 * En fonction de la stratégie choisie, l'ordinateur choisit une carte différemment.
	 * @param talon Talon sur lequel la carte va être posée.
	 * @return L'indice de la carte du joueur dans sa main.
	 */
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
	
	/**
	 * On peut modifier la stratégie du joueur grâce à cette méthode.
	 * @param strategie
	 */
	public void setStrategie(Strategie strategie) {
		this.strategie = strategie;
	}

}
