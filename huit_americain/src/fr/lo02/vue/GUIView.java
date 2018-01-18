package fr.lo02.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;

import fr.lo02.effets.EffetPiocherCarte;
import fr.lo02.huitamericain.Carte;
import fr.lo02.huitamericain.CouleurCarte;
import fr.lo02.huitamericain.Evenement;
import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.JoueurReel;
import fr.lo02.huitamericain.JoueurVirtuel;
import fr.lo02.huitamericain.Partie;
import fr.lo02.huitamericain.ValeurCarte;

public class GUIView implements View, Observer, Runnable {

	private JFrame frame;
	private Partie partie;


	private JPanel panneauTalon;
	private JPanel panneauCartes;
	private JPanel panneauPrincipal;

	private ArrayList<GUICarte> cartesJoueur;
	private ArrayList<GUIJoueurVirtuel> listeJoueurs;	
	
	private JPanel panneauControle;
	private JPanel panneauOrdi;
	

	private JButton btnPiocher;
	private JButton btnDireCarte;
	private JButton btnDireContreCarte;
	private JPanel panneauInfo;

	/**
	 * Constructeur de l'interface graphique.
	 * @param partie 
	 */
	public GUIView(Partie partie) {
		this.partie = partie;
		
		this.partie.addObserver(this);
		for(Joueur j : this.partie.getJoueurs()) {
			j.addObserver(this);
		}
		
		
		this.listeJoueurs = new ArrayList<GUIJoueurVirtuel>();
		this.cartesJoueur = new ArrayList<GUICarte>();

		initialize();
		ajoutControle();
	}

	
	/**
	 * Permet de mettre à jour la vue à chaque événement.
	 * @param obs Objet observable
	 * @param arg Argument de l'observable. Ici correspondra à un objet de type Evenement.
	 */
	public synchronized void update(Observable obs, Object arg) {
		if(obs instanceof Partie | obs instanceof Joueur) {
			this.action(obs, (Evenement) arg);
		}
	}

