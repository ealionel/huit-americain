package fr.lo02.huitamericain;

import java.awt.EventQueue;

import fr.lo02.exceptions.WrongInputException;
import fr.lo02.vue.ConsoleView;
import fr.lo02.vue.GUIView;
import fr.lo02.vue.View;


/**
 * Cette classe correspond à la partie "Contrôleur" de l'architecture MVC. Il
 * gère et traite toutes les entrées et se charge de faire des modifications
 * dans le modèle.
 * 
 *
 */
public class Controleur {
	private View vue;
	private ConsoleView vue2;
	private Partie partie;
	
	private Object lastInput; //Représente la dernière commande réalisée par l'utilisateur

	/**
	 * Le constructeur est initialisé avec la partie.
	 * 
	 * @param partie
	 */
	public Controleur(Partie partie, boolean interfaceGraphique) {
		this.partie = partie;
		
		if(interfaceGraphique) {
			
			this.vue = new GUIView(partie);
			this.vue2 = new ConsoleView(partie);
			
			EventQueue.invokeLater((Runnable)vue);
			
		} else {
			this.vue = new ConsoleView(partie);
		}
	}
	
	/**
	 * Attend que l'utilisateur entre certaines valeurs sans quoi il renvoie l'exception WrongInputException.
	 * @param motsAttendu Array de String contenant les mots attendus par la méthode.
	 * @param entierAttendu Booléen un indiquant si la valeur atendue peut être un entier ou non.
	 * @param borneInf Borne inférieure de l'entier attendu
	 * @param borneSup Borne supérieure de l'entier attendu
	 * @return La valeur de ce qu'a entré l'utilisateur, un String ou un int.
	 * @throws WrongInputException
	 */
	public Object attendreValeur(String[] motsAttendu, boolean entierAttendu, int borneInf, int borneSup) throws WrongInputException {
		
		// On attend que l'utilisateur rentre quelque chose dans la console...
		Object returnValue=null;
		boolean correctInput = false;
		
		try {
			synchronized(this) {
				this.wait();
			}
		} catch (InterruptedException e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}

		if ((this.getLastInput() instanceof Integer & entierAttendu)) {
			if((int)this.getLastInput() >= borneInf & (int)this.getLastInput() <= borneSup) {
				returnValue = (int) this.getLastInput();
				correctInput = true;
			}
		}
		if((this.getLastInput() instanceof String) & motsAttendu.length > 0) {
			for(String cmd : motsAttendu) {
				if(((String) this.getLastInput()).equalsIgnoreCase(cmd)) {
					returnValue = (String) cmd;
					correctInput = true;
				}
			}
		}
		if(!correctInput) {	
			throw new WrongInputException();
		}
		
		return returnValue;
	}
	
	/**
	 * Surcharge. Attends que l'utilisateur entre une certaine commande passée en liste de String en argument.
	 * @param motsAttendu
	 * @return Un des mots attendus par le paramètre motsAttendu.
	 * @throws WrongInputException
	 */
	public Object attendreValeur(String[] motsAttendu) throws WrongInputException {
		return this.attendreValeur(motsAttendu, false, 0, 0);
	}

	/**
	 * Execute la commande passée en paramètre.
	 * 
	 * @param commande
	 *            Commande sous forme de chaîne de caractère
	 */
	public void executer(String commande){

		switch (commande.toLowerCase()) {
		case "carte":
		case "c":
			this.partie.getJoueurs()[0].parler(true, this.partie);
			break;
		case "contre carte":
		case "cc":
			this.partie.getJoueurs()[0].parler(false, this.partie);
			break;
		case "piocher":
		case "p":
		    this.partie.getJoueurs()[0].piocherCarte(this.partie.getPioche());
 			break;
		case "main":
		case "m":
			if(this.vue instanceof ConsoleView) {
				((ConsoleView)this.vue).afficherCartes(this.partie.getJoueurs()[0]);
			}
			break;
		}
	}
	
	/**
	 * Retourne la vue.
	 * @return
	 */
	public View getVue() {
		return vue;
	}
	
	public synchronized void setLastInput(Object arg) {
		this.lastInput = arg;
		
		this.notifyAll();
		
	}
	
	public Object getLastInput() {
		return this.lastInput;
	}
}
