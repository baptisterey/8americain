package model.effets;

import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Effet représentant une Attaque d'un Joueur qui peut être contrable ou non.
 */
public class EffetAttaque extends Effet {

	/**
	 * Indique si l'attaque est contrable ou non.
	 */
	private boolean isContrable = true;

	/**
	 * Indique la valeur d'attaque de l'Effet (le nombre de cartes à piocher).
	 */
	private int valeurAttaque;

	/**
	 * Constante privée indiquant le score de cet Effet.
	 */
	private static final int score = 50;

	public EffetAttaque(int valeurAttaque, boolean isContrable) {
		super(EffetAttaque.score);
		this.isContrable = isContrable;
		this.valeurAttaque = valeurAttaque;
	}

	public boolean isContrable() {
		return this.isContrable;
	}

	/**
	 * Si la carte est contrable, fait basculer le jeu en mode Attaque (on ne peut
	 * poser que des cartes Attaque sous peine de piocher la valeur de toutes les
	 * cartes déja posées). Sinon fait piocher directement le nombre de cartes
	 * (valeurAttaque) au joueur suivant. Renvoie un message de type
	 * effetModeAttaque pour le premier cas ou effetAttaque pour le second cas.
	 */
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
