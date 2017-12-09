package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetAttaque extends Effet {

	private boolean isContrable = true;
	private int valeurAttaque;

	private static final int score = 50;

	public EffetAttaque(int valeurAttaque, boolean isContrable) {
		super(EffetAttaque.score);
		this.isContrable = isContrable;
		this.valeurAttaque = valeurAttaque;
	}

	public Message action(Joueur joueurCourant) {
		Message msg;
		if (isContrable) {
			Jeu.getInstance().setModeAttaque(true);
			Jeu.getInstance().addCarteAttaque(valeurAttaque);
			
			msg = new Message(Message.Types.effetModeAttaque);
		} else {
			Jeu.getInstance().piocherCarte(Jeu.getInstance().getJoueurSuivant(joueurCourant), valeurAttaque);
			
			msg = new Message(Message.Types.effetAttaque);
			msg.setJoueurVictime(Jeu.getInstance().getJoueurSuivant(joueurCourant));
		}
		
		msg.setJoueurCourant(joueurCourant);
		msg.setNbCartesAttaque(valeurAttaque);
		
		return msg;
	}

}
