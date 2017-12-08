package fr.lo02.effets;

import java.util.Observable;

import fr.lo02.huitamericain.Partie;

public abstract class AbstractEffet extends Observable {
	
	protected String nom;
	protected Partie partie;
	
	public AbstractEffet() {
	}
	
	public AbstractEffet(Partie partie) {
		this.partie = partie;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public Partie getPartie() {
		return this.partie;
	}
	
	public void notifier(String commande) {
		setChanged();
		notifyObservers(commande);
	}
}
