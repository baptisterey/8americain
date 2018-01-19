package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Effet representant un joueur qui rejoue.
 */
public class EffetRejouer extends Effet {

	/**
	 * Constante privee indiquant le score de cet Effet.
	 */
	private static final int score = 20;

	public EffetRejouer() {
		super(EffetRejouer.score);
	}

	/**
	 * Fait rejouer le Joueur grece e un appel de faireRejouer() de Jeu. Renvoie
	 * ensuite un message de type effetRejouer
	 */
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().faireRejouer(joueurCourant);

		Message msg = new Message(Message.Types.effetRejouer);
		msg.setJoueurCourant(joueurCourant);
		return msg;
	}

}
