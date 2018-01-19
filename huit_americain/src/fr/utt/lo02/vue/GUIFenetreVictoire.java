package fr.utt.lo02.vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.utt.lo02.huitamericain.Joueur;

/**
 * JFrame qui s'affiche en fin de partie, qui affiche le nom du gagnant.
 * @author Lionel EA
 *
 */
public class GUIFenetreVictoire extends JFrame{
	JPanel panneau;
	JLabel texte;
	
	/**
	 * Constructeur. Il prend en paramètre le joueur gagnant.
	 * @param joueur Joueur gagnant.
	 */
	public GUIFenetreVictoire(Joueur joueur) {
		
		this.setSize(500,100);
		this.setUndecorated(true);
		this.panneau = new JPanel();
		this.setLocationRelativeTo(null);
				
		this.panneau.setBackground(new Color(245, 195, 17));
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
		
		this.texte = new JLabel(joueur + " a gagné!" );
		this.panneau.setBorder(new EmptyBorder( 10, 20, 100, 40));
        
	    this.texte.setFont(new Font("Verdana", Font.BOLD, 30));
	    this.texte.setForeground(new Color(255,255,255));
		this.add(this.panneau);
		this.panneau.add(texte);
		this.setVisible(true);
	}
}
