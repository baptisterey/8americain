package view.interfaceConsole;

import controleur.Controleur;

public class InterfaceConsoleInitPartie extends InterfaceConsole implements Runnable{

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
		
		getControleur().getJeu().initJoueurs();
		//Integer choix = lireInteger("Choix action : ");
		//System.out.println("Crédits : REY Baptiste & LORIOT Thomas");
		
	}

}
