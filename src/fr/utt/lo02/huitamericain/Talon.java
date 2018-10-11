package fr.utt.lo02.huitamericain;

import java.util.ArrayDeque;

/**
 * Classe héritant de <em>GroupeCartes</em> qui représente un talon de partie.
 * @author Lionel EA
 *
 */
public class Talon extends GroupeCartes{

	public Talon(){
		listeCartes = new ArrayDeque<Carte>();
	}
	
}
