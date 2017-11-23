package fr.lo02.huitamericain;
/**
 * Enum�ration qui contient les diff�rentes couleurs possible que peut prendre une carte.
 * @author Lionel EA
 *
 *
 *
 */
public enum CouleurCarte {
	pique("Pique"),
	trefle("Tr�fle"),
	coeur("Coeur"),
	carreau("Carreau"),
	neutre("Neutre");
	
	/**
	 * Contient le nom de la couleur.
	 */
	private String nomCouleur;
	
	/**
	 * Constructeur permettant de sp�cifier le nom de la couleur.
	 * @param pNom
	 */
	CouleurCarte(String pNom){
		this.nomCouleur = pNom;
	}
	
	/**
	 * @return Le nom de la couleur.
	 */
	public String getName() {
		return this.nomCouleur;
	}
}
