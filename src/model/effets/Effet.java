package model.effets;

import model.Joueur;
import model.Message;

/**
 * Classe squelette representant un Effet lie e une Carte. Il possede une
 * methode action().
 *
 */
public abstract class Effet {
	/**
	 * La valeur de la carte, elle peut etre fixe ou dynamique.
	 */
	protected int valeurScore;

	/**
	 * Determine si la Carte est toujours posable ou bien suit les regles de posage
	 * classiques.
	 */
	private boolean alwaysPosable = false;

	/**
	 * Realise l'effet adequat puis retourne un Message indiquant l'action
	 * effectuee.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui a joue la Carte (et donc l'Effet).
	 * @return Le Message associe e l'Effet, indique ce qu'il sait passe grece e son
	 *         Type.
	 */
	public abstract Message action(Joueur joueurCourant);

	public Effet(int scoreValue) {
		this.valeurScore = scoreValue;
	}

	public int getScoreValue() {
		return valeurScore;
	}

	public boolean isAlwaysPosable() {
		return alwaysPosable;
	}

	public void setAlwaysPosable(boolean bool) {
		alwaysPosable = bool;
	}

}
