package view.interfaceConsole;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controleur.Controleur;

public class InterfaceConsoleInitPartie extends InterfaceConsole implements Runnable {

	public InterfaceConsoleInitPartie(Controleur ctrl) {
		super(ctrl);
	}

	@Override
	public void run() {
		System.out.println("=================== 8 Américain ====================");
		System.out.println("<< Zéro carte dans la main et c'est super bien ! >>");
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
					try {
						File f = new File ("./src/regle.txt");
					    FileReader fr = new FileReader (f);
					    BufferedReader br = new BufferedReader (fr);
						try
					    {
					        String line = br.readLine();
					 
					        while (line != null)
					        {
					            System.out.println (line);
					            line = br.readLine();
					        }
					 
					        br.close();
					        fr.close();
					    }
						catch (IOException exception)
					    {
					        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
					    }
					} catch (FileNotFoundException e) {
						System.out.println("Le fichier n'a pas été trouvé.");
					}
					break;

				case 2:
					break;

				case 3:
					break;

				default:
					choixok = false;
				}
			}
		} while (!choixok);

		if (choix != null && choix == 1) {
			getControleur().getJeu().initJoueurs();
		}

		// Integer choix = lireInteger("Choix action : ");
		// System.out.println("Crédits : REY Baptiste & LORIOT Thomas");

	}

}
