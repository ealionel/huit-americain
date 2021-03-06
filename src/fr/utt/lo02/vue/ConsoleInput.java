package fr.utt.lo02.vue;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Cet objet permet de récupérer dans un thread différent des entrées données par l'utilisateur.
 * Cela est fait pour que l'utilisateur n'ait pas à attendre son tour et puisse faire des entrées à tout moment.
 * @author Lionel EA
 *
 */

public class ConsoleInput extends Observable implements Runnable{
	
	Thread thread;
	boolean isRunning;
	
	/**
	 * Le constructeur ajoute un observeur à cet objet et initialise le thread avec ce Runnable.
	 * @param obs Observeur de l'objet, ici ce sera ConsoleView la plupart du temps.
	 */
	public ConsoleInput(Observer obs) {
		this.addObserver(obs);
		thread = new Thread(this);
	}
	
	/**
	 * Méthode appelée lors du démarrage du thread.
	 * Il demande en permanence à l'utilisateur une entrée, qui est envoyée via une notification à la vue. 
	 */
	public void run() {
		Scanner input = new Scanner(System.in);
		
		while(isRunning) {
			String line = input.nextLine();
			
			//Notification à la vue que l'utilisateur à entré quelque chose.
			setChanged();
			notifyObservers(line);
		}
		
		input.close();
	}
	
	/**
	 * Lance le thread. 
	 */
	public void demarrer() {
		this.isRunning = true;
		thread.start();
	}
	
	/**
	 * Fait arrêter le thread en faisant changeant le booléen qui fait tourner la boucle.
	 */
	public void arreter() {
		this.isRunning = false;
	}
}
