package model.effets;

import model.Carte;
import model.Jeu;
import model.Joueur;
import model.Message;
/**
 * 
 */
public class EffetDonner extends EffetAvecInput {
	Joueur joueurADonner;
	Carte carteADonner;

	
	private static final int score = 20;
	
	public EffetDonner(){
		super(EffetDonner.score);
	}
	
	public Message action(Joueur joueurCourant) {
		
		
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
	public void setData(Integer [] data, Joueur joueurCourant) throws ErreurDonneesEffet {
		try {
			joueurADonner = Jeu.getInstance().getJoueurs().get(data[1]);
			carteADonner = joueurCourant.getMain().get(data[0]);
		}catch(IndexOutOfBoundsException e) {
			throw new ErreurDonneesEffet();
		}
	}

	@Override
	public void resetData() {
		joueurADonner = null;
		carteADonner = null;
	}
	
}
