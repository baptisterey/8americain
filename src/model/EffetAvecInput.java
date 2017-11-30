package model;

public abstract class EffetAvecInput extends Effet {
	
	public EffetAvecInput(int scoreValue) {
		super(scoreValue);
		
	}

	public abstract void setData(int [] data);
	
	public abstract void resetData();
	
	
}
