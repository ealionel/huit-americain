package fr.lo02.effets;

import java.util.Observable;

import fr.lo02.huitamericain.Partie;

public abstract class AbstractEffet extends Observable {
	
	protected String nom;
	
	public AbstractEffet() {
	
	}	
	
	public String getNom() {
		return this.nom;
	}

	
	public void notifier(String commande) {
		setChanged();
		notifyObservers(commande);
	}
}
