package model;

public abstract class Effet {

	private boolean isAgressif = false;
	protected int valeurScore;
	public abstract String action(Joueur joueurCourant);
	
	public Effet(int scoreValue){
		this.valeurScore = scoreValue;
	}
	
	public int getScoreValue() {
		return valeurScore;
	}
		
}
