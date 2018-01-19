package model;

/**
 * JoueurArtificiel represente un Joueur BOT qui possede une surcharge de la
 * methode choisirCarte() qui utilise une strategie propre au Joueur.
 */

public class JoueurArtificiel extends Joueur {
	/**
	 * La strategie du joueur artificiel.
	 */
	private Strategie strategie;

	/**
	 * Constante pour la strategie Passive.
	 */
	public static final int PASSIF = 0;

	/**
	 * Constante pour la strategie Agressive.
	 */
	public static final int AGRESSIF = 1;
	
	public JoueurArtificiel(String pseudo, int strategie) {
		super(pseudo);

		switch (strategie) {
		case PASSIF:
			this.strategie = new Passif();
			break;
		case AGRESSIF:
			this.strategie = new Agressif();
			break;
		default: // Par defaut on choisi une strategie Passive.
			this.strategie = new Passif();
		}

	}

	public Carte choisirCarte() {
		return this.strategie.choisirCarteStrategie(this);
	}

	public Integer[] choisirDataDonner() {
		return this.strategie.choisirDataDonner(this);
	}

	public Integer[] choisirDataChangerCouleur() {
		return this.strategie.choisirDataChangerCouleur(this);
	}

	public String choisirAnnonce() {
		return this.strategie.choisirAnnonce(this);
	}

}
