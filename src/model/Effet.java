package model;

public abstract class Effet {

	private boolean isAgressif = false;
	private int scoreValue;
	public abstract String action(Joueur joueurCourant);
	
	public int getScoreValue() {
		return scoreValue;
	}
}
