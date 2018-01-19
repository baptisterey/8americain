package view.interfaceConsole;

import controleur.Controleur;
import model.Carte;
import model.Joueur;
import model.effets.EffetAvecInput;
import model.effets.ErreurDonneesEffet;

/**
 * Represente la demande faite aupres de l'Utilisateur lorsque ce dernier doit
 * choisir une nouvelle couleur (effet ChangerCouleur).
 */
public class InterfaceConsoleEffetChangerCouleur extends InterfaceConsole implements Runnable {
	/**
	 * L'effet qui doit etre initialiser avant d'etre joue. 
	 */
	private EffetAvecInput effet;
	
	/**
	 * Le joueur qui est entrain de jouer. 
	 */
	private Joueur joueurCourant;

	public InterfaceConsoleEffetChangerCouleur(Controleur ctrl, Joueur joueurCourant, EffetAvecInput effet) {
		super(ctrl);
		setJoueurCourant(joueurCourant);
		this.effet = effet;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	@Override
	/**
	 * Demande e l'utilisateur une nouvelle couleur, initialise l'EffetAvecInput
	 * <<effet>> avec cette donnee et appelle la methode jouerEffetAvecInputEnCours
	 * de Jeu. 
	 * Si le thread est interrompu, ne fait rien.
	 */
	public void run() {
		boolean setDataOk;
		Integer[] data = new Integer[2];
		do {
			setDataOk = true;

			System.out.println("-- CHOIX CHANGER COULEUR --");
			for (int i = 0; i < Carte.COULEURS.length; i++) {
				System.out.println("(" + i + ")" + Carte.COULEURS[i]);
			}

			data[0] = super.lireInteger("Choisir couleur : ");
			if (data[0] != null) {

				try {
					effet.setData(data, getJoueurCourant());
				} catch (ErreurDonneesEffet e) {
					setDataOk = false;
				}
			}
		} while (!setDataOk);

		if (data[0] != null) {
			getControleur().getJeu().jouerEffetAvecInputEnCours(effet, getJoueurCourant());
		}
	}

}
