package fr.utt.lo02.huitamericain;

/**
 * Classe contenant la méthode main(). 
 * @author Lionel EA
 *
 */
public class LanceurPrincipal {

	public static void main(String[] args) {
		Regle regle = new Regle("Doyen", 5, 2, 1, false, 5, 2);
		Partie partie = new Partie(regle);
		
		partie.demarrerPartie();

	}

}
