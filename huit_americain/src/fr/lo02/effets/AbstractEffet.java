package fr.lo02.effets;

import fr.lo02.huitamericain.Partie;

public abstract class AbstractEffet {
	
	protected String nom;
	protected Partie partie;
	
	public AbstractEffet() {	}
	
	public AbstractEffet(Partie partie) {
		this.partie = partie;
	}
	
	public String getNom() {
		return this.nom;
	}
}
