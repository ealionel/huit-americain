package fr.lo02.huitamericain;

import java.util.Observer;

/**
 * Classe qui tourne sur un thread et vérifie en boucle si un joueur a appelé "carte" ou "contre-carte"
 * @author Lionel EA
 *
 */
public class Checker implements Runnable {
	private Thread thread;
	private boolean isRunning;
	private int checkInterval; //en ms
	private Partie partie;
	private Controleur controleur;
	
	/**
	 * Constructeur. Prend en paramètre la partie pour pouvoir agir.
	 * @param partie
	 */
	public Checker(Partie partie) {
		this.partie = partie;
		this.controleur = this.partie.getControleur();
		this.thread = new Thread(this);
		this.demarrer();
		this.checkInterval = 10; //en ms
	}
	
	/**
	 * Méthode lancée lorsque start() est appelée. Vérifie en boucle si la méthode parler a été appelée.
	 */
	public void run() {
		while(isRunning) {
			synchronized(this.controleur.getVue()) {
				try {
					this.controleur.getVue().wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				if(this.controleur.getVue().getLastInput() instanceof String) {
					this.controleur.executer((String) this.controleur.getVue().getLastInput());
				}
				
			}
		}
	}
	
	/**
	 * Démarre le thread
	 */
	public void demarrer() {
		this.isRunning = true;
		this.thread.start();
	}
	
	public void arreter() {
		this.isRunning = false;
	}
	
}
