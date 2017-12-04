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

	/**
	 * Le constructeur est initialisé avec la partie.
	 * 
	 * @param partie
	 */
	public Controleur(Partie partie) {
		this.vue = new ConsoleView(partie);
		this.vue.initialiserInput();

	}

	/**
	 * Attend que l'utilisateur entre la position d'une carte dans sa main. L'entier demandé doit être valide.
	 * @param joueur
	 * @return La position de la carte dans la main du joueur.
	 */
	public int demanderCarte(Joueur joueur) {
		vue.afficherCartes(joueur);
		vue.demanderCarte();


		int positionEntree = -1;

		while(true) {
			try {
				positionEntree = this.attendreEntier();
				if(positionEntree < 1 | positionEntree > joueur.getMainJoueur().nbCartes()) {
					throw new WrongInputException();
				}
				break;
			} catch (WrongInputException e) {
				vue.afficherErreur();
			}
		}
		
		return positionEntree;
		
	}

	/**
	 * Attend que l'utilisateur entre un entier dans la console.
	 * 
	 * @return Un entier
	 * @throws WrongInputException
	 */
	public int attendreEntier() throws WrongInputException {
		
		// On attend que l'utilisateur rentre quelque chose dans la console...
		try {
			synchronized(vue) {
				vue.wait();
			}
		} catch (InterruptedException e) {
			System.out.println("Une erreur est survenue");
			e.printStackTrace();
		}

		if (vue.getLastInput() instanceof Integer) {
			return (int) vue.getLastInput();
		} else {
			throw new WrongInputException();
		}
	}

	/**
	 * Execute la commande passée en paramètre.
	 * 
	 * @param commande
	 *            Commande sous forme de chaîne de caractère
	 */
	public void executer(String commande) throws IOException {
		Object cmd = commande;

		if (commande.matches("-?\\d+(\\.\\d+)?")) { // Expression régulière pour savoir si la chaîne est composée que de
													// chiffres.
			cmd = Integer.parseInt(commande);
		}
		switch (commande.toLowerCase()) {
		case "carte":
			// A REMPLIR
			break;
		case "contre carte":
			// A REMPLIR
			break;
		}
	}
}
