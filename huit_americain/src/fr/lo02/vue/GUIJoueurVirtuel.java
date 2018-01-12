package fr.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.lo02.huitamericain.Joueur;

/**
 * JPanel qui affiche les informations relatives aux ordinateurs de la partie.
 * @author Lionel EA
 *
 */
public class GUIJoueurVirtuel extends JPanel {
	
	Joueur joueur;
	GUIView vue;
	
	JLabel nomJoueur;
	JLabel nbCartes;
	
	boolean actif;
	
	/**
	 * Constructeur.
	 * @param joueur Joueur affiché
	 * @param vue Référence vers la vue graphique.
	 */
	public GUIJoueurVirtuel(Joueur joueur, GUIView vue) {
		this.joueur = joueur;
		this.vue = vue;
		this.nomJoueur = new JLabel(joueur.getNom());
		this.nbCartes = new JLabel("Possède " + String.valueOf(joueur.getMainJoueur().nbCartes()) + " carte(s)");
		
		
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(this.nomJoueur);
		
		this.add(this.nbCartes);
		
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * Rafraichit les informations et l'état du joueur.
	 */
	public void rafraichir() {
		this.nbCartes.setText("Possède " + String.valueOf(this.joueur.getMainJoueur().nbCartes()) + " carte(s)");
		this.afficherActif();
	}
	
	/**
	 * Méthode qui affiche une bordure verte si le joueur est actif, sinon rien.
	 */
	public void afficherActif() {
		if(this.isActif()) {
			 this.setBorder(BorderFactory.createLineBorder(new Color(188, 247, 51),10));
		}else {
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		
	}

	/**
	 * Vérifie si le joueur est actif ou pas dans la partie.
	 * @return
	 */
	public boolean isActif() {
		
		if(this.vue.getPartie().getJoueurActif() == this.joueur) {
			this.setActif(true);
		}
		else {
			this.setActif(false);
		}
		
		return actif;
	}

	/**
	 * Modifie le boolean <em>actif</em>
	 * @param actif
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
