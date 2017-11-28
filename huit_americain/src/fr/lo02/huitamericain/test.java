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
//		
		Carte c = new Carte(ValeurCarte.as, CouleurCarte.carreau);
		
		Regle regle = new Regle(3, 1, 1, true);
		
		Partie partie = new Partie(regle);
		
		partie.distribuer();
		System.out.println(partie.getJoueurs()[0].getMainJoueur());
//		System.out.println(partie.getJoueurs()[1].getMainJoueur());
		
		partie.getJoueurs()[0].jouerCarte(1, partie.getTalon());
		partie.getJoueurs()[0].jouerCarte(1, partie.getTalon());
		partie.getJoueurs()[0].jouerCarte(1, partie.getTalon());

		System.out.println(partie.getJoueurs()[0].getMainJoueur());

		System.out.println(partie.getTalon());
		
		
	}

}
