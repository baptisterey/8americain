package view.interfaceGraphique;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;

import controleur.Controleur;
import model.Carte;
import model.ErreurCarteInposable;
import model.Jeu;
import model.Joueur;
import model.JoueurArtificiel;
import model.Message;
import model.effets.EffetAvecInput;
import model.effets.ErreurDonneesEffet;
import view.IHM;

import javax.swing.JPanel;

public class InterfaceGraphique extends IHM {

	public InterfaceGraphique(Controleur ctrl) {
		super(ctrl);
	}

	private javax.swing.JComboBox<String> comboBoxAnnonces;
	private javax.swing.JButton jButtonAnnoncer;
	private javax.swing.JButton jButtonPiocher;
	private javax.swing.JLabel jLabelManche;
	private javax.swing.JLabel jLabelMethodeVictoire;
	private javax.swing.JLabel jLabelScore;
	private javax.swing.JLabel jLabelTitre;
	private javax.swing.JPanel jPanelDefausse;
	private javax.swing.JPanel jPanelEast;
	private javax.swing.JPanel jPanelImgPioche;

	private javax.swing.JPanel jPanelJoueursArtificiels;
	private javax.swing.JPanel jPanelJoueursArtificielsEditable;
	private javax.swing.JScrollPane jScrollPaneJoueursArtificiels;

	private javax.swing.JPanel jPanelMainDuJoueur;
	private javax.swing.JPanel jPanelMainDuJoueurEditable;
	private javax.swing.JScrollPane jScrollPaneMainDuJoueur;

	private javax.swing.JPanel jPanelWest;
	private javax.swing.JScrollPane jScrollPaneHistorique;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JTextArea jTextAreaHistorique;

	private javax.swing.JFrame fenetreDeJeu;
	private javax.swing.JFrame fenetreChangerCouleur;

	private JFrame fenetreDonnerCarte;
	private JFrame fenetreDonnerJoueur;

	private InterfaceGraphiqueInitPartie initPartie;
	private InterfaceGraphiqueInitJoueurs initJoueurs;

