package model.effets;

import model.Joueur;

public class EffetClassique extends Effet {
	
	public EffetClassique(int valeur){
		super(valeur);
	}
	
	public String action(Joueur joueurCourant) {
		// Aucune action
		return "";
	}

}
