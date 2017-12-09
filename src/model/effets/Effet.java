package model.effets;

import model.Joueur;
import model.Message;

public abstract class Effet {

	protected int valeurScore;
	public abstract Message action(Joueur joueurCourant);
	
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
