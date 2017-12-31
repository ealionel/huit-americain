package fr.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.lo02.huitamericain.Carte;
import fr.lo02.huitamericain.CouleurCarte;
import fr.lo02.huitamericain.ValeurCarte;
import fr.lo02.huitamericain.View;

public class GUIView implements View {

	public JFrame frame;


	/**
	 * Create the application.
	 */
	public GUIView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 983, 661);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panneauCartes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panneauCartes.getLayout();
		panneauCartes.setBackground(new Color(128, 128, 128));
		panel.add(panneauCartes, BorderLayout.SOUTH);
		
		JPanel panneauTalon = new JPanel();
		panneauTalon.setBackground(new Color(154, 205, 50));
		panel.add(panneauTalon, BorderLayout.CENTER);
		
		GUICarte gc = new GUICarte(new Carte(ValeurCarte.as, CouleurCarte.carreau));
		GUICarte gc2 = new GUICarte(new Carte(ValeurCarte.deux, CouleurCarte.carreau));
		GUICarte gc3 = new GUICarte(new Carte(ValeurCarte.dame, CouleurCarte.coeur));
		GUICarte gc4 = new GUICarte(new Carte(ValeurCarte.as, CouleurCarte.pique));
		GUICarte gc5 = new GUICarte(new Carte(ValeurCarte.trois, CouleurCarte.trefle));
		panneauTalon.add(gc);
		panneauCartes.add(gc2);
		panneauCartes.add(gc3);
		panneauCartes.add(gc4);
		gc5.redimensionner(1);
		panneauCartes.add(gc5);	
	}

}
