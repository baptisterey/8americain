package model;

public class EffetClassique extends Effet {
	
	private static final int score = 0;
	
	public EffetClassique(){
		super(EffetClassique.score);
	}
	
	public String action(Joueur joueurCourant) {
		// Aucune action
		return "";
	}

}
