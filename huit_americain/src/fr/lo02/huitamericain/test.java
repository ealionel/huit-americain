package fr.lo02.huitamericain;

import fr.lo02.effets.Effet;
import fr.lo02.effets.EffetChangerSens;

public class test {

	public static void main(String[] args) {

		
//		for(Carte i: p.getListeCartes()) {
//			System.out.println(i.toString());
//		}

		Joueur j = new JoueurReel("Moi");
		Joueur j2 = new JoueurVirtuel("Ordi1");
		Joueur j3 = new JoueurVirtuel("Ordi2");
		
		System.out.println(j.getId() + " " + j3.getId());
		
		Carte c = new Carte(ValeurCarte.as, CouleurCarte.carreau);
		
	}

}
