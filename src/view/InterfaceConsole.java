package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import controleur.Controleur;
import model.JoueurArtificiel;
import model.Message;
import model.Carte;
import model.ErreurCarteInposable;
import model.Jeu;
import model.Joueur;

public class InterfaceConsole extends IHM {

	private Scanner sc;

	private boolean actif = true;
	private boolean tourDuJoueurHumain = false;

	public InterfaceConsole(Controleur ctrl) {
		super(ctrl);
	}

	public void joueurCarte(Joueur joueurCourant) {
		String carteDefausse = "Carte sommet défausse : ";
		if (Jeu.getInstance().getDefausse().isEmpty()) {
			carteDefausse += "aucune!";

		} else {
			carteDefausse += Jeu.getInstance().getDefausse().get(Jeu.getInstance().getDefausse().size() - 1);
		}
		System.out.println(carteDefausse);

		System.out.println("-- MAIN DE " + joueurCourant.getPseudo() + " --");
		for (Carte carte : joueurCourant.getMain()) {
			System.out.println(" * (" + joueurCourant.getMain().indexOf(carte) + ") " + carte.toString());
		}
		System.out.println("=====================");
		System.out.println("(-1)Piocher une carte");

		Carte carte = null;
		boolean choixok;
		do {
			System.out.print("Votre choix :");

			sc = new Scanner(System.in);

			int choix = sc.nextInt();
			choixok = true;

			switch (choix) {
			case -1:
				carte = null;
				break;

			default:
				try {
					carte = joueurCourant.getMain().get(choix);
				} catch (IndexOutOfBoundsException e) {
					System.out.println("Choix improbable!");
					choixok = false;
				}
			}
			if (choixok) {
				try {
					getControleur().getJeu().jouerCarte(joueurCourant, carte);
				} catch (ErreurCarteInposable e) {
					System.out.println(carte.toString() + " n'a pas pu être posé(e)!");
					choixok = false;
				}
			}

		} while (!choixok);

	}

	public void initJoueurs() {
		System.out.println("---- CREATION AUTO DES JOUEURS (POUR TESTER LE RESTE) ----");
		this.getControleur().getJeu().getJoueurs().clear();
		this.getControleur().getJeu().getJoueurs().add(new Joueur("Civetdelapin"));
		this.getControleur().getJeu().getJoueurs().add(new JoueurArtificiel("AI_1", 0));
		this.getControleur().getJeu().getJoueurs().add(new JoueurArtificiel("AI_2", 1));
		this.getControleur().getJeu().getJoueursInitiation().clear();
		for (int i = 0; i < this.getControleur().getJeu().getJoueurs().size(); i++) {
			this.getControleur().getJeu().getJoueursInitiation().add(this.getControleur().getJeu().getJoueurs().get(i));
		}
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("---- CREATION DU JOUEUR ----");
		 * System.out.print("Entrer votre nom : "); String nom = sc.nextLine(); Joueur j
		 * = new Joueur(nom); this.getControleur().getJeu().getJoueurs().add(j);
		 * System.out.println("---- CREATION DES JOUEURS ARTIFICELS ----");
		 * System.out.print("Combien de joueurs artificiels ? "); int nbJoueur =
		 * sc.nextInt(); int strategie; for (int i = 1 ; i <= nbJoueur ; i++) {
		 * 
		 * System.out.print("Entrer nom joueur"+i+": ");
		 * 
		 * nom = sc.next();
		 * 
		 * System.out.print("Entrer stratÃ©gie joueur"
		 * +i+" (taper 0 pour passif, 1 pour agrÃ©ssif) :");
		 * 
		 * strategie = sc.nextInt();
		 * 
		 * j = new JoueurArtificiel(nom, strategie);
		 * 
		 * 
		 * this.getControleur().getJeu().getJoueurs().add(j); } for (int i = 0 ; i <
		 * this.getControleur().getJeu().getJoueurs().size() ; i++) {
		 * this.getControleur().getJeu().getJoueursInitiation().add(this.getControleur()
		 * .getJeu().getJoueurs().get(i)); }
		 */

		this.getControleur().getJeu().commencerPartie();
	}

	public int[] choixIndexDonner(Joueur joueurCourant) {
		int[] data = new int[2];
		System.out.println("-- CHOIX EFFET DONNER --");

		System.out.println("-- MAIN DE " + joueurCourant.getPseudo() + " --");
		for (Carte carte : joueurCourant.getMain()) {
			System.out.println("(" + joueurCourant.getMain().indexOf(carte) + ")" + carte.toString());
		}

		data[0] = readInt("Choisir index Carte : ");

		System.out.println("-- CHOIX DU JOUEUR A QUI DONNER LA CARTE --");
		for (int i = 0; i < Jeu.getInstance().getJoueurs().size() - 1; i++) {
			if (!joueurCourant.equals(Jeu.getInstance().getJoueurs().get(i))) {
				System.out.println("(" + i + ")" + Jeu.getInstance().getJoueurs().get(i).getPseudo());
			}
		}

		data[1] = readInt("Choisir index Joueur : ");

		return data;
	}

