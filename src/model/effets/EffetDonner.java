package model.effets;

import model.Carte;
import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetDonner extends EffetAvecInput {
	
	int indexJoueurADonner;
	int indexCarteADonner;
	
	private static final int score = 20;
	
	public EffetDonner(){
		super(EffetDonner.score);
	}
	
	public Message action(Joueur joueurCourant) {
		Joueur joueurADonner = Jeu.getInstance().getJoueurs().get(indexJoueurADonner);
		Carte carteADonner = joueurCourant.getMain().get(indexCarteADonner);
		
		joueurADonner.getMain().add(carteADonner);
		joueurCourant.getMain().remove(carteADonner);
		
		Message msg = new Message(Message.Types.effetDonner);
		msg.setJoueurCourant(joueurCourant);
		msg.setJoueurVictime(joueurADonner);
		msg.setCarteADonner(carteADonner);
		
		resetData();
		return msg;
    }

	@Override
	public void setData(int [] data) {
		indexCarteADonner = data[0];
		indexJoueurADonner = data[1];
	}

	@Override
	public void resetData() {
		indexJoueurADonner = -1;
		indexCarteADonner = -1;
	}
	
}
