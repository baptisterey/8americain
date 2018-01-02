package view.interfaceGraphique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

import controleur.Controleur;
import model.Carte;
import model.Jeu;
import model.Message;
import view.IHM;


import javax.swing.JPanel;

public class InterfaceGraphique extends IHM {

	public InterfaceGraphique(Controleur ctrl) {
		super(ctrl);
		initialize();
	}

	private JFrame frame;
	JTextArea txtrHistorique;
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 589, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAnnoncer = new JButton("Annoncer");
		btnAnnoncer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnnoncer.setBounds(335, 101, 203, 25);
		frame.getContentPane().add(btnAnnoncer);
		
		JButton btnPiocher = new JButton("Piocher");
		btnPiocher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPiocher.setBounds(335, 139, 203, 25);
		frame.getContentPane().add(btnPiocher);
		
		txtrHistorique = new JTextArea();
		txtrHistorique.setBounds(335, 177, 203, 157);
		frame.getContentPane().add(txtrHistorique);
		
		JButton btnAbandonner = new JButton("Abandonner");
		btnAbandonner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAbandonner.setBounds(374, 339, 123, 14);
		frame.getContentPane().add(btnAbandonner);
		
		//la main du joueur
		JPanel panel = new JPanel(); 
		panel.setBounds(12, 230, 311, 123);
		frame.getContentPane().add(panel);
		//
		
		//le centre avec la défausse
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(62, 139, 214, 78);
		frame.getContentPane().add(panel_1);
		//
		
		//les IA
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 13, 311, 113);
		frame.getContentPane().add(panel_2);
		//
		
		frame.setVisible(true);
	}
	
	public void refreshDisplay(Jeu jeu) {
		
		
		
		
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
				break;

			case initJoueurs:
				
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
