package model;

public class EffetSauterTour extends Effet {
	private int scoreValue = 20;
    
	public String action(Joueur joueurCourant) {
		
		Jeu.getInstance().getJoueurSuivant(joueurCourant).setPeutJouer(false);
		
    	return getMessage(joueurCourant);
	}

	
	private String getMessage(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo()+" empêche "+Jeu.getInstance().getJoueurSuivant(joueurCourant).getPseudo()+" de jouer!";
		return str;
	}

}
