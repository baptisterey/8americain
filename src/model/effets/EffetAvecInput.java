package model.effets;

import model.Joueur;

public abstract class EffetAvecInput extends Effet {

	public EffetAvecInput(int scoreValue) {
		super(scoreValue);

	}

	public abstract void setData(int[] data, Joueur joueurCourant) throws ErreurDonneesEffet;

	public abstract void resetData();

}
