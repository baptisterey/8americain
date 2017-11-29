package model;

public class EffetContrerChangerCouleur extends EffetChangerCouleur {
	private int scoreValue = 50;
   
	public String action(Joueur joueurCourant) {
		//On arrête les attaques
		if(Jeu.getInstance().isModeAttaque()){
			Jeu.getInstance().setModeAttaque(false);
			Jeu.getInstance().setNbCarteModeAttaque(0);
		}
		//On réalise l'action ChangerCouleur
		super.action(joueurCourant);
		return "";	
    	}
	

}
