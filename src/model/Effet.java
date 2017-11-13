package model;

public interface Effet {

	public void action(Joueur joueurCourant);
	
	public String getMessage(Joueur joueurCourant);
	
}
