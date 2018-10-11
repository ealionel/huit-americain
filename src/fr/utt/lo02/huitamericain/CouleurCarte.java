package fr.utt.lo02.huitamericain;
/**
 * Enumération qui contient les différentes couleurs possible que peut prendre une carte.
 * @author Lionel EA
 *
 *
 *
 */
public enum CouleurCarte {
	pique	("♠ Pique"),
	trefle	("♣ Trèfle"),
	coeur	("♥ Coeur"),
	carreau	("♦ Carreau"),
	neutre	("Neutre");
	
	/**
	 * Contient le nom de la couleur.
	 */
	private String nomCouleur;
	
	/**
	 * Constructeur permettant de spécifier le nom de la couleur.
	 * @param pNom
	 */
	private CouleurCarte(String pNom){
		this.nomCouleur = pNom;
	}
	
	/**
	 * @return Le nom de la couleur.
	 */
	public String getName() {
		return this.nomCouleur;
	}
}
