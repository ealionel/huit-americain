package fr.utt.lo02.huitamericain;

import fr.utt.lo02.effets.*;

/**
 * Classe qui contient les differentes règles du jeu.
 * @author Lionel EA
 *
 */

public class Regle {

	protected String nomVariante;
	
	protected String nom;
	protected int nbJoueurs;
	protected int nbJeuxCartes;	//Nombre de jeux de cartes, peut être choisi en fonction du nombre de joueurs.
	protected int modePoints; 	//Pour choisir si le nombre de points est à compte positif ou négatif
	protected boolean joker; 	//Si on met un joker ou non dans le deque
	protected int nbCartesDebut;//Nombre distribué à chaque joueur au début d'une partie
	protected Effet[] effetCartes = new Effet[14]; //Liste qui va contenir la liste des effets pour chaque valeur de cartes, genre {new EffetNormal, new EffetChangerCouleur, new EffetNormal, ...}, effetCartes[0] correspond � l'effet de l'As, etc.

	
	/**
	 * Constructeur basique. Les effets ne sont pas initialisés. (NE PAS UTILISER)
	 */
	public Regle(String nom, int nbJoueurs, int nbJeuxCartes, int modePoints, boolean joker, int nbCartesDebut) {

		this.nom = nom;
		this.nbJoueurs = nbJoueurs;
		this.nbJeuxCartes = nbJeuxCartes;
		this.modePoints = modePoints;
		this.joker = joker;
		this.nbCartesDebut = nbCartesDebut; //A modifier dynamiquement
	}
	
	
	/**
	 * Surcharge. Consutructeur avec une liste d'effets en paramètre.
	 * @param nom Nom du joueur
	 * @param nbJoueurs Nombre de joueurs
	 * @param nbJeuxCartes Nom de jeux de cartes
	 * @param modePoints Mode point (inutilisé)
	 * @param joker Jouer avec des jokers
	 * @param nbCartesDebut Nombre de cartes de début de partie
	 * @param effetCartes Liste des effets de cartes. Liste de 14 éléments, chaque case correspond à l'effet associé à son numéro de carte.
	 */
	public Regle(String nom, int nbJoueurs, int nbJeuxCartes, int modePoints, boolean joker, int nbCartesDebut, Effet[] effetCartes) {
		this(nom, nbJoueurs, nbJeuxCartes, modePoints, joker, nbCartesDebut);
		
		for(int i = 0; i < effetCartes.length; i++) {
			this.effetCartes[i] = effetCartes[i];
		}
	}
	
	/**
	 * Surcharge. Consutructeur avec une liste d'effets en paramètre.
	 * @param nom Nom du joueur
	 * @param nbJoueurs Nombre de joueurs
	 * @param nbJeuxCartes Nom de jeux de cartes
	 * @param modePoints Mode point (inutilisé)
	 * @param joker Jouer avec des jokers
	 * @param nbCartesDebut Nombre de cartes de début de partie
	 * @param variante Numéro de la variante initialisée.
	 */
	public Regle(String nom, int nbJoueurs, int nbJeuxCartes, int modePoints, boolean joker, int nbCartesDebut, int variante) {
		this(nom, nbJoueurs, nbJeuxCartes, modePoints, joker, nbCartesDebut);
		this.setVariante(variante);
	}

	
	/**
	 * Permet de séléctionner les variantes.
	 * @param numeroVariante Numéro de la variante.
	 */
	public void setVariante(int numeroVariante) {
		switch(numeroVariante) {
		
		//Ne pas modifier comment c'est présenté, ça permet que ce soit plus visuel et facile à modifier.
		
			case 1: //VARIANTE MINIMALE
				this.nomVariante = "Variante minimale";
				this.effetCartes[0] = new EffetNormal();
				this.effetCartes[1] = new EffetNormal();
				this.effetCartes[2] = new EffetNormal();
				this.effetCartes[3] = new EffetNormal();
				this.effetCartes[4] = new EffetNormal();
				this.effetCartes[5] = new EffetNormal();
				this.effetCartes[6] = new EffetChangerSens();
				this.effetCartes[7] = new EffetChangerCouleur();
				this.effetCartes[8] = new EffetNormal();
				this.effetCartes[9] = new EffetObligerRejouer();
				this.effetCartes[10] = new EffetNormal();
				this.effetCartes[11] = new EffetPiocherCarte(2);
				this.effetCartes[12] = new EffetNormal();
				this.effetCartes[13] = new EffetNormal();
				break;
			
			case 2:
				this.nomVariante = "Demo LO02";
				this.effetCartes[0] = new EffetNormal();
				this.effetCartes[1] = new EffetNormal();
				this.effetCartes[2] = new EffetNormal();
				this.effetCartes[3] = new EffetBloquerSuivant();
				this.effetCartes[4] = new EffetPiocherCarte(2);
				this.effetCartes[5] = new EffetChangerSens();
				this.effetCartes[6] = new EffetPiocherCarte(2);
				this.effetCartes[7] = new EffetBloquerSuivant();
				this.effetCartes[8] = new EffetNormal();
				this.effetCartes[9] = new EffetObligerRejouer();
				this.effetCartes[10] = new EffetNormal();
				this.effetCartes[11] = new EffetNormal();
				this.effetCartes[12] = new EffetChangerSens();
				this.effetCartes[13] = new EffetNormal();
				break;
		
			case 3:
				this.nomVariante = "Variante 1";
				this.effetCartes[0] = new EffetNormal();
				this.effetCartes[1] = new EffetNormal();
				this.effetCartes[2] = new EffetNormal();
				this.effetCartes[3] = new EffetNormal();
				this.effetCartes[4] = new EffetNormal();
				this.effetCartes[5] = new EffetNormal();
				this.effetCartes[6] = new EffetNormal();
				this.effetCartes[7] = new EffetChangerCouleur();
				this.effetCartes[8] = new EffetNormal();
				this.effetCartes[9] = new EffetObligerRejouer();
				this.effetCartes[10] = new EffetNormal();
				this.effetCartes[11] = new EffetNormal();
				this.effetCartes[12] = new EffetNormal();
				this.effetCartes[13] = new EffetNormal();
				break;
		}
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public int getNbJeuxCartes() {
		return nbJeuxCartes;
	}

	public int getModePoints() {
		return modePoints;
	}

	public boolean isJoker() {
		return joker;
	}

	public int getNbCartesDebut() {
		return nbCartesDebut;
	}

	public Effet[] getEffetCartes() {
		return effetCartes;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public void setNbJeuxCartes(int nbJeuxCartes) {
		this.nbJeuxCartes = nbJeuxCartes;
	}

	public void setModePoints(int modePoints) {
		this.modePoints = modePoints;
	}

	public void setJoker(boolean hasJoker) {
		this.joker = hasJoker;
	}

	public void setNbCartesDebut(int nbCartesDebut) {
		this.nbCartesDebut = nbCartesDebut;
	}

	public void setEffetCartes(Effet[] effetCartes) {
		this.effetCartes = effetCartes;
	}
}