package fr.lo02.vue;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.lo02.huitamericain.Carte;

/**
 * JPanel qui gère l'affichage d'une carte et des événements basiques relatives
 * à la carte.
 * 
 * @author Lionel EA
 *
 */
public class GUICarte extends JPanel implements MouseListener {

	BufferedImage image;
	JLabel imageLabel;
	Carte carte;
	JPanel conteneur;
	GUIView vue;

	int largeur = 140;
	int hauteur = 190;

	/**
	 * Constructeur du JPanel contenant la carte.
	 * 
	 * @param carte
	 *            Carte qui veut être affichée.
	 * @param conteneur
	 *            Référence vers le conteneur dans lequel il est contenu.
	 * @param vue
	 *            Référence vers la vue.
	 */
	public GUICarte(Carte carte, JPanel conteneur, GUIView vue) {

		this.conteneur = conteneur;
		this.carte = carte;
		this.vue = vue;

		// Pour afficher le fond transparent
		this.setOpaque(false);

		// Taille par défaut de l'image
		this.setSize(this.largeur, this.hauteur);

		// On obtient le nom du fichier en fonction de la carte.
		String nomFichier = carte.getCouleur() + carte.getValeur().getName() + ".png";
		String chemin = "img/cartes/";

		// On charge le fichier
		try {
			image = ImageIO.read(new File(chemin + nomFichier));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// On redimensionne l'image avec la taille du JPanel.
		Image imageRedim = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		imageLabel = new JLabel(new ImageIcon(imageRedim));
		this.add(imageLabel);

		this.addMouseListener(this);

	}

	/**
	 * Surcharge du constructeur permettant de spécifier la hauteur et la largeur
	 * par défaut de la carte.
	 * 
	 * @param carte
	 * @param conteneur
	 *            Référence vers le conteneur dans lequel il est contenu.
	 * @param vue
	 *            Référence vers la vue.
	 * @param largeur
	 *            Largeur de la carte
	 * @param hauteur
	 *            Hauteur de la carte.
	 */
	public GUICarte(Carte carte, JPanel conteneur, GUIView vue, int largeur, int hauteur) {
		this(carte, conteneur, vue);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.redimensionnerAbsolu(largeur, hauteur);
	}

	/**
	 * Redimensionne la taille du JPanel et de l'image par rapport à la taille par
	 * défaut de l'image (140x190).
	 * 
	 * @param scaler
	 *            Coefficient de redimensionnage
	 */
	public void redimensionner(double scaler) {

		double rLongueur = scaler * this.largeur;
		double rHauteur = scaler * this.hauteur;

		this.redimensionnerAbsolu((int) rLongueur, (int) rHauteur);
	}

	/**
	 * Redimensionne la taille du JPanel et de l'image par rapport à la taille
	 * actuelle de l'image.
	 * 
	 * @param scaler
	 *            Coefficient de redimensionnage
	 */
	public void redimensionnerRelatif(double scaler) {
		double rLongueur = scaler * this.getWidth();
		double rHauteur = scaler * this.getHeight();

		this.redimensionnerAbsolu((int) rLongueur, (int) rHauteur);
	}

	/**
	 * Redimensionne la taille du JPanel avec les valeurs données en paramètre.
	 * 
	 * @param largeur
	 * @param hauteur
	 */
	public void redimensionnerAbsolu(int largeur, int hauteur) {
		this.setSize(largeur, hauteur);

		this.remove(imageLabel);

		Image imageRedim = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
		imageLabel = new JLabel(new ImageIcon(imageRedim));
		this.add(imageLabel);

		this.conteneur.revalidate();
		this.conteneur.repaint();
	}

	public void mouseEntered(MouseEvent event) {
		((GUIPanneauInfo) this.vue.getPanneauInfo()).setInfoCarte(this.carte);
		this.redimensionnerAbsolu(this.largeur + 10, this.hauteur + 10);
	}

	public void mouseExited(MouseEvent event) {
		((GUIPanneauInfo) this.vue.getPanneauInfo()).setInfoCarte();
		this.redimensionnerAbsolu(this.largeur, this.hauteur);
	}

	public void mousePressed(MouseEvent event) {
	}

	public void mouseReleased(MouseEvent event) {

	}

	public void mouseClicked(MouseEvent event) {
		if (this.conteneur == this.vue.getPanneauCartes()) {
			this.vue.setLastInput(this.vue.getCartesJoueur().indexOf(this) + 1);
		}
	}

}
