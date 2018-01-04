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
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

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

	private JFrame fenetreDeJeu;
	private JTextArea txtrHistorique;
	private JPanel panelMainDuJoueur;
	private JPanel panelCentreDefausse;
	private JPanel panelJoueurArtificiel;
	private JButton btnPiocher;

	private JFrame fenetreChangerCouleur;

	private InterfaceGraphiqueInitPartie initPartie;
	private InterfaceGraphiqueInitJoueurs initJoueurs;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeJouerTour(Joueur joueurCourant) {
		fenetreDeJeu = new JFrame();
		fenetreDeJeu.setBounds(100, 100, 589, 413);
		fenetreDeJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetreDeJeu.getContentPane().setLayout(null);

		JButton btnAnnoncer = new JButton("Annoncer");
		btnAnnoncer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnnoncer.setBounds(335, 101, 203, 25);
		fenetreDeJeu.getContentPane().add(btnAnnoncer);

		btnPiocher = new JButton("Piocher");
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().getJeu().jouerCarte(joueurCourant, null); // On pioche une carte
			}
		});

		btnPiocher.setBounds(335, 139, 203, 25);
		fenetreDeJeu.getContentPane().add(btnPiocher);

		txtrHistorique = new JTextArea();
		txtrHistorique.setBounds(335, 177, 203, 157);
		fenetreDeJeu.getContentPane().add(txtrHistorique);

		JButton btnAbandonner = new JButton("Abandonner");
		btnAbandonner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbandonner.setBounds(374, 339, 123, 14);
		fenetreDeJeu.getContentPane().add(btnAbandonner);

		// la main du joueur
		panelMainDuJoueur = new JPanel();
		panelMainDuJoueur.setBounds(12, 230, 311, 123);
		fenetreDeJeu.getContentPane().add(panelMainDuJoueur);
		//

		// le centre avec la défausse
		panelCentreDefausse = new JPanel();
		panelCentreDefausse.setBounds(62, 139, 214, 78);
		fenetreDeJeu.getContentPane().add(panelCentreDefausse);
		//

		// les IA
		panelJoueurArtificiel = new JPanel();
		panelJoueurArtificiel.setBounds(12, 13, 311, 113);
		fenetreDeJeu.getContentPane().add(panelJoueurArtificiel);
		//

		fenetreDeJeu.setVisible(true);
	}

	public void refreshDisplay(Jeu jeu, Joueur joueurCourant) {
		// On actualise la main du Joueur
		afficherMainJoueur(joueurCourant);

		// On actualise la défausse
		panelCentreDefausse.removeAll();
		panelCentreDefausse.add(new JLabel("Défausse : " + jeu.getDefausse().getLast()));

		// On actualise les joueurs artificiels
		afficherJoueursArtificiels(jeu.getJoueurs());

		fenetreDeJeu.repaint();
	}

	private void afficherMainJoueur(Joueur joueurCourant) {
		panelMainDuJoueur.removeAll();

		for (Carte carte : joueurCourant.getMain()) {
			JButton bouton = new JButton(carte.toString());
			bouton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						getControleur().getJeu().jouerCarte(joueurCourant, carte);
					} catch (ErreurCarteInposable e1) {
						JOptionPane.showMessageDialog(fenetreDeJeu, carte.toString() + " n'a pas pu être posé(e)!");
					}
				}
			});
			panelMainDuJoueur.add(bouton);
		}

	}

	private void afficherJoueursArtificiels(List<Joueur> joueurs) {
		panelJoueurArtificiel.removeAll();

		for (Joueur joueur : joueurs) {
			if (joueur instanceof JoueurArtificiel) {
				panelJoueurArtificiel.add(new InterfaceGraphiqueJoueurArtificiel(joueur));
			}
		}
	}

	private void menuChangerCouleur(EffetAvecInput effet, Joueur joueurCourant) {
		fenetreChangerCouleur = new JFrame();
		fenetreDeJeu.setEnabled(false);
		fenetreChangerCouleur.setVisible(true);
		fenetreChangerCouleur.setSize(400, 400);

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
				afficherConsole(
						((Message) msg).getJoueurCourant().getPseudo() + " a arreté une attaque et a choisi la couleur "
								+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetChangerCouleur:
				fenetreDeJeu.setEnabled(true);
				fenetreChangerCouleur.dispose();
				afficherConsole(((Message) msg).getJoueurCourant().getPseudo() + " a choisi la couleur "
						+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetDonner:
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
				afficherConsole("---------- MANCHE N°" + ((Message) msg).getNumeroManche() + " ----------");
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
		txtrHistorique.append(str + "\n");
	}
}
