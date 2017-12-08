package fr.lo02.huitamericain;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class JoueurChecker implements Observer, Runnable {

	private static JoueurChecker instance = null;
	private Thread thread;
	private boolean isRunning;
	private ArrayList<Joueur> listeJoueurs;
	
	private JoueurChecker() {
		this.isRunning = false;
		this.thread = new Thread(this);
		this.demarrer();
	}
	
	public static synchronized JoueurChecker getInstance() {
		if(instance == null) {
			JoueurChecker.instance = new JoueurChecker();
		}
		return JoueurChecker.instance;
	}
	
	public synchronized void update(Observable obs, Object o) {
		if(((String) o) == "vulnerable") {
			this.notify(); //Reveille le run()
		}
	}
	
	public void run() {
		synchronized(this) {
			try{
				this.wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	/**
	 * Ajoute un joueur à la collection. Permet de les monitorer
	 */
	public void ajouterJoueur(Joueur joueur) {
		this.listeJoueurs.add(joueur);
	}
	
	public void demarrer() {
		this.isRunning = true;
		this.thread.start();
	}
	
	public void arreter() {
		this.isRunning = false;		
	}
	
}

