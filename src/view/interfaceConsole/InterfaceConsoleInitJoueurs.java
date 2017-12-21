package view.interfaceConsole;

import controleur.Controleur;
import model.Joueur;


public class InterfaceConsoleInitJoueurs extends InterfaceConsole {

	public InterfaceConsoleInitJoueurs(Controleur ctrl, Joueur joueurCourant) {
		super(ctrl);
		setJoueurCourant(joueurCourant);
	}
	
	
	@Override
	public void run() {
		
	}

}
