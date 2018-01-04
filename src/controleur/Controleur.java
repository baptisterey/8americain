package controleur;

import model.*;
import view.IHM;
import view.interfaceConsole.InterfaceConsole;
import view.interfaceGraphique.InterfaceGraphique;
import view.interfaceGraphique.InterfaceGraphiqueInitJoueurs;
import view.interfaceGraphique.InterfaceGraphiqueInitPartie;
import view.interfaceGraphique.OldInterfaceGraphique;

/**
 * Implémentation du modèle MVC. Ici le Controleur.
 */
public class Controleur {

	private Jeu jeu;

	public Controleur() {
		this.setJeu(Jeu.getInstance());
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	// Methode MAIN du logiciel.
	public static void main(String[] args) {

		Controleur controleur = new Controleur();

		IHM ihm = new InterfaceConsole(controleur);
		controleur.getJeu().addObserver(ihm);

		IHM ihm2 = new InterfaceGraphique(controleur);
		controleur.getJeu().addObserver(ihm2);
		
		controleur.getJeu().initPartie();
	}

}
