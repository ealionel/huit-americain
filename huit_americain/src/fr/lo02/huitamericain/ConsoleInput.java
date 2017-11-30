package fr.lo02.huitamericain;

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
		
		while(input.hasNext() & isRunning) {
			String line = input.nextLine();
			System.out.println("ENTREE : > " + line);
			
			//Notification à la vue que l'utilisateur à entré quelque chose.
			setChanged();
			notifyObservers(line);
			
			if("arreter".equalsIgnoreCase(line)) {
				this.arreter();
			}
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
