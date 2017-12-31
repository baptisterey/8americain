package model.effets;

import model.Joueur;
import model.Message;

/**
 * Effet vide servant pour les cartes qui ne font rien.
 */
public class EffetClassique extends Effet {
	
	public EffetClassique(int valeur){
		super(valeur);
	}
	
	/**
	 * Ne fait rien et envoie un message de type effetClassique.
	 */
	public Message action(Joueur joueurCourant) {
		
		return new Message(Message.Types.effetClassique);
	}

}
