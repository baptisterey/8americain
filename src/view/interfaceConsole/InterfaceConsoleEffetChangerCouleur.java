package view.interfaceConsole;

import controleur.Controleur;
import model.Carte;
import model.Jeu;
import model.Joueur;
import model.effets.EffetAvecInput;
import model.effets.ErreurDonneesEffet;

public class InterfaceConsoleEffetChangerCouleur implements Runnable {
	private Joueur joueurCourant;
	private EffetAvecInput effet;
	InterfaceConsole interfaceConsole;
	
	public InterfaceConsoleEffetChangerCouleur(InterfaceConsole interfaceConsole,Joueur joueurCourant, EffetAvecInput effet) {
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
			
			System.out.println("-- CHOIX CHANGER COULEUR --");
			for (int i = 0; i < Carte.COULEURS.length; i++) {
				System.out.println("(" + i + ")" + Carte.COULEURS[i]);
			}

			data[0] = interfaceConsole.lireInteger("Choisir couleur : ");
			
			try {
				effet.setData(data, joueurCourant);
			}catch (ErreurDonneesEffet e){
				setDataOk = false;
			}

		} while (!setDataOk);
		
		interfaceConsole.getControleur().getJeu().jouerEffetAvecInputEnCours(effet, joueurCourant);
	}
	
}
