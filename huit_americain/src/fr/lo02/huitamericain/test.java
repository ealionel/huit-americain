package fr.lo02.huitamericain;

import java.awt.EventQueue;

import fr.lo02.vue.GUICarte;
import fr.lo02.vue.GUIView;

public class test {

	public static void main(String[] args) {
		Regle regle = new Regle(3, 2, 1, false, 2);
		Partie partie = new Partie(regle);

		Carte c  = new Carte(ValeurCarte.trois, CouleurCarte.coeur);
		GUICarte gc = new GUICarte(c);
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIView window = new GUIView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

//		partie.demarrerPartie();

	}

}
