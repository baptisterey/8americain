package view;

import java.util.Observable;

import controleur.Controleur;
import model.Joueur;

public class InterfaceGraphique extends IHM {

	public InterfaceGraphique(Controleur ctrl) {
		super(ctrl);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notifier(String msg) {
		
		
		
	}

	@Override
	public int choixIndexCarte(Joueur joueurCourant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initJoueurs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int [] choixIndexDonner(Joueur joueurCourant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] choixChangerCouleur(Joueur joueurCourant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChoixAction(Joueur joueurCourant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAnnonce(Joueur joueurCourant) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
