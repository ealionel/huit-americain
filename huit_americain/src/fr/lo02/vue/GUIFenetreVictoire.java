package fr.lo02.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.lo02.huitamericain.Joueur;

public class GUIFenetreVictoire extends JFrame{
	JPanel panneau;
	JLabel texte;
	
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
