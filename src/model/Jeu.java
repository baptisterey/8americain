package model;

import java.util.Collections;
import java.util.LinkedList;

import model.effets.EffetAttaque;
import model.effets.EffetAvecInput;
import model.effets.EffetContrerChangerCouleur;
import model.effets.EffetDonner;
import model.effets.ErreurDonneesEffet;
import model.variantes.Basique;
import model.variantes.Variante;

public class Jeu extends java.util.Observable {

	// DEFINITION DES VARIABLES REPRESENTANTS CHAQUE METHODE DE COMPTAGE
	public final static int COMPTE_POSITIF = 0;
	public final static int COMPTE_NEGATIF = 1;
	
	public final static int DECK_52_CARTES = 5;
	public final static int DECK_32_CARTES = 0;
	
	private Variante variante = new Basique();
	private int methodeCompte = COMPTE_POSITIF;
	private int nbCarteDeck = DECK_52_CARTES;
	
	private int nbCarteModeAttaque = 0;
	private int numManche = 1;
	
	private static Jeu instance;
	
	public final static String ANNONCE_CARTE = "Carte";
	public final static String ANNONCE_CONTRE_CARTE = "Contre Carte";

	private boolean modeAttaque = false;
	private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	private LinkedList<Joueur> joueursInitiation = new LinkedList<Joueur>();
	private LinkedList<Carte> pioche = new LinkedList<Carte>();
	private LinkedList<Carte> defausse = new LinkedList<Carte>();
	private LinkedList<Joueur> gagnants = new LinkedList<Joueur>();

	public int getMethodeCompte() {
		return methodeCompte;
	}

	public void setMethodeCompte(int methodeCompte) {
		this.methodeCompte = methodeCompte;
	}
	
	public void setVariante(Variante variante) {
		this.variante = variante;
	}
	
	public void setNbCarteDeck(int nbCarteDeck) {
		this.nbCarteDeck = nbCarteDeck;
	}

	public LinkedList<Joueur> getJoueursInitiation() {
		return joueursInitiation;
	}
	
	public void setJoueursInitiation(LinkedList<Joueur> joueursInitiation) {
		this.joueursInitiation = joueursInitiation;
	}

