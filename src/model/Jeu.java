package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.effets.EffetAttaque;
import model.effets.EffetChangerCouleur;
import model.effets.EffetChangerSensJeu;
import model.effets.EffetContrerChangerCouleur;
import model.effets.EffetDonner;
import model.effets.EffetRejouer;
import model.effets.EffetSauterTour;

public class Jeu {

	// DEFINITION DES VARIABLES REPRESENTANTS CHAQUE VARIANTE
	public final static int BASIQUE = 0;
	public final static int MINIMALE = 1;
	public final static int MONCLAR = 2;

	// DEFINITION DES VARIABLES REPRESENTANTS CHAQUE METHODE DE COMPTAGE
	public final static int COMPTE_POSITIF = 0;
	public final static int COMPTE_NEGATIF = 1;

	private int variante = BASIQUE;
	private int methodeCompte = COMPTE_POSITIF;
	private int nbCarteModeAttaque = 0;

	public final static String ANNONCE_CARTE = "Carte";
	public final static String ANNONCE_CONTRE_CARTE = "Contre Carte";

	private boolean modeAttaque = false;
	private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	private LinkedList<Joueur> joueursInitiation = new LinkedList<Joueur>();
	private LinkedList<Carte> pioche = new LinkedList<Carte>();
	private LinkedList<Carte> defausse = new LinkedList<Carte>();
	private LinkedList<Joueur> gagnants = new LinkedList<Joueur>();

	public LinkedList<Joueur> getJoueursInitiation() {
		return joueursInitiation;
	}

	public LinkedList<Joueur> getGagnants() {
		return gagnants;
	}

	private static Jeu instance;

	private Jeu() {

	}

