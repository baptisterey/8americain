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
		int indexCarteChoisie = 0;
		for (int i = 1 ; i < joueurCourant.getMain().size() ; i++) {
			if (joueurCourant.getMain().get(indexCarteChoisie).getEffet() instanceof EffetAttaque || joueurCourant.getMain().get(indexCarteChoisie).getEffet() instanceof EffetContrerChangerCouleur) {
				indexCarteChoisie = i;
			}
		}
		
		int indexJoueurChoisi = 0;
		for (int j = 1 ; j < Jeu.getInstance().getJoueurs().size() - 1 ; j++) { //le joueur courant est en dernière position de cette linkedList
			if (Jeu.getInstance().getJoueurs().get(indexJoueurChoisi).getMain().size() > Jeu.getInstance().getJoueurs().get(j).getMain().size()) {
				indexJoueurChoisi = j;
			}
		}
		
		int [] data = new int [2];
		data[0] = indexCarteChoisie;
		data[1] = indexJoueurChoisi;
		return data;
	}
 
}
