package model;


public class CarteDonner extends Carte {

    public CarteDonner(int valeur, int couleur) {
		super(valeur, couleur);
	}
    
    public CarteDonner (String valeur, String couleur) {
    	super(valeur,couleur);
    }

	public void action(Joueur JoueurCourant) {
    }

}
