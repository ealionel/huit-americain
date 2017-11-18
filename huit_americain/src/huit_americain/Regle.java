package huit_americain;

import effets.*;

/**
 * Classe qui contient les differentes règles du jeu.
 * @author Lionel EA
 *
 */

public class Regle {
	
	protected int nbJoueurs;
	protected int nbJeuxCartes;	//Nombre de jeux de cartes, peut être choisi en fonction du nombre de joueurs.
	protected int modePoints; 	//Pour choisir si le nombre de points est à compte positif ou négatif
	protected boolean joker; 	//Si on met un joker ou non dans le dèque
	
	/**
	 * Cet atribut permet à attribuer à chaque carte, pour chaque variante différente, un effet différent. Par exemple effetCartes[0] est l'effet de l'as, effetCartes[1] du 2, etc...
	 */
	protected Effet[] effetCartes = new Effet[14]; //Liste qui va contenir la liste des effets pour chaque valeur de cartes, genre {new EffetNormal, new EffetChangerCouleur, new EffetNormal, ...}, effetCartes[0] correspond à l'effet de l'As, etc.
	
	/**
	 * Regles par défaut du jeu : version minimale.
	 */
	Regle(int nbJoueurs, int nbJeuxCartes, int modePoints, boolean joker) {
		
		this.nbJoueurs = nbJoueurs;
		this.nbJeuxCartes = nbJeuxCartes;
		this.modePoints = modePoints;
		this.joker = joker;
		
		effetCartes[0] = new EffetNormal();
		effetCartes[1] = new EffetPiocherCarte();
		effetCartes[2] = new EffetNormal();
		effetCartes[3] = new EffetNormal();
		effetCartes[4] = new EffetNormal();
		effetCartes[5] = new EffetNormal();
		effetCartes[6] = new EffetNormal();
		effetCartes[7] = new EffetNormal();
		effetCartes[8] = new EffetNormal();
		effetCartes[9] = new EffetNormal();
		effetCartes[10] = new EffetNormal();
		effetCartes[11] = new EffetNormal();
		effetCartes[12] = new EffetNormal();
		effetCartes[13] = new EffetNormal();
		effetCartes[14] = new EffetNormal();
	}
}