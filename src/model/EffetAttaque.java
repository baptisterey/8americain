package model;


public class EffetAttaque implements Effet {

	private boolean isContrable = true;
    private int valeurAttaque;
    
    public EffetAttaque(int valeurAttaque,boolean isContrable) {
    	this.isContrable = isContrable;
    	this.valeurAttaque = valeurAttaque;
    }

    public void action(Joueur joueurCourant) {
    	if(isContrable) {
    		Jeu.getInstance().setModeAttaque(true);
        	Jeu.getInstance().addCarteAttaque(valeurAttaque);
    	}else {
    		Jeu.getInstance().piocherCarte(Jeu.getInstance().getJoueurSuivant(joueurCourant), valeurAttaque);
    	}
    }

	@Override
	public String getMessage(Joueur joueurCourant) {
		String str = "";
		if(isContrable==false) {
			str+= joueurCourant.getPseudo()+ " ajoute "+this.valeurAttaque+" carte(s) au tas attaque.";
		}else {
			str+= joueurCourant.getPseudo()+" oblige "+Jeu.getInstance().getJoueurSuivant(joueurCourant).getPseudo()+" à piocher "+this.valeurAttaque+" carte(s).";	
		}
		return str;
	}

}
