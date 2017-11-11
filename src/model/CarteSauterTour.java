package model;

public class CarteSauterTour extends Carte {

    public CarteSauterTour(int valeur, int couleur) {
		super(valeur, couleur);
	}
    
    public CarteSauterTour (String valeur, String couleur) {
    	super(valeur,couleur);
    }

	public void action(Joueur joueurCourant) {
		
		Jeu.getInstance().getJoueurSuivant().setPeutJoueur(false);
    
	}

}
