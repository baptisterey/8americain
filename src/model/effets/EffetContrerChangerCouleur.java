package model.effets;

import model.Carte;
import model.Jeu;
import model.Joueur;

public class EffetContrerChangerCouleur extends EffetChangerCouleur {
	private static final int score = 50;
	
	public EffetContrerChangerCouleur() {
		this.valeurScore = EffetContrerChangerCouleur.score;
	}
	
	public String action(Joueur joueurCourant) {
		String message;
		
		//On arrête les attaques
		if(Jeu.getInstance().isModeAttaque()){
			Jeu.getInstance().setModeAttaque(false);
			Jeu.getInstance().setNbCarteModeAttaque(0);
			message = getMessageStopAttaque(joueurCourant);
			
			//On réalise l'action ChangerCouleur
			super.action(joueurCourant);
		}else {
			//On réalise l'action ChangerCouleur
			message = super.action(joueurCourant);
		}
		

		return message;	
    }
	
	private String getMessageStopAttaque(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo()+" a arreter une attaque et a choisi la couleur "+ Carte.COULEURS[nouvelleCouleur]+"!";
		
		return str;
	}
	
}
