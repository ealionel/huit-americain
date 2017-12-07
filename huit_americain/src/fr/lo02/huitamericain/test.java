package fr.lo02.huitamericain;

import fr.lo02.effets.Effet;
import fr.lo02.effets.EffetNormal;
import fr.lo02.effets.EffetPiocherCarte;

public class test {

	public static void main(String[] args) {

		
//		for(Carte i: p.getListeCartes()) {
//			System.out.println(i.toString());
//		}

//		Joueur j = new JoueurReel("Moi");
//		Joueur j2 = new JoueurVirtuel("Ordi1");
//		Joueur j3 = new JoueurVirtuel("Ordi2");
//		
//		System.out.println(j.getId() + " " + j3.getId());
		
		Effet[] effets = new Effet[14];
		
		for (int i = 0; i < effets.length; i++) {
			effets[i] = new EffetNormal();
		}
		Carte c = new Carte(ValeurCarte.as, CouleurCarte.carreau);
		
		Regle regle = new Regle(3, 2, 1, false, effets);
		Partie partie = new Partie(regle);
//		ConsoleView vue = new ConsoleView(partie);
		
//		System.out.println(partie.getJoueurs()[0].getMainJoueur());
//		System.out.println(partie.getJoueurs()[1].getMainJoueur());
		
//		vue.afficherCartes(partie.getJoueurs()[0]);
		
//		partie.getControleur().demanderCarte(partie.getJoueurs()[0]);
//		System.out.println(partie.getPioche());
//		partie.jouerTour(partie.getJoueurs()[0]);
		partie.demarrerPartie();
		
	}

}
