package fr.lo02.huitamericain;

import fr.lo02.effets.Effet;
import fr.lo02.effets.EffetChangerSens;

public class test {

	public static void main(String[] args) {

		Pioche p = new Pioche(1, true);
		
//		for(Carte i: p.getListeCartes()) {
//			System.out.println(i.toString());
//		}
		p.getCarte(1).effet();
		p.getCarte(1).setEffet(new EffetChangerSens());
		p.getCarte(1).effet();
		
		
		
		Carte c = new Carte(ValeurCarte.as, CouleurCarte.carreau);
		
	}

}
