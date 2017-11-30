package model;

public class EffetRejouer extends Effet {
	
	private static final int score = 20;
    
	
	public EffetRejouer(){
		super(EffetRejouer.score);
	}
	
	
	public String action(Joueur joueurCourant) {
		
		Jeu.getInstance().faireRejouer(joueurCourant);
		
		return getMessage(joueurCourant);
    }
	
	private String getMessage(Joueur joueurCourant){
		String str = joueurCourant.getPseudo()+ " rejoue!";
		
		return str;
	}


}
