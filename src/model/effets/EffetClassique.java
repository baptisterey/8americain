package model.effets;

import model.Joueur;
import model.Message;

public class EffetClassique extends Effet {
	
	public EffetClassique(int valeur){
		super(valeur);
	}
	
	public Message action(Joueur joueurCourant) {
		
		return new Message(Message.Types.effetClassique);
	}

}