	public LinkedList<Joueur> getGagnants() {
		return gagnants;
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
	
	/**
	 * Notifie tous les Observateurs, indiquant qu'une partie est prête à être créer.
	 */
	public void initPartie() {
		setChanged();
		notifyObservers(new Message(Message.Types.initPartie));
	}
	
	/**
	 * Initialise le compteur de manche à 1 et appelle la méthode commencerNouvelleManche();
	 */
	public void commencerPartie() {
		numManche = 1;
		commencerNouvelleManche();
	}

	public synchronized void jouerManche() {
		if (!isMancheOver()) {
			Joueur joueurCourant = jouerTourJoueursArtificiels(); // On fait
																	// jouer
																	// tous les
																	// joueurs
																	// Artificiels

			if (joueurCourant.isPeutJouer() && !isMancheOver()) {
				joueurCourant.setPeutFinir(false);
				Message msg = new Message(Message.Types.tourJoueurHumain);
				msg.setJoueurCourant(joueurCourant);

				setChanged();
				notifyObservers(msg);

			} else if (!isMancheOver()) {
				joueurCourant.setPeutJouer(true);

				Message msg = new Message(Message.Types.nePeutPasJouer);
				msg.setJoueurCourant(joueurCourant);
				setChanged();
				notifyObservers(msg);
				finirTour(joueurCourant);
			} else {
				finirManche();
			}

		} else {
			finirManche();
		}
	}

	private synchronized Joueur jouerTourJoueursArtificiels() {
		Joueur joueurCourant = getJoueurCourant();
		while (joueurCourant instanceof JoueurArtificiel && !isMancheOver()) {
			Message msg = new Message(Message.Types.afficherTour);
			msg.setJoueurCourant(joueurCourant);

			setChanged();
			notifyObservers(msg);

			if (joueurCourant.isPeutJouer()) {
				joueurCourant.setPeutFinir(false);

				boolean actionrequise = true;
				while (actionrequise) {
					String annonce = ((JoueurArtificiel) joueurCourant).choisirAnnonce();
					if (annonce != null) {
						annoncer(joueurCourant, annonce);
					} else {
						Carte carte = ((JoueurArtificiel) joueurCourant).choisirCarte();
						jouerCarte(joueurCourant, carte);
						actionrequise = false;
					}
				}

			} else {
				joueurCourant.setPeutJouer(true);

				msg = new Message(Message.Types.nePeutPasJouer);
				msg.setJoueurCourant(joueurCourant);
				setChanged();
				notifyObservers(msg);

			}

			finirTour(joueurCourant);

			joueurCourant = getJoueurCourant();
		}

		return joueurCourant;
	}

	public synchronized void commencerNouvelleManche() {
		initCarteManche();

		Message msg = new Message(Message.Types.nouvelleManche);
		msg.setNumeroManche(numManche);
		setChanged();
		notifyObservers(msg);
		jouerManche();
	}

	public synchronized void finirTour(Joueur joueurCourant) {

		if (joueurCourant.isPeutFinir() && joueurCourant.getMain().size() != 1) {
			joueurCourant.setPeutFinir(false);
			piocherCarte(joueurCourant, 2);

			Message msg = new Message(Message.Types.annonceCarteTropTot);
			msg.setJoueurCourant(joueurCourant);

			setChanged();
			notifyObservers(msg);
		}

		if (joueurCourant.getMain().isEmpty()) {

			Jeu.getInstance().getGagnants().add(joueurCourant);
			Jeu.getInstance().getJoueurs().remove(joueurCourant);

			Message msg = new Message(Message.Types.joueurAFiniManche);
			msg.setJoueurCourant(joueurCourant);

			setChanged();
			notifyObservers(msg);
		}

		if (!(joueurCourant instanceof JoueurArtificiel)) {
			setChanged();
			notifyObservers(new Message(Message.Types.finTourJoueurHumain));
			jouerManche();
		}

	}
	
	/**
	 * Si la partie est finie, appelle la méthode finirPartie(), sinon incrémente le numéro de manche et appelle la méthode commencerNouvelleManche();
	 */
	public synchronized void finirManche() {
		if (!isPartieOver()) {
			numManche++;
			commencerNouvelleManche();
		} else {
			finirPartie();
		}
	}

	public synchronized void finirPartie() {
		Joueur gagnant = getJoueursInitiation().get(0);
		if (getMethodeCompte() == Jeu.COMPTE_POSITIF) {
			for (int i = 1; i < getJoueursInitiation().size(); i++) {
				if (getJoueursInitiation().get(i).getScore() > gagnant.getScore()) {
					gagnant = getJoueursInitiation().get(i);
				}
			}
		} else if (getMethodeCompte() == Jeu.COMPTE_NEGATIF) {
			for (int i = 1; i < getJoueursInitiation().size(); i++) {
				if (getJoueursInitiation().get(i).getScore() < gagnant.getScore()) {
					gagnant = getJoueursInitiation().get(i);
				}
			}
		}

		Message msg = new Message(Message.Types.finPartie);
		msg.setJoueurCourant(gagnant);
		notifyObservers(msg);
		
		initPartie(); // On recommence une partie
	}

	public synchronized void jouerCarte(Joueur joueurCourant, Carte carte) throws ErreurCarteInposable {
		boolean effetJouableDirectement = true;

		if (isCartePosable(carte)) {
			if (carte == null) {
				if (isModeAttaque()) {
					int nbCarteAttaque = getNbCarteAttaque();
					piocherCarte(joueurCourant, nbCarteAttaque);
					setModeAttaque(false);

					Message msg = new Message(Message.Types.piocherCarte);
					msg.setJoueurCourant(joueurCourant);
					msg.setNbCartesAttaque(nbCarteAttaque);

					setChanged();
					notifyObservers(msg);
				} else {
					piocherCarte(joueurCourant, 1);

					Message msg = new Message(Message.Types.piocherCarte);
					msg.setJoueurCourant(joueurCourant);
					msg.setNbCartesAttaque(1);

					setChanged();
					notifyObservers(msg);
				}

			} else { // On joue la carte
				defausserCarte(joueurCourant, carte);

				Message msg = new Message(Message.Types.cartePosee);
				msg.setJoueurCourant(joueurCourant);
				msg.setCarteADonner(carte);

				setChanged();
				notifyObservers(msg);

				if (carte.getEffet() instanceof EffetAvecInput) { // On doit
																	// d'abord
																	// init
																	// l'effet
																	// avec les
																	// donnees
																	// nécessaires

					Integer[] data;
					if (carte.getEffet() instanceof EffetDonner) {

						if (joueurCourant instanceof JoueurArtificiel) {
							data = ((JoueurArtificiel) joueurCourant).choisirDataDonner();
							try {
								((EffetAvecInput) carte.getEffet()).setData(data, joueurCourant);
							} catch (ErreurDonneesEffet e) {
								e.printStackTrace();
							}
						} else {
							effetJouableDirectement = false;

							msg = new Message(Message.Types.choixDonnerCarte);
							msg.setEffetAvecInputEnCours((EffetAvecInput) carte.getEffet());
							msg.setJoueurCourant(joueurCourant);
							setChanged();

							notifyObservers(msg);
						}

					} else { // Effet EffetChangerCouleur

						if (joueurCourant instanceof JoueurArtificiel) {
							data = ((JoueurArtificiel) joueurCourant).choisirDataChangerCouleur();
							try {
								((EffetAvecInput) carte.getEffet()).setData(data, joueurCourant);
							} catch (ErreurDonneesEffet e) {
								e.printStackTrace();
							}
						} else {
							effetJouableDirectement = false;

							msg = new Message(Message.Types.choixChangerCouleur);
							msg.setEffetAvecInputEnCours((EffetAvecInput) carte.getEffet());
							msg.setJoueurCourant(joueurCourant);
							setChanged();

							notifyObservers(msg);
						}
					}

				}
				if (effetJouableDirectement) {
					setChanged();
					notifyObservers(carte.getEffet().action(joueurCourant)); // On
																				// joue
																				// la
																				// carte
																				// et
																				// on
																				// indique
																				// son
																				// action
				}
			}
		} else {
			throw new ErreurCarteInposable();
		}

		if (!(joueurCourant instanceof JoueurArtificiel) && effetJouableDirectement) {
			finirTour(joueurCourant);
		}
	}

	public synchronized void annoncer(Joueur joueurCourant, String annonce) {
		Message msg = new Message(Message.Types.joueurAnnonce);
		msg.setJoueurCourant(joueurCourant);
		msg.setAnnonce(annonce);
		setChanged();
		notifyObservers(msg);

		switch (annonce) {
		case Jeu.ANNONCE_CARTE:
			joueurCourant.setPeutFinir(true);

			msg = new Message(Message.Types.annonceCarte);
			msg.setJoueurCourant(joueurCourant);
			setChanged();
			notifyObservers(msg);
			break;

		case Jeu.ANNONCE_CONTRE_CARTE:
			boolean fausseAnnonce = true;
			for (Joueur joueur : getJoueurs()) {
				if (!joueur.isPeutFinir() && joueur.getMain().size() == 1 && joueur != joueurCourant) {
					piocherCarte(joueur, 2);

					msg = new Message(Message.Types.annonceContreCarteReussi);
					msg.setJoueurVictime(joueur);
					msg.setJoueurCourant(joueurCourant);

					setChanged();
					notifyObservers(msg);

					fausseAnnonce = false;
				}
			}
			if (fausseAnnonce) {
				piocherCarte(joueurCourant, 2);

				msg = new Message(Message.Types.annonceContreCarteEchoue);
				msg.setJoueurCourant(joueurCourant);

				setChanged();
				notifyObservers(msg);
			}

			break;

		default:
			setChanged();
			notifyObservers(new Message(Message.Types.annonceInconnue));
			break;
		}
	}

	public synchronized void initJoueurs() {
		setChanged();
		notifyObservers(new Message(Message.Types.initJoueurs));
	}
	
	/**
	 * Methode à appeler une fois setData() réaliser dans effet. Elle permet de jouer l'effet et de finir le tour du joueur humain.
	 * @param effet Effet qui va être jouer, doit être déja initialiser avec un appel de sa méthode setData().
	 * @param joueurCourant Joueur utilisant cet Effet, après cela son tour est terminé.
	 */
	public void jouerEffetAvecInputEnCours(EffetAvecInput effet, Joueur joueurCourant) {
		setChanged();
		notifyObservers(effet.action(joueurCourant)); 
		
		setChanged();
		notifyObservers(new Message(Message.Types.finTourJoueurHumain));
		finirTour(joueurCourant);
	}
	
	public synchronized void initCarteManche() {

		joueurs.clear();
		gagnants.clear();
		pioche.clear();
		defausse.clear();

		for (Joueur joueur : joueursInitiation) {
			joueur.getMain().clear();
			joueurs.add(joueur);
		}

		for (int valeur = nbCarteDeck; valeur < 13; valeur++) {
			for (int couleur = 0; couleur < 4; couleur++) {
				Carte carte = new Carte(valeur, couleur);
				this.variante.gererVariante(carte); // Application des effets en
													// fonction de la variante
				pioche.add(carte);
			}
		}

		Collections.shuffle(pioche);

		int nbpiocher = 0;
		if (joueurs.size() == 2) {
			nbpiocher = 10;
		} else if (joueurs.size() == 3) {
			nbpiocher = 8;
		} else {
			nbpiocher = 6;
		}
		for (Joueur joueur : getJoueurs()) {
			piocherCarte(joueur, nbpiocher);
		}

		defausse.add(pioche.removeLast());
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

	private Joueur getJoueurCourant() {
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
		for (int i = 0; i < nb; i++) {
			if (pioche.isEmpty()) {

				for (Carte carte : defausse) {
					pioche.add(carte);
				}

				Carte carteDefausse = defausse.getLast();
				defausse.clear();
				defausse.add(carteDefausse);

				Collections.shuffle(pioche);
			}
			joueur.getMain().add(pioche.removeLast());
		}
	}

	public boolean isCartePosable(Carte carte) {
		if (defausse.isEmpty() || carte == null) {
			return true;
		}

		if (modeAttaque) {
			if ((carte.getEffet() instanceof EffetAttaque && !(((EffetAttaque) carte.getEffet()).isContrable())) || carte.getEffet() instanceof EffetContrerChangerCouleur) {
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
			for (Joueur joueur : joueursInitiation) {
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
			if (gagnants.size() == 1) {

				gagnants.getFirst().addScore(50);
				joueurs.getFirst().addScore(20);

			} else if (gagnants.size() == 2) {

				gagnants.getFirst().addScore(50);
				gagnants.get(1).addScore(20);
				joueurs.getFirst().addScore(10);

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
				if (joueursInitiation.get(i).getScore() >= 100) {
					b = true;
				}
			}
		} else if (methodeCompte == COMPTE_POSITIF) {
			for (int i = 0; i < joueursInitiation.size(); i++) {
				if (joueursInitiation.get(i).getScore() >= 100) {
					b = true;
				}
			}
		}
		return b;
	}
	


}
