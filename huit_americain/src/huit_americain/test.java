package huit_americain;

import effets.Effet;
import effets.EffetChangerSens;

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