	public int[] choixChangerCouleur(Joueur joueurCourant) {

		System.out.println("-- CHOIX CHANGER COULEUR --");
		for (int i = 0; i < Carte.COULEURS.length; i++) {
			System.out.println("(" + i + ")" + Carte.COULEURS[i]);
		}

		int couleur = readInt("Choisir couleur : ");

		int[] data = new int[1];
		data[0] = couleur;
		return data;
	}

	private int readInt(String msg) {
		int num = 0;
		boolean loop = true;

		while (loop) {
			try {
				System.out.print(msg);
				num = sc.nextInt();
				loop = false;
			} catch (InputMismatchException e) {

			}
		}
		return num;
	}

	public int getChoixAction(Joueur joueurCourant) {
		System.out.println("(0) Annoncer");
		System.out.println("(1) Jouer carte");
		System.out.println("=====================");
		return readInt("Choisir action : ");
	}

	public void annoncer(Joueur joueurCourant) {
		System.out.print("Votre annonce :");

		sc = new Scanner(System.in);
		Jeu.getInstance().annoncer(joueurCourant, sc.nextLine());

	}

	public void jouerTour(Joueur joueurCourant) {
		tourDuJoueurHumain = true;

		System.out.println("---- A TOI DE JOUER ----");
		for (Joueur joueur : getControleur().getJeu().getJoueurs()) {
			if (joueur != joueurCourant) {
				System.out.println(joueur.getPseudo() + " possède " + joueur.getMain().size() + " carte(s)!");
			}
		}
		System.out.println("Carte sommet défausse : " + Jeu.getInstance().getDefausse().getLast());
		System.out.println("-- MAIN DE " + joueurCourant.getPseudo() + " --");
		for (Carte carte : joueurCourant.getMain()) {
			System.out.println(" * " + carte.toString());
		}
		System.out.println("===== ACTION =====");
		System.out.println("(0) Annoncer");
		System.out.println("(1) Jouer ou piocher une carte");
		System.out.println("------------------");

		while (tourDuJoueurHumain) {
			boolean choixok;
			do {
				System.out.print("Choisir action :");

				sc = new Scanner(System.in);
				int choix = sc.nextInt();
				choixok = true;

				switch (choix) {
				case 0:
					annoncer(joueurCourant);
					break;

				case 1:
					joueurCarte(joueurCourant);
					break;

				default:
					choixok = false;
				}

			} while (!choixok);
		}
	}

	@Override
	public void update(Observable jeu, Object msg) {
		if (msg instanceof Message) {
			switch (((Message) msg).getType()) {
			case effetAttaque:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " oblige "
						+ ((Message) msg).getJoueurVictime().getPseudo() + " à piocher "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s)!");
				break;

			case effetClassique:
				break;

			case effetRejouer:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " rejoue!");
				break;

			case effetSauterTour:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " empéche "
						+ ((Message) msg).getJoueurVictime().getPseudo() + " de jouer!");
				break;

			case effetContrerChangerCouleur:
				System.out.println(
						((Message) msg).getJoueurCourant().getPseudo() + " a arreté une attaque et a choisi la couleur "
								+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetChangerCouleur:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " a choisi la couleur "
						+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetDonner:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " ajoute un(e) "
						+ ((Message) msg).getCarteADonner().toString() + " dans la main de "
						+ ((Message) msg).getJoueurVictime());
				break;

			case effetModeAttaque:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " ajoute "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s) au tas d'attaque!");
				break;

			case effetChangerSensJeu:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " inverse le sens de jeu!");
				break;

			case nePeutPasJouer:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " ne peut pas jouer!");
				break;

			case joueurAnnonce:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " dit : \""
						+ ((Message) msg).getAnnonce() + "\"!");
				break;

			case annonceContreCarteReussi:
				System.out.println(((Message) msg).getJoueurVictime().getPseudo()
						+ " pioche deux cartes grâce à un Contre Carte réussi de " + ((Message) msg).getJoueurCourant()
						+ "!");
				break;

			case annonceContreCarteEchoue:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Contre Carte sans aucune raison!");
				break;

			case annonceInconnue:
				System.out.println("Rien ne se passe!");
				break;

			case piocherCarte:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " pioche "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s)!");
				break;

			case choixChangerCouleur:
				System.out.println("CHOIX CHANGER COULEUR A FAIRE");
				// TODO
				break;
			case choixDonnerCarte:
				System.out.println("CHOIX DONNER CARTE");
				// TODO
				break;
			case cartePosee:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " pose un(e) "
						+ ((Message) msg).getCarteADonner().toString() + "!");
				break;
			case annonceCarteTropTot:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Carte trop tôt!");
				break;
			case joueurAFiniManche:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Carte trop tôt!");
				break;
			case afficherTour:
				System.out.println("------- TOUR DE " + ((Message) msg).getJoueurCourant().getPseudo() + " -------");
				break;
			case tourJoueurHumain:
				jouerTour(((Message) msg).getJoueurCourant());
				break;
			case initJoueurs:
				initJoueurs();
				break;
			case finTourJoueurHumain:
				tourDuJoueurHumain = false;
				sc.reset();
				break;

			default:
				System.out.println("MESSAGE NON PRIS EN CHARGE : " + ((Message) msg).getType().toString());
				break;
			}
		}

	}

}
