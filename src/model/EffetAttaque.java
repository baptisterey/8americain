package model;


public class EffetAttaque extends Effet {

	private boolean isContrable = true;
    private int valeurAttaque;
    
    private static final int score = 50;
    
    
    
    public EffetAttaque(int valeurAttaque,boolean isContrable) {
    	super(EffetAttaque.score);
    	this.isContrable = isContrable;
    	this.valeurAttaque = valeurAttaque;
    }

    public String action(Joueur joueurCourant) {
    	if(isContrable) {
    		Jeu.getInstance().setModeAttaque(true);
        	Jeu.getInstance().addCarteAttaque(valeurAttaque);
    	}else {
    		Jeu.getInstance().piocherCarte(Jeu.getInstance().getJoueurSuivant(joueurCourant), valeurAttaque);
    		
    	}
	    
	return getMessage(joueurCourant);
    }

	
	private String getMessage(Joueur joueurCourant) {
		String str = "";
		if(isContrable) {
			str+= joueurCourant.getPseudo()+ " ajoute "+this.valeurAttaque+" carte(s) au tas attaque!";
		}else {
			str+= joueurCourant.getPseudo()+" oblige "+Jeu.getInstance().getJoueurSuivant(joueurCourant).getPseudo()+" à piocher "+this.valeurAttaque+" carte(s)!";	
		}
		return str;
	}

}
