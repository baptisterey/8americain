package view.interfaceConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import controleur.Controleur;
import model.JoueurArtificiel;
import model.Message;
import view.IHM;
import model.Carte;
import model.ErreurCarteInposable;
import model.Jeu;
import model.Joueur;

public class InterfaceConsole extends IHM implements Runnable {

	private Thread th;
	private Joueur joueurCourant;

	public InterfaceConsole(Controleur ctrl) {
		super(ctrl);
	}

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public Joueur getJoueurCourant() {
		return this.joueurCourant;
	}

	protected Thread getThread() {
		return th;
	}

	protected void setThread(Thread th) {
		this.th = th;
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
			choixok = true;

			Integer choix = lireInteger("Votre choix :");

			if (choix != null) {
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
			}

		} while (!choixok);

	}



	protected Integer lireInteger(String msg) {

		String numString = lireChaine(msg);
		if (numString != null) {
			boolean conversionok;
			do {
				conversionok = true;
				try {
					return Integer.parseInt(numString);
				} catch (NumberFormatException e) {
					numString = lireChaine(msg);
					if (numString == null) {
						return null;
					}
					conversionok = false;
				}
			} while (!conversionok);
		}

		return null;
	}

	protected String lireChaine(String msg) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(msg);
		while (!th.isInterrupted()) {
			try {
				if (stdin.ready()) {
					String resultat = stdin.readLine();
					return resultat;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public boolean annoncer(Joueur joueurCourant) {
		String annonce = lireChaine("Votre annonce :");
		if (annonce != null) {
			Jeu.getInstance().annoncer(joueurCourant, annonce);
			return true;
		}
		return false;
	}

	public void jouerTour(Joueur joueurCourant) {
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

		boolean choixok;
		do {
			Integer choix = lireInteger("Choisir action :");

			choixok = true;
			if (choix != null) {
				switch (choix) {
				case 0:
					if (annoncer(joueurCourant)) {
						choixok = false;
					}
					break;

				case 1:
					joueurCarte(joueurCourant);
					break;

				default:
					choixok = false;
				}

			}
		} while (!choixok);
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
						+ ((Message) msg).getJoueurVictime().getPseudo());
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
						+ " pioche deux cartes grâce à un Contre Carte réussi de "
						+ ((Message) msg).getJoueurCourant().getPseudo() + "!");
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
				commencerThread(new InterfaceConsoleEffetChangerCouleur(getControleur(),
						((Message) msg).getJoueurCourant(), ((Message) msg).getEffetAvecInputEnCours(), th));
				break;

			case choixDonnerCarte:
				commencerThread(new InterfaceConsoleEffetDonner(getControleur(), ((Message) msg).getJoueurCourant(),
						((Message) msg).getEffetAvecInputEnCours(), th));
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
				System.out.println(
						((Message) msg).getJoueurCourant().getPseudo() + " vient de poser sa dernière carte! Bravo!");
				break;

			case afficherTour:
				System.out.println("------- TOUR DE " + ((Message) msg).getJoueurCourant().getPseudo() + " -------");
				break;

			case tourJoueurHumain:
				this.joueurCourant = ((Message) msg).getJoueurCourant();
				commencerThread(this);
				break;

			case initJoueurs:
				commencerThread(new InterfaceConsoleInitJoueurs(getControleur(), getThread()));
				break;

			case finTourJoueurHumain:
				arreterThread();
				break;

			case annonceCarte:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " a bien annoncé Carte!");
				break;

			case nouvelleManche:
				System.out.println("---------- MANCHE N°" + ((Message) msg).getNumeroManche() + " ----------");
				break;

			case finPartie:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + "gagne la partie!");
				break;

			default:
				System.out.println("MESSAGE NON PRIS EN CHARGE : " + ((Message) msg).getType().toString());
				break;
			}
		}

	}

	private void commencerThread(Runnable runnable) {
		arreterThread();
		th = new Thread(runnable);
		th.start();
	}

	private void arreterThread() {
		if (th != null) {
			th.interrupt();
			try {
				th.join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
		jouerTour(joueurCourant);
	}

}
