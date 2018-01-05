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
	
	
	public GUIJoueurVirtuel(Joueur joueur, GUIView vue) {
		this.joueur = joueur;
		this.vue = vue;
		this.nomJoueur = new JLabel(joueur.getNom());
		this.nbCartes = new JLabel("Possède " + String.valueOf(joueur.getMainJoueur().nbCartes()) + " carte(s)");
		
		
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(this.nomJoueur);
		
		this.add(this.nbCartes);
		
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void rafraichir() {
		this.nbCartes.setText("Possède " + String.valueOf(this.joueur.getMainJoueur().nbCartes()) + " carte(s)");
		this.afficherActif();
	}
	
	public void afficherActif() {
		
		if(this.isActif()) {
			 this.setBorder(BorderFactory.createLineBorder(Color.GREEN,10));
		}else {
			this.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		
	}

	public boolean isActif() {
		
		if(this.vue.getPartie().getJoueurActif() == this.joueur) {
			this.setActif(true);
		}
		else {
			this.setActif(false);
		}
		
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

}
