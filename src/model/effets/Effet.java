package model.effets;

import model.Joueur;
import model.Message;

/**
 * Classe squelette représentant un Effet lié à une Carte. Il possède une
 * méthode action().
 *
 */
public abstract class Effet {
	/**
	 * La valeur de la carte, elle peut être fixe ou dynamique.
	 */
	protected int valeurScore;

	/**
	 * Détermine si la Carte est toujours posable ou bien suit les régles de posage
	 * classiques.
	 */
	private boolean alwaysPosable = false;

	/**
	 * Réalise l'effet adéquat puis retourne un Message indiquant l'action
	 * effectuée.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui a joué la Carte (et donc l'Effet).
	 * @return Le Message associé à l'Effet, indique ce qu'il sait passé grâce à son
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
