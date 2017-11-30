package model.effets;

import model.Jeu;
import model.Joueur;

public class EffetSauterTour extends Effet {
	
	private static final int score = 20;
	
	public EffetSauterTour(){
		super(EffetSauterTour.score);
	}
    
	
	
	public String action(Joueur joueurCourant) {
		
		Jeu.getInstance().getJoueurSuivant(joueurCourant).setPeutJouer(false);
		
    	return getMessage(joueurCourant);
	}

	
	private String getMessage(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo()+" empêche "+Jeu.getInstance().getJoueurSuivant(joueurCourant).getPseudo()+" de jouer!";
		return str;
	}

}
