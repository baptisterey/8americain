package model;

public class Passif implements Strategie {
	
	public Carte choisirCarteStrategie(Joueur joueurCourant) {
		Carte carteChoisie = null;
		for (int i = 0 ; i <= joueurCourant.getMain().size()-1 ; i++) {
			if (Jeu.getInstance().isCartePosable(joueurCourant.getMain().get(i))) {
				if (carteChoisie == null || carteChoisie.getEffet() instanceof EffetAttaque) {
					carteChoisie = joueurCourant.getMain().get(i);
				}
			} 
		}
		return carteChoisie;
		}
	
		
	@Override
	public int[] choisirDataDonner(Joueur joueurCourant) {
		int [] data = new int [2];
		data[0] = 0;
		data[1] = 0;
		return data;
	}


	@Override
	public int[] choisirDataChangerCouleur(Joueur joueurCourant) {
		int [] data = new int [1];
		data[0] = Carte.COEUR;
		return data;
	}
}
