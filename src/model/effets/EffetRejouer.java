package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Effet représentant un joueur qui rejoue.
 */
public class EffetRejouer extends Effet {

	/**
	 * Constante privée indiquant le score de cet Effet.
	 */
	private static final int score = 20;

	public EffetRejouer() {
		super(EffetRejouer.score);
	}

	/**
	 * Fait rejouer le Joueur grâce à un appel de faireRejouer() de Jeu. Renvoie
	 * ensuite un message de type effetRejouer
	 */
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().faireRejouer(joueurCourant);

		Message msg = new Message(Message.Types.effetRejouer);
		msg.setJoueurCourant(joueurCourant);
		return msg;
	}

}
