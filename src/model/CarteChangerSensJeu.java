package model;

import java.util.ArrayList;
import java.util.List;

public class CarteChangerSensJeu extends Carte {

    public CarteChangerSensJeu(int valeur, int couleur) {
		super(valeur, couleur);
	}
    
    public CarteChangerSensJeu (String valeur, String couleur) {
    	super(valeur,couleur);
    }

	public void action(String joueurCourant) {
		Jeu.getInstance().changerSensJeu();
    }

}
