package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetChangerSensJeu extends Effet {
	private static final int score = 20;

	public EffetChangerSensJeu() {
		super(EffetChangerSensJeu.score);
	}

	@Override
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().changerSensJeu();

		Message msg = new Message(Message.Types.effetChangerSensJeu);
		msg.setJoueurCourant(joueurCourant);
		return msg;
	}

}
