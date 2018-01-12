package fr.lo02.huitamericain;

import fr.lo02.vue.GUIFenetreVictoire;

public class test {

	public static void main(String[] args) {
		Regle regle = new Regle("Lionel", 5, 2, 1, false, 4, 2);
		Partie partie = new Partie(regle);
		
		partie.demarrerPartie();

	}

}