	/**
	 * Implementation du design patern SINGLETON
	 */
	public static Jeu getInstance() {
		if (instance != null) {
			return instance;
		} else {
			Jeu.instance = new Jeu();
			return Jeu.instance;
		}
	}

	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}

	public LinkedList<Carte> getDefausse() {
		return defausse;
	}

	public void initCarteManche() {

		joueurs.clear();
		gagnants.clear();
		pioche.clear();
		defausse.clear();

		for (Joueur joueur : joueursInitiation) {
			joueur.getMain().clear();
		}

		for (int i = 0; i < getJoueursInitiation().size(); i++) {
			getJoueurs().add(getJoueursInitiation().get(i));
		}

		// Création des 32 cartes (TODO faire avec 52)
		for (int valeur = 5; valeur < 13; valeur++) {
			for (int couleur = 0; couleur < 4; couleur++) {
				Carte carte = new Carte(valeur, couleur);
				gererVariante(carte); // Application des effets en fonction de la variante

				pioche.add(carte);
			}
		}

		Collections.shuffle(pioche);

		for (int i = 0; i < 1; i++) {
			for (Joueur joueur : getJoueurs()) {
				piocherCarte(joueur, 2);
			}
		}

		defausse.add(pioche.removeLast());
	}

	private void gererVariante(Carte carte) {

		int valeur = carte.getValeur();
		switch (valeur) {
		case Carte.CINQ:
			switch (variante) {
			case BASIQUE:
				carte.setEffet(new EffetDonner());
				break;
			}
			break;

		case Carte.SEPT:
			switch (variante) {
			case BASIQUE:
				carte.setEffet(new EffetSauterTour());
				break;
			case MONCLAR:
				carte.setEffet(new EffetSauterTour());
				break;
			}
			break;

		case Carte.HUIT:
			switch (variante) {
			case BASIQUE:
				carte.setEffet(new EffetContrerChangerCouleur());
				break;
			case MINIMALE:
				carte.setEffet(new EffetChangerCouleur());
				carte.getEffet().setAlwaysPosable(true);
				break;
			case MONCLAR:
				carte.setEffet(new EffetContrerChangerCouleur());
				break;
			}
			break;

		case Carte.NEUF:
			switch (variante) {

			case MONCLAR:
				carte.setEffet(new EffetAttaque(1, false));
				break;
			}
			break;

		case Carte.DIX:
			switch (variante) {
			case BASIQUE:
				carte.setEffet(new EffetRejouer());
				break;
			case MONCLAR:
				carte.setEffet(new EffetRejouer());
				break;
			}
			break;

		case Carte.VALET:
			switch (variante) {
			case BASIQUE:
				carte.setEffet(new EffetChangerSensJeu());
				break;
			case MONCLAR:
				carte.setEffet(new EffetChangerSensJeu());
				break;
			}
			break;

		case Carte.AS:
			switch (variante) {
			case BASIQUE:
				carte.setEffet(new EffetAttaque(2, true));
				break;
			case MONCLAR:
				carte.setEffet(new EffetAttaque(3, true));
				break;
			}
			break;

		}
	}

	public boolean isModeAttaque() {
		return this.modeAttaque;
	}

	public void setModeAttaque(boolean bool) {

		if (!bool) {
			nbCarteModeAttaque = 0;
		}

		this.modeAttaque = bool;
	}

	public int getNbCarteAttaque() {
		return this.nbCarteModeAttaque;
	}

	public void addCarteAttaque(int nbCarteAPiocher) {
		this.nbCarteModeAttaque += nbCarteAPiocher;
	}

	public void setNbCarteModeAttaque(int nbCarte) {
		this.nbCarteModeAttaque = nbCarte;
	}

	public Joueur getJoueurCourant() {
		Joueur joueurCourant = joueurs.get(0);
		joueurs.add(joueurs.removeFirst());
		return joueurCourant;
	}

	public Joueur getJoueurSuivant(Joueur joueurCourant) {
		return joueurs.get((joueurs.indexOf(joueurCourant) + 1) % joueurs.size());
	}

	public void changerSensJeu() {
		Collections.reverse(joueurs);

		// OBLIGATOIRE Sinon le joueur rejoue.
		joueurs.add(joueurs.removeFirst());
	}

	public void faireRejouer(Joueur joueurCourant) {
		while (!joueurCourant.equals(joueurs.get(0))) {
			joueurs.add(joueurs.removeFirst());
		}
	}

	public void defausserCarte(Joueur joueurCourant, Carte carte) {
		defausse.add(carte);
		joueurCourant.getMain().remove(carte);
	}

	public void defausserCarte(Joueur joueurCourant, int indexCarte) {
		defausse.add(joueurCourant.getMain().get(indexCarte));
		joueurCourant.getMain().remove(indexCarte);
	}

	public void piocherCarte(Joueur joueur, int nb) {

		if (pioche.isEmpty()) {
			for (int i = 0; i < defausse.size() - 1; i++) {
				pioche.add(defausse.get(i));
			}
			Carte carteDefausse = defausse.getLast();
			defausse.clear();
			defausse.add(carteDefausse);

			Collections.shuffle(pioche);
		}

		for (int i = 0; i < nb; i++) {
			joueur.getMain().add(pioche.removeLast());
		}
	}

	public boolean isCartePosable(Carte carte) {
		if (defausse.isEmpty() || carte == null) {
			return true;
		}

		if (modeAttaque) {
			if (carte.getEffet() instanceof EffetAttaque || carte.getEffet() instanceof EffetContrerChangerCouleur) {
				return true;
			}
		} else {
			if (carte.getEffet().isAlwaysPosable()) {
				return true;
			} else {
				Carte carteTapis = defausse.getLast();

				if (carteTapis.getCouleur() == carte.getCouleur() || carteTapis.getValeur() == carte.getValeur()) {
					return true;
				}
			}

		}

		return false;
	}

	public int getNombreJoueursActifs() {
		return getJoueurs().size();
	}

	public boolean isMancheOver() {

		if (this.methodeCompte == COMPTE_NEGATIF) {
			for (Joueur joueur : joueurs) {
				if (joueur.getMain().isEmpty()) {
					return true;
				}
			}
		} else if (this.methodeCompte == COMPTE_POSITIF) {
			
			if ((this.gagnants.size() > 2) || (getNombreJoueursActifs() < 2)) {
				return true;
			}
		}
		return false;
	}

	public void compterScore() {
		switch (methodeCompte) {
		case COMPTE_NEGATIF:
			// COMPTENEGATIF

			for (int i = 0; i < joueurs.size(); i++) {
				for (int j = 0; j < joueurs.get(i).getMain().size(); j++) {
					joueurs.get(i).addScore(joueurs.get(i).getMain().get(j).getEffet().getScoreValue());
				}
			}

			break;

		default:
			// COMPTEPOSITIF
			if (gagnants.size() == 2) {

				gagnants.getFirst().addScore(50);
				gagnants.get(1).addScore(20);

			} else {

				gagnants.getFirst().addScore(50);
				gagnants.get(1).addScore(20);
				gagnants.get(2).addScore(10);

			}
			break;

		}

	}

	public boolean isPartieOver() {
		boolean b = false;
		if (methodeCompte == COMPTE_NEGATIF) {
			for (int i = 0; i < joueursInitiation.size(); i++) {
				if (joueursInitiation.get(i).getScore() >= 50) {
					b = true;
				}
			}
		} else if (methodeCompte == COMPTE_POSITIF) {
			for (int i = 0; i < joueursInitiation.size(); i++) {
				if (joueursInitiation.get(i).getScore() >= 50) {
					b = true;
				}
			}
		}
		return b;
	}
}
