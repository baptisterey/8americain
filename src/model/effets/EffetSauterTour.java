package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Effet représentant une action qui va annuler le tour du joueur suivant?
 */
public class EffetSauterTour extends Effet {

	/**
	 * Constante privée indiquant le score de cet Effet.
	 */
	private static final int score = 20;

	public EffetSauterTour() {
		super(EffetSauterTour.score);
	}

	/**
	 * Change le boolean peutJoueur du joueurSuivant à false puis renvoie un msg de
	 * type effetSauterTour.
	 */
	public Message action(Joueur joueurCourant) {
		Jeu.getInstance().getJoueurSuivant(joueurCourant).setPeutJouer(false);

		Message msg = new Message(Message.Types.effetSauterTour);
		msg.setJoueurCourant(joueurCourant);
		msg.setJoueurVictime(Jeu.getInstance().getJoueurSuivant(joueurCourant));
		return msg;
	}

}
