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
//		Carte c = new Carte(ValeurCarte.as, CouleurCarte.carreau);
		
		Effet effetCartes[] = new Effet[14];
		
		effetCartes[0] = new EffetNormal();
		effetCartes[1] = new EffetPiocherCarte();
		
		for (int i = 2; i < 14; i++) {
			effetCartes[i] = new EffetNormal();
	   }
		
		Pioche p = new Pioche(1, effetCartes, true);
		
		System.out.println(p.toString());
	}

}
