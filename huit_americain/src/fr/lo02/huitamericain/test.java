package fr.lo02.huitamericain;

public class test {

	public static void main(String[] args) {
		Regle regle = new Regle(3, 2, 1, false, 2);
		Partie partie = new Partie(regle);

		partie.demarrerPartie();
	}

}
