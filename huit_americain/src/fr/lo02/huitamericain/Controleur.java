package fr.lo02.huitamericain;

import java.io.IOException;

import fr.lo02.exceptions.WrongInputException;

/**
 * Cette classe correspond à la partie "Contrôleur" de l'architecture MVC. Il
 * gère et traite toutes les entrées et se charge de faire des modifications
 * dans le modèle.
 * 
 *
 */
public class Controleur {
	private ConsoleView vue;
	private Partie partie;

	/**
	 * Le constructeur est initialisé avec la partie.
	 * 
	 * @param partie
	 */
	public Controleur(Partie partie) {
		this.partie = partie;
		this.vue = new ConsoleView(partie);
		this.vue.initialiserInput();

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
			synchronized(vue) {
				vue.wait();
			}
		} catch (InterruptedException e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}

		if ((vue.getLastInput() instanceof Integer & entierAttendu)) {
			if((int)vue.getLastInput() >= borneInf & (int)vue.getLastInput() <= borneSup) {
				returnValue = (int) vue.getLastInput();
				correctInput = true;
			}
		}
		if((vue.getLastInput() instanceof String) & motsAttendu.length > 0) {
			for(String cmd : motsAttendu) {
				if(((String) vue.getLastInput()).equalsIgnoreCase(cmd)) {
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
	 * Execute la commande passée en paramètre.
	 * 
	 * @param commande
	 *            Commande sous forme de chaîne de caractère
	 */
	public void executer(String commande){

		switch (commande.toLowerCase()) {
		case "carte":
			this.partie.getJoueurs()[0].parler(true, this.partie);
			break;
		case "contre carte":
			this.partie.getJoueurs()[0].parler(false, this.partie);
			break;
		case "piocher":
			partie.getJoueurActif().piocherCarte(this.partie.getPioche());
			break;
		}
	}
	
	/**
	 * Retourne la vue.
	 * @return
	 */
	public ConsoleView getVue() {
		return vue;
	}
}
