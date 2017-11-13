package model;

import java.util.ArrayList;
import java.util.List;

public class EffetChangerSensJeu implements Effet {

	@Override
	public void action(Joueur joueurCourant) {
		Jeu.getInstance().changerSensJeu();
	}

	@Override
	public String getMessage(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo()+" inverse le sens de jeu!";
		return str;
	}

}
