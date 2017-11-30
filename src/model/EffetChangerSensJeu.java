package model;

import java.util.ArrayList;
import java.util.List;

public class EffetChangerSensJeu extends Effet {
	private static final int score = 20;
	
	public EffetChangerSensJeu(){
		super(EffetChangerSensJeu.score);
	}
	
	@Override
	public String action(Joueur joueurCourant) {
		Jeu.getInstance().changerSensJeu();
		return getMessage(joueurCourant);
	}

	private String getMessage(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo()+" inverse le sens de jeu!";
		return str;
	}

}
