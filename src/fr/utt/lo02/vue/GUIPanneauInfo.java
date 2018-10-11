package fr.utt.lo02.vue;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.utt.lo02.effets.AbstractEffet;
import fr.utt.lo02.huitamericain.Carte;

/**
 * JPanel qui affiche les informations d'une carte.
 * 
 * @author Lionel EA
 *
 */
public class GUIPanneauInfo extends JPanel {
	
	JLabel nomCarte;
	JLabel infoEffet;
	
	public GUIPanneauInfo() {
		
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		this.nomCarte = new JLabel();
		this.infoEffet = new JLabel();
		
		this.add(nomCarte);
		this.add(infoEffet);
	
	}
	
	/**
	 * Modifie le texte affiché dans le JPanel en fonction de la carte mise en paramètre.
	 * @param carte
	 */
	public void setInfoCarte(Carte carte) {
		this.nomCarte.setText(carte.toString());
		this.infoEffet.setText("<html>" + ((AbstractEffet)carte.getEffet()).getNom() + "</html>");
	}
	
	/**
	 * Surcharge. Permet de ne rien afficher dans le JPanel.
	 */
	public void setInfoCarte() {
		this.nomCarte.setText("");
		this.infoEffet.setText("");
	}

}
