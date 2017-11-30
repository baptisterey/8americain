package model;

public class EffetRejouer extends Effet {
	private int scoreValue = 20;
    
	public String action(Joueur joueurCourant) {
		
		Jeu.getInstance().faireRejouer(joueurCourant);
		
		return getMessage(joueurCourant);
    }
	
	private String getMessage(Joueur joueurCourant){
		String str = joueurCourant.getPseudo()+ " rejoue!";
		
		return str;
	}


}
