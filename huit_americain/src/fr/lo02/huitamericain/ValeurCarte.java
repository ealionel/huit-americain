package fr.lo02.huitamericain;
/**
 * Enumération qui contient les différentes valeurs que peut prendre une carte.
 * @author Lionel EA
 *
 *
 *
 */
public enum ValeurCarte {
	as ("As"),
	deux("2"),
	trois("3"),
	quatre("4"),
	cinq("5"),
	six("6"),
	sept("7"), 
	huit("8"),
	neuf("9"),
	dix("10"),
	valet("Valet"),
	dame("Dame"),
	roi("Roi"),
	joker("Joker");

	private String nomValeur;

	private ValeurCarte(String pNom){
		this.nomValeur = pNom; 
	}
	
	
	public String getName() {
		return this.nomValeur;
	}
	
}