package model;

/**
 * JoueurArtificiel représente un Joueur BOT qui posséde une surcharge de la méthode choisirCarte() qui utilise une strategie propre au Joueur.
 * 
 *
 */

public class JoueurArtificiel extends Joueur {
	
	private Strategie strategie;
	
	private static final int PASSIF = 0;
	private static final int AGRESSIF = 1;
	
	public JoueurArtificiel (String pseudo, int strategie) {
		super(pseudo);
		
		switch(strategie) {
	    case PASSIF:
	        this.strategie = new Passif();
	        break;
	    case AGRESSIF:
	    	this.strategie = new Agressif();
	        break;
	    default:
	    	this.strategie = new Passif();
		}
	
	}
	
	public Carte choisirCarte() {
		return this.strategie.choisirCarteStrategie(this);
    }
	
	public int[] choisirDataDonner() {
		return this.strategie.choisirDataDonner(this);
	}
	
}
