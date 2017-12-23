package view.interfaceConsole;

import controleur.Controleur;
import model.Joueur;


public class InterfaceConsoleInitJoueurs extends InterfaceConsole {

	public InterfaceConsoleInitJoueurs(Controleur ctrl, Joueur joueurCourant, Thread th) {
		super(ctrl);
		setJoueurCourant(joueurCourant);
		setThread(th);
	}
	
	
	@Override
	public void run() {
		
	}

}
