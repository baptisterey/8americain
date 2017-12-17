package view.interfaceConsole;

import controleur.Controleur;
import model.Carte;
import model.Jeu;
import model.Joueur;
import model.effets.EffetAvecInput;
import model.effets.ErreurDonneesEffet;

public class InterfaceConsoleEffetDonner implements Runnable {
	private Joueur joueurCourant;
	private EffetAvecInput effet;
	InterfaceConsole interfaceConsole;
	
	public InterfaceConsoleEffetDonner(InterfaceConsole interfaceConsole,Joueur joueurCourant, EffetAvecInput effet) {
		this.joueurCourant = joueurCourant;
		this.effet = effet;
		this.interfaceConsole = interfaceConsole;
	}
	
	
	@Override
	public void run() {
		boolean setDataOk;
		do {
			setDataOk = true;
			int[] data = new int[2];
			System.out.println("-- CHOIX EFFET DONNER --");

			System.out.println("-- MAIN DE " + joueurCourant.getPseudo() + " --");
			for (Carte carte : joueurCourant.getMain()) {
				System.out.println("(" + joueurCourant.getMain().indexOf(carte) + ")" + carte.toString());
			}

			data[0] = interfaceConsole.lireInteger("Quelle carte donner ? ");

			System.out.println("-- CHOIX DU JOUEUR A QUI DONNER LA CARTE --");
			for (int i = 0; i < Jeu.getInstance().getJoueurs().size() - 1; i++) {
				if (!joueurCourant.equals(Jeu.getInstance().getJoueurs().get(i))) {
					System.out.println("(" + i + ")" + Jeu.getInstance().getJoueurs().get(i).getPseudo());
				}
			}

			data[1] = interfaceConsole.lireInteger("A quel joueur ? ");
			try {
				effet.setData(data, joueurCourant);
			}catch (ErreurDonneesEffet e){
				setDataOk = false;
			}

		} while (!setDataOk);
		
		interfaceConsole.getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
	}
	
	

}
