package view.interfaceConsole;

import controleur.Controleur;
import model.Carte;
import model.Joueur;
import model.effets.EffetAvecInput;
import model.effets.ErreurDonneesEffet;

public class InterfaceConsoleEffetChangerCouleur extends InterfaceConsole {
	private Joueur joueurCourant;
	private EffetAvecInput effet;

	public InterfaceConsoleEffetChangerCouleur(Controleur ctrl, Joueur joueurCourant,
			EffetAvecInput effet) {
		super(ctrl);
		this.joueurCourant = joueurCourant;
		this.effet = effet;
	}

	@Override
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
					effet.setData(data, joueurCourant);
				} catch (ErreurDonneesEffet e) {
					setDataOk = false;
				}
			}
		} while (!setDataOk);

		if (data[0] != null) {
			getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
		}
	}

}
