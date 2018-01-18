package fr.lo02.huitamericain;

import java.util.Observable;
import java.util.Observer;

import fr.lo02.vue.ConsoleInput;

/**
 * Objet qui permet d'initialiser les règles
 * @author Lionel EA
 *
 */
public class InitialiseurRegle implements Observer{
	
	ConsoleInput console;
	
	protected String nom;
	protected int nbJoueurs;
	protected int nbJeuxCartes;	//Nombre de jeux de cartes, peut être choisi en fonction du nombre de joueurs.
	protected int modePoints; 	//Pour choisir si le nombre de points est à compte positif ou négatif
	protected boolean joker; 	//Si on met un joker ou non dans le deque
	protected int nbCartesDebut;

	private boolean isRunning;
	
	public InitialiseurRegle() {
		this.console = new ConsoleInput(this);
		
		this.isRunning = true;
		
		
		while(isRunning) {
			synchronized(this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	public void update(Observable obs, Object arg) {
		
		this.action((String)arg);
		this.notifyAll();
		
	}
	
	/**
	 * Execute l'action en fonction de la commande executée
	 * @param arg
	 */
	public void action(String arg) {
		
		Object valeur = null;
		valeur = this.recupererValeur(arg);
		
		if(arg.startsWith(nom )) {
			this.nom = (String) valeur;
		}
		if(arg.startsWith("nombrejoueurs ")) {
			this.nbJoueurs = (int)valeur;
		}
		if(arg.startsWith("nombrejeux ")) {
			this.nbJeuxCartes = (int)valeur;
		}
		if(arg.startsWith("joker ")) {
			this.joker = true;
		}
		if(arg.startsWith("cartesdebut ")) {
			this.nbCartesDebut = (int) valeur;
		}
		if(arg.equalsIgnoreCase("commencer")) {
//			this.isRunning;
		}
	}
	
	/**
	 * Récupère la valeur de l'option modifiée
	 * @return
	 */
	public Object recupererValeur(String chaine) {
		
		String[] split = chaine.split(" ");
		Object retour = null;
		
		try {
			retour = Integer.parseInt(split[1]);
		}catch(NumberFormatException e) {
			retour = split[1];
		}
		
		return retour;
	}
	
	public Object[] retournerRegles() {
		
		Object[] retour = {this.nom, this.nbJoueurs, 0, this.joker, this.nbCartesDebut};
		
		return retour; 
	}
	
}
