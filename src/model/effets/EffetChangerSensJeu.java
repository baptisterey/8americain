package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Effet représentant un changement dans le sens du tour de jeu.
 */
public class EffetChangerSensJeu extends Effet {
	/**
	 * Constante privée pour le score de l'Effet.
	 */
	private static final int score = 20;

	public EffetChangerSensJeu() {
		super(EffetChangerSensJeu.score);
	}

	@Override
	/**
	 * Change le sens du jeu grâce un appel de changerSensJeu() de la classe Jeu.
	 * Renvoie un message de type effetChangerSensJeu.
	 */
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().changerSensJeu();

		Message msg = new Message(Message.Types.effetChangerSensJeu);
		msg.setJoueurCourant(joueurCourant);
		return msg;
	}

}
