package model.effets;

import model.Joueur;

/**
 * Squelette pour les Effets ayant besoin d'être initialiser avec des données
 * Utilisateur avant d'être jouée.
 */
public abstract class EffetAvecInput extends Effet {

	public EffetAvecInput(int scoreValue) {
		super(scoreValue);

	}

	/**
	 * @param data
	 *            Les données sous forme d'un tableau d'Entier, se référer aux
	 *            classes qui héritent EffetAvecInput afin de connaître ce qu'elles
	 *            représentent.
	 * @param joueurCourant
	 *            Le joueur qui joue la Carte.
	 * @throws ErreurDonneesEffet
	 *             Est levée si les données passées ne sont pas conformes.
	 */
	public abstract void setData(Integer[] data, Joueur joueurCourant) throws ErreurDonneesEffet;

	/**
	 * Remet à zéro les données de la classe.
	 */
	public abstract void resetData();

}