	private javax.swing.JLabel jLabelDefausse;
	private javax.swing.JLabel jLabelTitreDéfausse;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeJouerTour(Joueur joueurCourant) {
		// Généré automatiquement par Windows BUILDER
		fenetreDeJeu = new JFrame();
		fenetreDeJeu.setTitle("8 Américain");

		jPanelWest = new javax.swing.JPanel();
		jPanelJoueursArtificiels = new javax.swing.JPanel();
		jPanelMainDuJoueur = new javax.swing.JPanel();
		jPanelImgPioche = new javax.swing.JPanel();
		jPanelDefausse = new javax.swing.JPanel();
		jPanelEast = new javax.swing.JPanel();
		jButtonPiocher = new javax.swing.JButton();
		comboBoxAnnonces = new javax.swing.JComboBox<>();
		jButtonAnnoncer = new javax.swing.JButton();
		jScrollPaneHistorique = new javax.swing.JScrollPane();
		jTextAreaHistorique = new javax.swing.JTextArea();
		jLabelManche = new javax.swing.JLabel();
		jLabelScore = new javax.swing.JLabel();
		jLabelTitre = new javax.swing.JLabel();
		jLabelMethodeVictoire = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		jSeparator2 = new javax.swing.JSeparator();
		jPanelJoueursArtificielsEditable = new javax.swing.JPanel();
		jPanelMainDuJoueurEditable = new javax.swing.JPanel();
		jScrollPaneMainDuJoueur = new javax.swing.JScrollPane();
		jScrollPaneJoueursArtificiels = new javax.swing.JScrollPane();
		jLabelDefausse = new javax.swing.JLabel();
		jLabelTitreDéfausse = new javax.swing.JLabel();

		fenetreDeJeu.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		fenetreDeJeu.setPreferredSize(new java.awt.Dimension(1300, 768));
		fenetreDeJeu.setResizable(false);
		fenetreDeJeu.setLocation(350, 30);

		jPanelJoueursArtificiels.setBorder(javax.swing.BorderFactory.createTitledBorder("Joueurs Artificiels"));

		jScrollPaneJoueursArtificiels.setViewportView(jPanelJoueursArtificielsEditable);

		javax.swing.GroupLayout jPanelJoueursArtificielsLayout = new javax.swing.GroupLayout(jPanelJoueursArtificiels);
		jPanelJoueursArtificiels.setLayout(jPanelJoueursArtificielsLayout);
		jPanelJoueursArtificielsLayout.setHorizontalGroup(
				jPanelJoueursArtificielsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPaneJoueursArtificiels, javax.swing.GroupLayout.PREFERRED_SIZE, 716,
								javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanelJoueursArtificielsLayout.setVerticalGroup(jPanelJoueursArtificielsLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelJoueursArtificielsLayout
						.createSequentialGroup().addComponent(jScrollPaneJoueursArtificiels,
								javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		jPanelMainDuJoueur.setBorder(javax.swing.BorderFactory.createTitledBorder("Votre main"));

		jScrollPaneMainDuJoueur.setViewportView(jPanelMainDuJoueurEditable);

		javax.swing.GroupLayout jPanelMainDuJoueurLayout = new javax.swing.GroupLayout(jPanelMainDuJoueur);
		jPanelMainDuJoueur.setLayout(jPanelMainDuJoueurLayout);
		jPanelMainDuJoueurLayout.setHorizontalGroup(jPanelMainDuJoueurLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPaneMainDuJoueur,
						javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE));
		jPanelMainDuJoueurLayout.setVerticalGroup(jPanelMainDuJoueurLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPaneMainDuJoueur,
						javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE));

		jPanelDefausse.setBackground(new java.awt.Color(255, 255, 255));
		jPanelDefausse.setPreferredSize(new java.awt.Dimension(165, 0));

		jLabelDefausse.setBackground(new java.awt.Color(204, 0, 102));

		javax.swing.GroupLayout jPanelDefausseLayout = new javax.swing.GroupLayout(jPanelDefausse);
		jPanelDefausse.setLayout(jPanelDefausseLayout);
		jPanelDefausseLayout.setHorizontalGroup(jPanelDefausseLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelDefausseLayout.createSequentialGroup().addContainerGap()
						.addComponent(jLabelDefausse, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
						.addContainerGap()));
		jPanelDefausseLayout
				.setVerticalGroup(jPanelDefausseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabelDefausse, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE));

		jLabelTitreDéfausse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelTitreDéfausse.setText("Défausse");

		javax.swing.GroupLayout jPanelWestLayout = new javax.swing.GroupLayout(jPanelWest);
		jPanelWest.setLayout(jPanelWestLayout);
		jPanelWestLayout.setHorizontalGroup(jPanelWestLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelWestLayout.createSequentialGroup().addContainerGap().addGroup(jPanelWestLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanelWestLayout.createSequentialGroup()
								.addComponent(jPanelMainDuJoueur, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap())
						.addComponent(jPanelJoueursArtificiels, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelWestLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanelWestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jPanelDefausse, javax.swing.GroupLayout.DEFAULT_SIZE, 157,
										Short.MAX_VALUE)
								.addComponent(jLabelTitreDéfausse, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(287, 287, 287)));
		jPanelWestLayout.setVerticalGroup(jPanelWestLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelWestLayout.createSequentialGroup()
						.addComponent(jPanelJoueursArtificiels, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabelTitreDéfausse)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanelDefausse, javax.swing.GroupLayout.PREFERRED_SIZE, 193,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(107, 107, 107).addComponent(jPanelMainDuJoueur, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

		jPanelEast.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations"));

		jButtonPiocher.setText("Piocher");
		jButtonPiocher.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				getControleur().getJeu().jouerCarte(joueurCourant, null); // On pioche une carte
			}
		});

		comboBoxAnnonces.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Carte!", "Contre Carte!" }));

		jButtonAnnoncer.setText("Annoncer");
		jButtonAnnoncer.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				switch (comboBoxAnnonces.getSelectedIndex()) {
				case 0:
					getControleur().getJeu().annoncer(joueurCourant, Jeu.ANNONCE_CARTE);
					break;
				case 1:
					getControleur().getJeu().annoncer(joueurCourant, Jeu.ANNONCE_CONTRE_CARTE);
					break;
				}

			}
		});

		jTextAreaHistorique.setEditable(false);
		jTextAreaHistorique.setColumns(20);
		jTextAreaHistorique.setRows(5);
		jScrollPaneHistorique.setViewportView(jTextAreaHistorique);

		jLabelManche.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabelManche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelManche.setText("Manche : ");

		jLabelScore.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabelScore.setText("Score : ");

		jLabelTitre.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
		jLabelTitre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelTitre.setText("8 Américain");
		jLabelTitre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jLabelMethodeVictoire.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabelMethodeVictoire.setText("Le premier à 500 gagne !");

		javax.swing.GroupLayout jPanelEastLayout = new javax.swing.GroupLayout(jPanelEast);
		jPanelEast.setLayout(jPanelEastLayout);
		jPanelEastLayout.setHorizontalGroup(jPanelEastLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabelTitre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jSeparator1).addComponent(jSeparator2)
				.addGroup(jPanelEastLayout.createSequentialGroup().addContainerGap().addGroup(jPanelEastLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabelManche, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanelEastLayout.createSequentialGroup().addComponent(jLabelScore).addGap(0, 0,
								Short.MAX_VALUE))
						.addComponent(jLabelMethodeVictoire, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanelEastLayout.createSequentialGroup().addGroup(jPanelEastLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanelEastLayout.createSequentialGroup()
										.addComponent(comboBoxAnnonces, 0, 413, Short.MAX_VALUE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButtonAnnoncer, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jButtonPiocher, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jScrollPaneHistorique, javax.swing.GroupLayout.Alignment.TRAILING))
								.addContainerGap()))));
		jPanelEastLayout.setVerticalGroup(jPanelEastLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelEastLayout.createSequentialGroup().addContainerGap().addComponent(jLabelTitre)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabelManche, javax.swing.GroupLayout.PREFERRED_SIZE, 43,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(8, 8, 8)
						.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelScore)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabelMethodeVictoire, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(10, 10, 10)
						.addGroup(jPanelEastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(comboBoxAnnonces, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButtonAnnoncer, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jButtonPiocher, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPaneHistorique, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(79, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(fenetreDeJeu.getContentPane());
		fenetreDeJeu.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jPanelWest, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jPanelEast, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jPanelEast, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jPanelWest, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));

		fenetreDeJeu.pack();

		fenetreDeJeu.setVisible(true);
	}

	private void refreshDisplay(Jeu jeu, Joueur joueurCourant) {

		// Affichage du numero de la manche
		jLabelManche.setText("Manche : " + jeu.getNumManche());

		// Affichage de la méthode de victoire
		switch (jeu.getMethodeCompte()) {
		case Jeu.COMPTE_NEGATIF:
			jLabelMethodeVictoire.setText("Le premier à 100 points perd la partie!");
			break;

		case Jeu.COMPTE_POSITIF:
			jLabelMethodeVictoire.setText("Le premier à 100 points remporte la partie!");
			break;
		}

		// Affichage du Score
		jLabelScore.setText("Score : " + joueurCourant.getScore());

		// Actualisation des joueurs artificiels
		afficherJoueursArtificiels(jeu.getJoueurs());

		// Actualisation de la main du Joueur
		afficherMainJoueur(joueurCourant);

		// Actualisation de la défausse
		String str = "images/cartes/" + Carte.VALEURS[jeu.getDefausse().getLast().getValeur()] + "_"
				+ Carte.COULEURS[jeu.getDefausse().getLast().getCouleur()] + ".png";
		ImageIcon icon = new ImageIcon(new ImageIcon(str).getImage().getScaledInstance(133, 193, Image.SCALE_DEFAULT));

		jLabelDefausse.setIcon(icon);

		jPanelDefausse.revalidate();
		jPanelDefausse.repaint();

		jLabelDefausse.revalidate();
		jLabelDefausse.repaint();
	}

	private void afficherJoueursArtificiels(List<Joueur> joueurs) {
		jPanelJoueursArtificielsEditable.removeAll();

		for (Joueur joueur : joueurs) {
			if (joueur instanceof JoueurArtificiel) {
				jPanelJoueursArtificielsEditable.add(new InterfaceGraphiqueJoueurArtificiel(joueur));
			}
		}

		jPanelJoueursArtificiels.revalidate();
		jPanelJoueursArtificiels.repaint();

		jScrollPaneJoueursArtificiels.revalidate();
		jScrollPaneJoueursArtificiels.repaint();

		jPanelJoueursArtificielsEditable.revalidate();
		jPanelJoueursArtificielsEditable.repaint();

	}

	private void afficherMainJoueur(Joueur joueurCourant) {
		jPanelMainDuJoueurEditable.removeAll();

		for (Carte carte : joueurCourant.getMain()) {
			String str = "images/cartes/" + Carte.VALEURS[carte.getValeur()] + "_" + Carte.COULEURS[carte.getCouleur()]
					+ ".png";
			ImageIcon icon = new ImageIcon(
					new ImageIcon(str).getImage().getScaledInstance(80, 110, Image.SCALE_DEFAULT));// pour gérer la
																									// taille des images
			JButton bouton = new JButton(icon);
			bouton.setBackground(Color.WHITE);
			bouton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						getControleur().getJeu().jouerCarte(joueurCourant, carte);
					} catch (ErreurCarteInposable e1) {
						JOptionPane.showMessageDialog(fenetreDeJeu, carte.toString() + " n'a pas pu être posé(e)!");
					}
				}
			});
			jPanelMainDuJoueurEditable.add(bouton);
		}
	}

	private void menuChangerCouleur(EffetAvecInput effet, Joueur joueurCourant) {
		fenetreChangerCouleur = new JFrame();
		fenetreDeJeu.setEnabled(false);

		fenetreChangerCouleur.setLocation(350, 500);

		fenetreChangerCouleur.setVisible(true);
		fenetreChangerCouleur.setSize(500, 200);

		fenetreChangerCouleur.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.add(new JLabel("Choisir la nouvelle Couleur", JLabel.CENTER), BorderLayout.NORTH);

		JPanel panelImg = new JPanel();

		JButton trefle = new JButton(new ImageIcon("images/iconesCouleurs/trefle.png"));
		trefle.setBackground(Color.WHITE);
		trefle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer[] data = new Integer[1];
				data[0] = Carte.TREFLE;
				try {

					effet.setData(data, joueurCourant);
					getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
				} catch (ErreurDonneesEffet e1) {
					e1.printStackTrace();
				}
			}
		});

		panelImg.add(trefle);

		JButton carreau = new JButton(new ImageIcon("images/iconesCouleurs/carreau.png"));
		carreau.setBackground(Color.WHITE);
		carreau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer[] data = new Integer[1];
				data[0] = Carte.CARREAU;
				try {
					effet.setData(data, joueurCourant);
					getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
				} catch (ErreurDonneesEffet e1) {
					e1.printStackTrace();
				}
			}
		});

		panelImg.add(carreau);

		JButton coeur = new JButton(new ImageIcon("images/iconesCouleurs/coeur.png"));
		coeur.setBackground(Color.WHITE);
		coeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer[] data = new Integer[1];
				data[0] = Carte.COEUR;
				try {
					effet.setData(data, joueurCourant);
					getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
				} catch (ErreurDonneesEffet e1) {
					e1.printStackTrace();
				}
			}
		});

		panelImg.add(coeur);

		JButton pique = new JButton(new ImageIcon("images/iconesCouleurs/pique.png"));
		pique.setBackground(Color.WHITE);
		pique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer[] data = new Integer[1];
				data[0] = Carte.PIC;
				try {
					effet.setData(data, joueurCourant);
					getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
				} catch (ErreurDonneesEffet e1) {
					e1.printStackTrace();
				}
			}
		});

		panelImg.add(pique);

		panel.add(panelImg, BorderLayout.CENTER);

		fenetreChangerCouleur.add(panel);
	}

	private void menuDonnerCarte(EffetAvecInput effet, Joueur joueurCourant) {
		fenetreDonnerCarte = new JFrame();
		fenetreDeJeu.setEnabled(false);
		fenetreDonnerCarte.setVisible(true);
		fenetreDonnerCarte.setSize(500, 200);
		fenetreDonnerCarte.setTitle("Choisir une carte");
		fenetreDonnerCarte.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		panel.add(new JLabel("Choisir votre carte à donner", JLabel.CENTER), BorderLayout.NORTH);

		JPanel panelImg = new JPanel();

		for (Carte carte : joueurCourant.getMain()) {
			String str = "images/cartes/" + Carte.VALEURS[carte.getValeur()] + "_" + Carte.COULEURS[carte.getCouleur()]
					+ ".png";
			ImageIcon icon = new ImageIcon(
					new ImageIcon(str).getImage().getScaledInstance(80, 110, Image.SCALE_DEFAULT));// pour gérer la
																									// taille des images
			JButton bouton = new JButton(icon);
			bouton.setBackground(Color.WHITE);
			bouton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Integer[] data = new Integer[2];
					data[0] = joueurCourant.getMain().indexOf(carte);
					fenetreDonnerJoueur = new JFrame();
					fenetreDonnerCarte.setEnabled(false);
					fenetreDonnerJoueur.setVisible(true);
					fenetreDonnerJoueur.setSize(500, 200);
					fenetreDonnerJoueur.setTitle("Choisir le joueur");

					fenetreDonnerJoueur.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

					JPanel panel2 = new JPanel();
					panel2.setLayout(new BorderLayout());

					panel2.add(new JLabel("Choisir joueur à qui donner la carte", JLabel.CENTER), BorderLayout.NORTH);

					JPanel panelImg2 = new JPanel();

					for (Joueur joueur : Jeu.getInstance().getJoueurs()) {
						if (joueur instanceof JoueurArtificiel) {
							JButton bouton = new JButton(joueur.getPseudo());
							bouton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									data[1] = Jeu.getInstance().getJoueurs().indexOf(joueur);
									try {
										effet.setData(data, joueurCourant);
										getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
									} catch (ErreurDonneesEffet e1) {
										e1.printStackTrace();
									}
								}
							});
							panelImg2.add(bouton);
						}
					}
					panel2.add(panelImg2, BorderLayout.CENTER);
					fenetreDonnerJoueur.add(panel2);
				}
			});
			panelImg.add(bouton);
		}
		panel.add(panelImg, BorderLayout.CENTER);

		fenetreDonnerCarte.add(panel);
	}

	public void update(Observable jeu, Object msg) {
		if (msg instanceof Message) {
			switch (((Message) msg).getType()) {
			case effetAttaque:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " oblige "
						+ ((Message) msg).getJoueurVictime().getPseudo() + " à piocher "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s)!");
				break;

			case effetClassique:
				break;

			case effetRejouer:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " rejoue!");
				break;

			case effetSauterTour:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " empéche "
						+ ((Message) msg).getJoueurVictime().getPseudo() + " de jouer!");
				break;

			case effetContrerChangerCouleur:
				if (!(((Message) msg).getJoueurCourant() instanceof JoueurArtificiel)) {
					fenetreDeJeu.setEnabled(true);
					fenetreChangerCouleur.dispose();
					refreshDisplay(getControleur().getJeu(), ((Message) msg).getJoueurCourant());
				}
				afficherConsole(
						((Message) msg).getJoueurCourant().getPseudo() + " a arreté une attaque et a choisi la couleur "
								+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetChangerCouleur:
				if (!(((Message) msg).getJoueurCourant() instanceof JoueurArtificiel)) {
					fenetreDeJeu.setEnabled(true);
					fenetreChangerCouleur.dispose();
					refreshDisplay(getControleur().getJeu(), ((Message) msg).getJoueurCourant());
				}
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " a choisi la couleur "
						+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetDonner:
				if (!(((Message) msg).getJoueurCourant() instanceof JoueurArtificiel)) {
					fenetreDeJeu.setEnabled(true);
					fenetreDonnerCarte.dispose();
					if (fenetreDonnerJoueur != null) {
						fenetreDonnerJoueur.dispose();
					}
				}
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " ajoute un(e) "
						+ ((Message) msg).getCarteADonner().toString() + " dans la main de "
						+ ((Message) msg).getJoueurVictime().getPseudo());
				break;

			case effetModeAttaque:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " ajoute "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s) au tas d'attaque!");
				break;

			case effetChangerSensJeu:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " inverse le sens de jeu!");
				break;

			case nePeutPasJouer:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " ne peut pas jouer!");
				break;

			case joueurAnnonce:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " dit : \""
						+ ((Message) msg).getAnnonce() + "\"!");
				break;

			case annonceContreCarteReussi:
				afficherConsole(((Message) msg).getJoueurVictime().getPseudo()
						+ " pioche deux cartes grâce à un Contre Carte réussi de "
						+ ((Message) msg).getJoueurCourant().getPseudo() + "!");
				break;

			case annonceContreCarteEchoue:
				refreshDisplay(getControleur().getJeu(), ((Message) msg).getJoueurCourant());
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Contre Carte sans aucune raison!");
				break;

			case annonceInconnue:
				afficherConsole("Rien ne se passe!");
				break;

			case piocherCarte:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " pioche "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s)!");
				break;

			case choixChangerCouleur:
				menuChangerCouleur(((Message) msg).getEffetAvecInputEnCours(), ((Message) msg).getJoueurCourant());
				break;

			case choixDonnerCarte:
				menuDonnerCarte(((Message) msg).getEffetAvecInputEnCours(), ((Message) msg).getJoueurCourant());
				break;

			case cartePosee:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " pose un(e) "
						+ ((Message) msg).getCarteADonner().toString() + "!");
				break;

			case annonceCarteTropTot:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Carte trop tôt!");
				break;

			case joueurAFiniManche:
				afficherConsole(
						((Message) msg).getJoueurCourant().getPseudo() + " vient de poser sa dernière carte! Bravo!");
				break;

			case afficherTour:
				afficherConsole("------- TOUR DE " + ((Message) msg).getJoueurCourant().getPseudo() + " -------");
				break;

			case tourJoueurHumain:
				afficherConsole("------- TOUR DE " + ((Message) msg).getJoueurCourant().getPseudo() + " -------");
				refreshDisplay(getControleur().getJeu(), ((Message) msg).getJoueurCourant());
				break;

			case initJoueurs:
				if (initPartie != null) {
					initPartie.getMenuInitJFrame().dispose();
				}
				initJoueurs = new InterfaceGraphiqueInitJoueurs(getControleur());
				break;

			case finTourJoueurHumain:
				break;

			case annonceCarte:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " a bien annoncé Carte!");
				break;

			case nouvelleManche:
				// afficherConsole("---------- MANCHE N°" + ((Message) msg).getNumeroManche() +
				// " ----------");
				break;

			case finPartie:
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + "gagne la partie!");
				break;

			case initPartie:
				if (fenetreDeJeu != null) {
					fenetreDeJeu.dispose();
				}
				initPartie = new InterfaceGraphiqueInitPartie(getControleur());
				break;

			case debutPartie:
				if (initJoueurs != null) {
					initJoueurs.getMenuInitJFrame().dispose();
				}
				initializeJouerTour(((Message) msg).getJoueurCourant());
				break;

			default:
				afficherConsole("MESSAGE NON PRIS EN CHARGE : " + ((Message) msg).getType().toString());
				break;
			}
		}

	}

	public void afficherConsole(String str) {
		jTextAreaHistorique.append(str + "\n");
	}
}
