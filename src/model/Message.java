package model;

public class Message {

	private Types type;
	private Joueur joueurCourant;
	private int nbCartesAttaque;
	private Joueur joueurVictime;
	private Carte carteADonner;
	private int nouvelleCouleur;
	private String annonce;
	
	public static enum Types {
		effetAttaque, effetClassique, effetRejouer, effetSauterTour, effetContrerChangerCouleur, effetChangerCouleur, effetDonner, effetModeAttaque, effetChangerSensJeu, nePeutPasJouer, joueurAnnonce, annonceContreCarteReussi, annonceContreCarteEchoue, annonceInconnue, piocherCarte, choixChangerCouleur, choixDonnerCarte, cartePosee, annonceCarteTropTot, joueurAFiniManche, afficherTour, tourJoueurHumain, initJoueurs, finTourJoueurHumain
	}
	
	public Message (Types type) {
		this.type = type;
	}
	
	public Types getType() {
		return type;
	}
	
	public void setType(Types type) {
		this.type = type;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public int getNbCartesAttaque() {
		return nbCartesAttaque;
	}

	public void setNbCartesAttaque(int nbCartesAttaque) {
		this.nbCartesAttaque = nbCartesAttaque;
	}

	public Joueur getJoueurVictime() {
		return joueurVictime;
	}

	public void setJoueurVictime(Joueur joueurVictime) {
		this.joueurVictime = joueurVictime;
	}

	public Carte getCarteADonner() {
		return carteADonner;
	}

	public void setCarteADonner(Carte carteADonner) {
		this.carteADonner = carteADonner;
	}

	public int getNouvelleCouleur() {
		return nouvelleCouleur;
	}

	public void setNouvelleCouleur(int nouvelleCouleur) {
		this.nouvelleCouleur = nouvelleCouleur;
	}

	public String getAnnonce() {
		return annonce;
	}

	public void setAnnonce(String annonce) {
		this.annonce = annonce;
	};
	
	
}
