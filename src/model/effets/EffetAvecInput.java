package model.effets;

import model.Joueur;

/**
 * Squelette pour les Effets ayant besoin d'etre initialiser avec des donnees
 * Utilisateur avant d'etre jouee.
 */
public abstract class EffetAvecInput extends Effet {

	public EffetAvecInput(int scoreValue) {
		super(scoreValue);

	}

	/**
	 * Initialise avec les donnees passees en parametre, afin de permettre la
	 * realisation de la methode action().
	 * 
	 * @param data
	 *            Les donnees sous forme d'un tableau d'Entier, se referer aux
	 *            classes qui heritent EffetAvecInput afin de connaetre ce qu'elles
	 *            representent.
	 * @param joueurCourant
	 *            Le joueur qui joue la Carte.
	 * @throws ErreurDonneesEffet
	 *             Est levee si les donnees passees ne sont pas conformes.
	 */
	public abstract void setData(Integer[] data, Joueur joueurCourant) throws ErreurDonneesEffet;

	/**
	 * Remet e zero les donnees de la classe.
	 */
	public abstract void resetData();

}
