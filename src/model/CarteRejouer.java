package model;

public class CarteRejouer extends Carte {

    public CarteRejouer(int valeur, int couleur) {
		super(valeur, couleur);
	}
    
    public CarteRejouer (String valeur, String couleur) {
    	super(valeur,couleur);
    }

	public void action(Joueur joueurCourant) {
    }

}
