package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetRejouer extends Effet {
	
	private static final int score = 20;
    
	
	public EffetRejouer(){
		super(EffetRejouer.score);
	}
	
	
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().faireRejouer(joueurCourant);
		
		Message msg = new Message(Message.Types.effetRejouer);
		msg.setJoueurCourant(joueurCourant);
		return msg;
    }
	
}
