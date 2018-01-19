package view.interfaceConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controleur.Controleur;
import model.Jeu;
import model.variantes.Basique;
import model.variantes.Minimale;
import model.variantes.Monclar;
import model.variantes.Variante;

/**
 * Représente l'affichage de la console au lancement de l'application.
 * permet de commencer une partie en précisant les règles de celle ci.
 *
 */

public class InterfaceConsoleInitPartie extends InterfaceConsole implements Runnable {

	public InterfaceConsoleInitPartie(Controleur ctrl) {
		super(ctrl);
	}

	@Override
	public void run() {
		System.out.println("=================== 8 Américain ====================");
		System.out.println("<< Zéro carte dans ta main et c'est super bien ! >>");
		System.out.println("====================================================");
		System.out.println("(0) Lire les règles");
		System.out.println("(1) Commencer une partie");
		System.out.println("(2) Regarder les crédits");
		System.out.println("(3) Quitter le jeu");
		System.out.println("------------------");

		Integer choix;
		boolean choixok = true;
		do {
			choixok = true;
			choix = lireInteger("Choisir action :");

			if (choix != null) {
				switch (choix) {
				case 0:
					afficherFichier(new File("regles.txt"));
					choixok = false;
					break;

				case 1:
					initPartie();
					break;

				case 2:
					afficherFichier(new File("credits.txt"));
					choixok = false;
					break;

				case 3:
					System.exit(0);
					break;

				default:
					choixok = false;
				}
			}
		} while (!choixok);

	}

	/**
	 * Initialise la partie en demandant le nombre de cartes, la méthode de comptage
	 * et la variante. Si le Thread est interrompu ne fait rien, les modifications
	 * ne sont appliquées qu'aprés validation finale de l'utilisateur.
	 */
	private void initPartie() {
		int deck = Jeu.DECK_52_CARTES;
		int methodeComptage = Jeu.COMPTE_POSITIF;
		Variante variante = new Basique();

		boolean operationInterompue = false;
		boolean choixok;

		// System.out.println("CREATION AUTO DE LA VARIANTE ET DU DECK POUR TESTER,
		// CHANGER DANS InterfaceConsoleInitPartie");

		System.out.println("==== CHOIX DU MODE DE JEU ====");

		// CHOIX DU DECK
		System.out.println("--- Choix du Deck ---");
		System.out.println("(0) Deck à 32 Cartes");
		System.out.println("(1) Deck à 52 Cartes");
		System.out.println("------------------");
		do {
			choixok = true;
			Integer choix = super.lireInteger("Choix du deck : ");
			if (choix != null) {
				switch (choix) {
				case 0:
					deck = Jeu.DECK_32_CARTES;
					break;
				case 1:
					deck = Jeu.DECK_52_CARTES;
					break;
				default:
					choixok = false;
					break;
				}
			} else {
				operationInterompue = true;
			}

		} while (!choixok);

		if (!operationInterompue) {

			// CHOIX DE LA METHODE DE COMPTAGE
			System.out.println("--- Choix du Mode de Comptage ---");
			System.out.println("(0) Compte Positif");
			System.out.println("(1) Compte Negatif");
			System.out.println("------------------");
			do {
				choixok = true;
				Integer choix = super.lireInteger("Choix du mode : ");
				if (choix != null) {
					switch (choix) {
					case 0:
						methodeComptage = Jeu.COMPTE_POSITIF;
						break;
					case 1:
						methodeComptage = Jeu.COMPTE_NEGATIF;
						break;
					default:
						choixok = false;
						break;
					}
				} else {
					operationInterompue = true;
				}

			} while (!choixok);
		}

		if (!operationInterompue) {

			// CHOIX DE LA VARIANTE
			System.out.println("--- Choix de la Variante ---");
			System.out.println("(0) Basique");
			System.out.println("(1) Minimale");
			System.out.println("(2) Monclar");
			System.out.println("------------------");
			do {
				choixok = true;
				Integer choix = super.lireInteger("Choix de la variante : ");
				if (choix != null) {
					switch (choix) {
					case 0:
						variante = new Basique();
						break;

					case 1:
						variante = new Minimale();
						break;

					case 2:
						variante = new Monclar();
						break;
					default:
						choixok = false;
						break;
					}
				} else {
					operationInterompue = true;
				}

			} while (!choixok);
		}

		if (!operationInterompue) {
			getControleur().getJeu().setMethodeCompte(methodeComptage);
			getControleur().getJeu().setVariante(variante);
			getControleur().getJeu().setNbCarteDeck(deck);

			getControleur().getJeu().initJoueurs();
		}
	}

	/**
	 * Lit le fichier et affiche son contenue dans la console.
	 * 
	 * @param f
	 *            Le fichier à afficher.
	 */
	private void afficherFichier(File f) {
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			try {
				String line = br.readLine();

				while (line != null) {
					System.out.println(line);
					line = br.readLine();
				}

				br.close();
				fr.close();
			} catch (IOException exception) {
				System.out.println("Erreur lors de la lecture : " + exception.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas été trouvé.");
		}

	}

}
