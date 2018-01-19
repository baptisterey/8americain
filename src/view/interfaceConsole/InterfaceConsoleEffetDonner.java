package view.interfaceConsole;

import controleur.Controleur;
import model.Carte;
import model.Jeu;
import model.Joueur;
import model.effets.EffetAvecInput;
import model.effets.ErreurDonneesEffet;

/**
 * Represente la demande faite aupres de l'Utilisateur lorsque ce dernier joue
 * joue une carte e effet DonnerCarte.
 *
 */

public class InterfaceConsoleEffetDonner extends InterfaceConsole implements Runnable {
	
	private EffetAvecInput effet;
	private Joueur joueurCourant;
	
	public InterfaceConsoleEffetDonner(Controleur ctrl, Joueur joueurCourant, EffetAvecInput effet) {
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
	public void run() {
		boolean setDataOk;
		Integer[] data = new Integer[2];
		do {
			setDataOk = true;

			System.out.println("-- CHOIX EFFET DONNER --");

			System.out.println("-- MAIN DE " + getJoueurCourant().getPseudo() + " --");
			for (Carte carte : getJoueurCourant().getMain()) {
				System.out.println("(" + getJoueurCourant().getMain().indexOf(carte) + ")" + carte.toString());
			}

			data[0] = super.lireInteger("Quelle carte donner ? ");
			if (data[0] != null) {
				System.out.println("-- CHOIX DU JOUEUR A QUI DONNER LA CARTE --");
				for (int i = 0; i < Jeu.getInstance().getJoueurs().size() - 1; i++) {
					if (!getJoueurCourant().equals(Jeu.getInstance().getJoueurs().get(i))) {
						System.out.println("(" + i + ")" + Jeu.getInstance().getJoueurs().get(i).getPseudo());
					}
				}

				data[1] = super.lireInteger("A quel joueur ? ");
				if (data[1] != null) {
					try {
						effet.setData(data, getJoueurCourant());
					} catch (ErreurDonneesEffet e) {
						setDataOk = false;
					}
				}

			}

		} while (!setDataOk);
		if (data[0] != null && data[1] != null) {
			getControleur().getJeu().jouerEffetAvecInputEnCours(effet, getJoueurCourant());
		}

	}

}
