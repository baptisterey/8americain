package model;

public class CarteContrerChangerValeur extends Carte {

    public CarteContrerChangerValeur(int valeur, int couleur) {
		super(valeur, couleur);
	}
    
    public CarteContrerChangerValeur (String valeur, String couleur) {
    	super(valeur,couleur);
    }

	public void action(Joueur joueurCourant) {
    }

}
