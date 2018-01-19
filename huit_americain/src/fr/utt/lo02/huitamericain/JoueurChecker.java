package fr.utt.lo02.huitamericain;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Singleton qui permet aux ordinateurs de dire "carte" ou "contre-carte".
 * 
 * @author Lionel EA
 *
 */
public class JoueurChecker implements Observer, Runnable {

	private static JoueurChecker instance = null;
	private Thread thread;
	private boolean isRunning;
	private ArrayList<Joueur> listeJoueurs;
	private ArrayList<Joueur> joueursVulnerables;

	/**
	 * Constructeur.
	 */
	private JoueurChecker() {
		this.isRunning = false;
		this.thread = new Thread(this);
		this.demarrer();
	}

	/**
	 * Permet de retourner une référence vers le singleton
	 * @return
	 */
	public static synchronized JoueurChecker getInstance() {
		if (instance == null) {
			JoueurChecker.instance = new JoueurChecker();
		}
		return JoueurChecker.instance;
	}

	/**
	 * Lorsque le joueur est vulnérable, il envoie une notification et on la récupère via cette méthode.
	 */
	public synchronized void update(Observable joueurObs, Object arg) {
		if(((String) arg).equals("vulnerable")){
			this.joueursVulnerables.add((Joueur) joueurObs);
		}
		if(joueurObs instanceof JoueurReel) {
			
		}
		this.notify(); // Reveille le run()
	}

	
	public synchronized void run() {
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		if(joueursVulnerables[0])		

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
