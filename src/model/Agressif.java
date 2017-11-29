package model;


public class Agressif implements Strategie {

	@Override
	public Carte choisirCarteStrategie(Joueur joueurCourant) {
		Carte carteChoisie = null;
		for(Carte carte : joueurCourant.getMain()) {
			if (Jeu.getInstance().isCartePosable(carte)) {
				if ( !(carteChoisie.getEffet() instanceof EffetAttaque) || carteChoisie == null ) {
					carteChoisie = carte;
				}
			}
		}
		return carteChoisie; // TODO Faire une vraie strategy
	}

	
	
	@Override
	public int[] choisirDataDonner(Joueur joueurCourant) {
		
		int [] data = new int [2];
		data[0] = 0;
		data[1] = 0;
		return data;
	}
 
}
