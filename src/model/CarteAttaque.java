package model;


public class CarteAttaque extends Carte {

    public CarteAttaque(int valeur, int couleur) {
		super(valeur, couleur);
	}
    
    public CarteAttaque (String valeur, String couleur) {
    	super(valeur,couleur);
    }

	private boolean isContrable;
    private int valeurAttaque;

    public void action(Joueur joueurCourant) {
    }

}
