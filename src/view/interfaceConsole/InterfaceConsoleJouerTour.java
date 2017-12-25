package view.interfaceConsole;

import controleur.Controleur;
import model.Carte;
import model.ErreurCarteInposable;
import model.Jeu;
import model.Joueur;

public class InterfaceConsoleJouerTour extends InterfaceConsole implements Runnable {
	private Joueur joueurCourant;

	public InterfaceConsoleJouerTour(Controleur ctrl, Joueur joueurCourant) {
		super(ctrl);
		this.joueurCourant = joueurCourant;
	}

	@Override
	public void run() {
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

	private boolean annoncer(Joueur joueurCourant) {
		String annonce = lireChaine("Votre annonce :");
		if (annonce != null) {
			Jeu.getInstance().annoncer(joueurCourant, annonce);
			return true;
		}
		return false;
	}

	private void joueurCarte(Joueur joueurCourant) {
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

}
