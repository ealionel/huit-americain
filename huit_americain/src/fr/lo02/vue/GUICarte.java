package fr.lo02.vue;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.lo02.huitamericain.Carte;

/**
 * JPanel qui gère l'affichage d'une carte et des événements basiques relatives à la carte.
 * @author Lionel EA
 *
 */
public class GUICarte extends JPanel {

	BufferedImage image;
	JLabel imageLabel;

	/**
	 * Constructeur du JPanel contenant la carte.
	 * @param carte Carte qui veut être affichée.
	 */
	public GUICarte(Carte carte) {
		//Pour afficher le fond transparent
		this.setOpaque(false);
		
		//Taille par défaut de l'image
		this.setSize(140,190);
		
		//On obtient le nom du fichier en fonction de la carte.
		String nomFichier = carte.getCouleur() + carte.getValeur().getName() + ".png";
		String chemin = "img/cartes/";

		//On charge le fichier
		try {
			image = ImageIO.read(new File(chemin + nomFichier));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Image imageRedim = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
	
		imageLabel = new JLabel(new ImageIcon(imageRedim));
		
		this.add(imageLabel);
	}
	
	/**
	 * Redimensionne la taille du JPanel et de l'image par rapport à la taille par défaut de l'image (140x190).
	 * @param scaler Coefficient de redimensionnage
	 */
	public void redimensionner(float scaler) {
		this.setSize(Math.round(140*scaler), Math.round(190*scaler));
		
		this.remove(imageLabel);
		
		Image imageRedim = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		imageLabel = new JLabel(new ImageIcon(imageRedim));
		this.add(imageLabel);
	}
	
	/**
	 * Redimensionne la taille du JPanel et de l'image par rapport à la taille actuelle de l'image.
	 * @param scaler Coefficient de redimensionnage
	 */
	public void redimensionnerRelatif(float scaler) {
		this.setSize(Math.round(this.getWidth()*scaler), Math.round(this.getHeight()*scaler));
		
		this.remove(imageLabel);
		
		Image imageRedim = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		imageLabel = new JLabel(new ImageIcon(imageRedim));
		this.add(imageLabel);
	}

}