	/**
	 * Invoqué par l'Event Dispatch Thread
	 */
	public void run() {
		try {
			this.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.frame = new JFrame();
		this.frame.setTitle("Le huit américain ! (LO02 Edition)");
		frame.setBounds(100, 100, 1000, 700);
		 frame.setLocationRelativeTo(null);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		this.panneauPrincipal = new JPanel();

		frame.getContentPane().add(panneauPrincipal);
		panneauPrincipal.setLayout(new BorderLayout(0, 0));
		
		panneauControle = new JPanel();
		panneauControle.setPreferredSize(new Dimension(150, 500));
		panneauControle.setBorder(new TitledBorder(null, "Informations", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
		panneauPrincipal.add(panneauControle, BorderLayout.WEST);
		GridBagLayout gbl_panneauControle = new GridBagLayout();
		gbl_panneauControle.columnWidths = new int[]{117, 0};
		gbl_panneauControle.rowHeights = new int[] {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 0, 37, 60};
		gbl_panneauControle.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panneauControle.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panneauControle.setLayout(gbl_panneauControle);
		
		panneauInfo = new GUIPanneauInfo();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 9;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panneauControle.add(panneauInfo, gbc_panel);
		
		btnDireContreCarte = new JButton("Dire Contre Carte");

		GridBagConstraints gbc_btnDireContreCarte = new GridBagConstraints();
		gbc_btnDireContreCarte.fill = GridBagConstraints.BOTH;
		gbc_btnDireContreCarte.insets = new Insets(0, 0, 5, 0);
		gbc_btnDireContreCarte.gridx = 0;
		gbc_btnDireContreCarte.gridy = 10;
		panneauControle.add(btnDireContreCarte, gbc_btnDireContreCarte);
		
		btnDireCarte = new JButton("Dire Carte");

		GridBagConstraints gbc_btnDireCarte = new GridBagConstraints();
		gbc_btnDireCarte.insets = new Insets(0, 0, 5, 0);
		gbc_btnDireCarte.fill = GridBagConstraints.BOTH;
		gbc_btnDireCarte.gridx = 0;
		gbc_btnDireCarte.gridy = 11;
		panneauControle.add(btnDireCarte, gbc_btnDireCarte);
		
		btnPiocher = new JButton("Piocher");

		GridBagConstraints gbc_btnPiocher = new GridBagConstraints();
		gbc_btnPiocher.fill = GridBagConstraints.BOTH;
		gbc_btnPiocher.gridx = 0;
		gbc_btnPiocher.gridy = 14;
		panneauControle.add(btnPiocher, gbc_btnPiocher);

		this.panneauCartes = new JPanel();
		this.panneauCartes.setPreferredSize(new Dimension(1000, 150));
		panneauCartes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Votre main", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		FlowLayout flowLayout = (FlowLayout) panneauCartes.getLayout();
		panneauCartes.setBackground(new Color(62, 62, 62));
		panneauPrincipal.add(panneauCartes, BorderLayout.SOUTH);
		

		this.panneauTalon = new JPanel();
		panneauTalon.setBackground(new Color(247, 104, 44));
		panneauPrincipal.add(panneauTalon, BorderLayout.CENTER);
		
		panneauOrdi = new JPanel();
		panneauPrincipal.add(panneauOrdi, BorderLayout.NORTH);

		// On affiche la main du joueur
		for (Carte c : this.partie.getJoueurs()[0].getMainJoueur().getListeCartes()) {
			GUICarte carteG = new GUICarte(c, this.panneauCartes, this, 70, 95);
			this.cartesJoueur.add(carteG);
			this.panneauCartes.add(carteG);
		}
		GridBagLayout gbl_panneauTalon = new GridBagLayout();
		gbl_panneauTalon.columnWidths = new int[]{855, 0};
		gbl_panneauTalon.rowHeights = new int[]{618, 0};
		gbl_panneauTalon.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panneauTalon.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panneauTalon.setLayout(gbl_panneauTalon);
		GridBagConstraints gbc_carte = new GridBagConstraints();
		gbc_carte.gridx = 0;
		gbc_carte.gridy = 0;
		
		GUICarte carte = new GUICarte(this.partie.getTalon().getHead(), this.panneauTalon, this);
		this.panneauTalon.add(carte, gbc_carte);
		
		for(Joueur j : this.partie.getJoueurs()) {
			if(j instanceof JoueurVirtuel) {
				GUIJoueurVirtuel joueurG = new GUIJoueurVirtuel(j, this);
				this.listeJoueurs.add(joueurG);
				this.panneauOrdi.add(joueurG);
			}
		}
		
		this.btnPiocher.setEnabled(false);
		
		
	}
	
	/**
	 * Gère les modifications de l'interface graphique en fonction de l'événement appelé.
	 * @param objet Objet qui envoie la notification.
	 * @param evenement
	 */
	public void action(Observable objet, Evenement evenement) {
		switch(evenement) {
		
		case piocher:
			if(objet instanceof JoueurReel) {
				this.rafraichirMain();
			}
			this.rafraichirOrdi();
			break;
		case carteJouee:
			this.rafraichirMain();
			this.rafraichirTalon();
			this.rafraichirOrdi();
			break;
		case debutTour:
			if(partie.getJoueurActif() instanceof JoueurReel) {
				this.btnPiocher.setEnabled(true);
			}
			this.rafraichirOrdi();
			break;
		case finTour:
			if(partie.getJoueurActif() instanceof JoueurReel) {
				this.btnPiocher.setEnabled(false);
			}
			break;
		case fin:
			GUIFenetreVictoire victoire = new GUIFenetreVictoire(this.partie.getJoueurActif());
		}
	}
	
	/**
	 * Ajoute les Action listeners aux différents composants de l'interface graphique.
	 */
	public void ajoutControle() {
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIView.this.setLastInput("piocher");
			}
		});
		
		btnDireCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIView.this.setLastInput("carte");
			}
		});
		
		btnDireContreCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIView.this.setLastInput("contre carte");
			}
		});
	}
	
	/**
	 * Méthode qui permet de réactualiser la main du joueur.
	 */
	public void rafraichirMain() {
		this.panneauCartes.removeAll();

		this.cartesJoueur.clear();
		
		for (Carte c : this.partie.getJoueurs()[0].getMainJoueur().getListeCartes()) {
			GUICarte carteG = new GUICarte(c, this.panneauCartes, this, 70,95);
			this.cartesJoueur.add(carteG);
			this.panneauCartes.add(carteG);
		}
		this.panneauCartes.repaint();
		this.panneauCartes.revalidate();
	}
	
	/**
	 * Méthode qui permet de rafraichir le talon de la partie.
	 */
	public void rafraichirTalon() {
		this.panneauTalon.removeAll();
		this.panneauTalon.add(new GUICarte(this.partie.getTalon().getHead(), this.panneauTalon, this));
		this.panneauTalon.repaint();
		this.panneauTalon.revalidate();
	}
	
	public void rafraichirOrdi() {
		for(int i = 0; i < this.listeJoueurs.size(); i++) {
			this.listeJoueurs.get(i).rafraichir();
		}
	}

	/**
	 * Retourne une référence vers le JFrame principal.
	 * @return
	 */
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Modifie la dernière action de l'utilisateur. Il réveille tout thread en attente de celui-ci.
	 */
	public void setLastInput(Object arg) {
		this.partie.getControleur().setLastInput(arg);
	}

	/**
	 * Retourne une liste des JPanel correspondant aux cartes du joueur.
	 * @return
	 */
	public ArrayList<GUICarte> getCartesJoueur() {
		return cartesJoueur;
	}

	public void setCartesJoueur(ArrayList<GUICarte> cartesJoueur) {
		this.cartesJoueur = cartesJoueur;
	}
	
	public JPanel getPanneauTalon() {
		return panneauTalon;
	}


	public void setPanneauTalon(JPanel panneauTalon) {
		this.panneauTalon = panneauTalon;
	}


	public JPanel getPanneauCartes() {
		return panneauCartes;
	}


	public void setPanneauCartes(JPanel panneauCartes) {
		this.panneauCartes = panneauCartes;
	}


	public ArrayList<GUIJoueurVirtuel> getListeJoueurs() {
		return listeJoueurs;
	}


	public void setListeJoueurs(ArrayList<GUIJoueurVirtuel> listeJoueurs) {
		this.listeJoueurs = listeJoueurs;
	}


	public JPanel getPanneauControle() {
		return panneauControle;
	}


	public void setPanneauControle(JPanel panneauControle) {
		this.panneauControle = panneauControle;
	}


	public JPanel getPanneauOrdi() {
		return panneauOrdi;
	}


	public void setPanneauOrdi(JPanel panneauOrdi) {
		this.panneauOrdi = panneauOrdi;
	}
	

	public Partie getPartie() {
		return partie;
	}


	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
	public JPanel getPanneauInfo() {
		return this.panneauInfo;
	}


	public void setPanneauInfo(JPanel panneau) {
		this.panneauInfo = panneau;
	}
}

