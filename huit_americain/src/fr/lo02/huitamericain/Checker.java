package fr.lo02.huitamericain;

import java.util.Observer;

import fr.lo02.exceptions.WrongInputException;

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
	 * Méthode lancée lorsque start() est appelée. Vérifie si chaque entrée possède "parler" comme valeur.
	 */
	public void run() {

		String[] cmdAttendues = {"carte", "contre carte", "c", "cc", "main", "m"};

		while(isRunning) {
			try {
				this.controleur.attendreValeur(cmdAttendues);
				this.controleur.executer((String) this.controleur.getVue().getLastInput());
			}catch(WrongInputException e) {
				//On fait rien si c'est pas une bonne entrée.
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
