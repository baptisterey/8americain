package model;

import model.effets.EffetAvecInput;

/**
 * Classe utilisée dans le pattern Observer / Observable afin de faire passer
 * les données dans la méthode notifyObservers();
 * On identifie un message par son type.
 */
public class Message {

	private Types type;
	private Joueur joueurCourant;
	private int nbCartesAttaque;
	private Joueur joueurVictime;
	private Carte carteADonner;
	private int nouvelleCouleur;
	private String annonce;
	private int numeroManche;
	private EffetAvecInput effetAvecInputEnCours;
	
	/**
	 * Liste de tous les types de message possibles.
	 */
	public static enum Types {
		effetAttaque, effetClassique, effetRejouer, effetSauterTour, effetContrerChangerCouleur, effetChangerCouleur, effetDonner, effetModeAttaque, effetChangerSensJeu, nePeutPasJouer, joueurAnnonce, annonceContreCarteReussi, annonceContreCarteEchoue, annonceInconnue, piocherCarte, choixChangerCouleur, choixDonnerCarte, cartePosee, annonceCarteTropTot, joueurAFiniManche, afficherTour, tourJoueurHumain, initJoueurs, finTourJoueurHumain, annonceCarte, nouvelleManche, finPartie, initPartie
	}

	public Message(Types type) {
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
	}

	public int getNumeroManche() {
		return numeroManche;
	}

	public void setNumeroManche(int numeroManche) {
		this.numeroManche = numeroManche;
	}

	public EffetAvecInput getEffetAvecInputEnCours() {
		return effetAvecInputEnCours;
	}

	public void setEffetAvecInputEnCours(EffetAvecInput effetAvecInputEnCours) {
		this.effetAvecInputEnCours = effetAvecInputEnCours;
	};

}
