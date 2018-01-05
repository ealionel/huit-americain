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
import javax.swing.border.TitledBorder;

import fr.lo02.huitamericain.Carte;
import fr.lo02.huitamericain.Evenement;
import fr.lo02.huitamericain.Joueur;
import fr.lo02.huitamericain.JoueurReel;
import fr.lo02.huitamericain.JoueurVirtuel;
import fr.lo02.huitamericain.Partie;

public class GUIView implements View, Observer, Runnable {

	private JFrame frame;
	private Partie partie;


	private JPanel panneauTalon;
	private JPanel panneauCartes;
	private JPanel panneauPrincipal;

	private ArrayList<GUICarte> cartesJoueur;
	private ArrayList<GUIJoueurVirtuel> listeJoueurs;

	private Object lastInput; // Dernière commande réalisée par l'utilisateur
	
	

	private JPanel panneauControle;
	private JPanel panneauOrdi;
	

	private JButton btnPiocher;
	private JButton btnDireCarte;
	private JButton btnDireContreCarte;
	private JLabel lblNewLabel;

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
		this.frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		this.panneauPrincipal = new JPanel();
		panneauPrincipal.setBounds(0, 0, 983, 661);
		frame.getContentPane().add(panneauPrincipal);
		panneauPrincipal.setLayout(new BorderLayout(0, 0));
		
		panneauControle = new JPanel();
		panneauControle.setSize(new Dimension(50, 500));
		panneauControle.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panneauPrincipal.add(panneauControle, BorderLayout.WEST);
		GridBagLayout gbl_panneauControle = new GridBagLayout();
		gbl_panneauControle.columnWidths = new int[]{117, 0};
		gbl_panneauControle.rowHeights = new int[] {100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40, 40, 0, 37, 60};
		gbl_panneauControle.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panneauControle.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panneauControle.setLayout(gbl_panneauControle);
		
		lblNewLabel = new JLabel("Vos actions");
		lblNewLabel.setMaximumSize(new Dimension(54, 100));
		lblNewLabel.setMinimumSize(new Dimension(54, 100));
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 9;
		panneauControle.add(lblNewLabel, gbc_lblNewLabel);
		
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
		panneauCartes.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Votre main", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		FlowLayout fowLayout = (FlowLayout) panneauCartes.getLayout();
		panneauCartes.setBackground(new Color(128, 128, 128));
		panneauPrincipal.add(panneauCartes, BorderLayout.SOUTH);
		

		this.panneauTalon = new JPanel();
		panneauTalon.setBackground(new Color(154, 205, 50));
		panneauPrincipal.add(panneauTalon, BorderLayout.CENTER);
		
		panneauOrdi = new JPanel();
		panneauPrincipal.add(panneauOrdi, BorderLayout.NORTH);

		// On affiche la main du joueur
		for (Carte c : this.partie.getJoueurs()[0].getMainJoueur().getListeCartes()) {
			GUICarte carteG = new GUICarte(c, this.panneauCartes, this, 70, 95);
			this.cartesJoueur.add(carteG);
			this.panneauCartes.add(carteG);
		}
		
		//Ajoute la carte du joueur au milieu
		this.panneauTalon.add(new GUICarte(this.partie.getTalon().getHead(), this.panneauTalon, this));
		
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
	 * @param objet
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
			if(objet instanceof JoueurReel){
				this.rafraichirMain();
			}
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
	 * Renvoie le nom de la dernière action faite par l'utilisateur
	 */
	public Object getLastInput() {
		return this.lastInput;
	}

	/**
	 * Modifie la dernière action de l'utilisateur. Il réveille tout thread en attente de celui-ci.
	 */
	public void setLastInput(Object arg) {
		this.lastInput = arg;
		
		synchronized(this) {
			notifyAll();
		}
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
}

