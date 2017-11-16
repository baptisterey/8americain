package model;


public class Agressif implements Strategie {

	@Override
	public Carte choisirCarteStrategie(Joueur joueurCourant) {
		
		return joueurCourant.getMain().get(0); // TODO Faire une vraie strategy
	}
    
  
}
