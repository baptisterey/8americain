package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetContrerChangerCouleur extends EffetChangerCouleur {

	/**
	 * Constante privée indiquant le score de cet Effet.
	 */
	private static final int score = 50;

	public EffetContrerChangerCouleur() {
		this.valeurScore = EffetContrerChangerCouleur.score;
	}

	/**
	 * Si possible, Contre un message puis change la couleur de la carte et renvoie
	 * un msg de type effetContrerChangerCouleur. Sinon change seulement la couleur
	 * et renvoie un message de type effetChangerCouleur.
	 */
	public Message action(Joueur joueurCourant) {
		Message msg;

		// On arrête les attaques
		if (Jeu.getInstance().isModeAttaque()) {
			Jeu.getInstance().setModeAttaque(false);
			Jeu.getInstance().setNbCarteModeAttaque(0);

			msg = new Message(Message.Types.effetContrerChangerCouleur);
			msg.setJoueurCourant(joueurCourant);
			msg.setNouvelleCouleur(nouvelleCouleur);

			// ET On réalise l'action ChangerCouleur
			super.action(joueurCourant);
		} else {
			// On réalise l'action ChangerCouleur
			msg = super.action(joueurCourant);
		}

		return msg;
	}

}
