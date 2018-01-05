package fr.lo02.huitamericain;

import java.awt.EventQueue;

import fr.lo02.vue.GUICarte;
import fr.lo02.vue.GUIView;

public class test {

	public static void main(String[] args) {
		Regle regle = new Regle(3, 2, 1, false, 2);
		Partie partie = new Partie(regle);
		
	
		partie.demarrerPartie();

	}

}
