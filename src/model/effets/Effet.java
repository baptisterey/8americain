package model.effets;

import model.Joueur;

public abstract class Effet {

	protected int valeurScore;
	public abstract String action(Joueur joueurCourant);
	
	private boolean alwaysPosable = false;
	
	public Effet(int scoreValue){
		this.valeurScore = scoreValue;
	}
	
	public int getScoreValue() {
		return valeurScore;
	}
	
	public boolean isAlwaysPosable(){
		return alwaysPosable;
	}
	
	public void setAlwaysPosable(boolean bool){
		alwaysPosable = bool;
	}
		
}
