package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetSauterTour extends Effet {
	
	private static final int score = 20;
	
	public EffetSauterTour(){
		super(EffetSauterTour.score);
	}
    
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().getJoueurSuivant(joueurCourant).setPeutJouer(false);
		
		Message msg = new Message(Message.Types.effetSauterTour);
		msg.setJoueurCourant(joueurCourant);
		msg.setJoueurVictime(Jeu.getInstance().getJoueurSuivant(joueurCourant));
    	return msg;
	}

}
