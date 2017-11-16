package model;

public class EffetRejouer extends Effet {

    
	public String action(Joueur joueurCourant) {
		
		Jeu.getInstance().faireRejouer(joueurCourant);
		
		return "";
    }
	
	private String getMessage(Joueur joueurCourant){
		String str = joueurCourant.getPseudo()+ " rejoue!";
		
		return str;
	}


}
