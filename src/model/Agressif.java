package model;


public class Agressif implements Strategie {

	@Override
	public Carte choisirCarteStrategie(Joueur joueurCourant) {
		for(Carte carte : joueurCourant.getMain()) {
			if (Jeu.getInstance().isCartePosable(carte)) {
				return carte;
			}
		}
		return null; // TODO Faire une vraie strategy
	}

	
	
	@Override
	public int[] choisirDataDonner(Joueur joueurCourant) {
		int [] data = new int [2];
		data[0] = 0;
		data[1] = 0;
		return data;
	}
 
}
