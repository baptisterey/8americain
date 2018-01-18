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

	public final static int DECK_52_CARTES = 0;
	public final static int DECK_32_CARTES = 5;

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

	public int getNumManche() {
		return numManche;
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
	 * Notifie tous les Observateurs, indiquant qu'une partie est prête à être
	 * créer.
	 */
	public void initPartie() {
		setChanged();
		notifyObservers(new Message(Message.Types.initPartie));
	}

	/**
	 * Initialise le compteur de manche à 1, notifie les Observateurs de la nouvelle
	 * partie et appelle la méthode commencerNouvelleManche();
	 */
	public void commencerPartie() {
		numManche = 1;

		// On place de le joueur humain en premier dans l'array pour qu'il soit le
		// premier à jouer
		Joueur joueurHumain = null;
		for (Joueur joueur : joueursInitiation) {
			if (!(joueur instanceof JoueurArtificiel)) {
				joueurHumain = joueur;

			}
		}

		joueursInitiation.remove(joueurHumain);
		joueursInitiation.addFirst(joueurHumain);

		Message msg = new Message(Message.Types.debutPartie);
		msg.setJoueurCourant(getJoueurHumain());
		setChanged();
		notifyObservers(msg);

		commencerNouvelleManche();
	}

	/**
	 * Fait jouer un tour à tous les Joueurs Artificiels en appelant
	 * {@link #jouerTourJoueursArtificiels()} et lorsque c'est à Joueur Humain de
	 * jouer, on notfie avec un Message de type tourJoueurHumain. Si la manche est
	 * terminé (@ {@link #isMancheOver()}) appelle {link #finirManche()}
	 */
	public void jouerManche() {
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

	/**
	 * Permet d'obtenir le jour humain de la partie
	 * 
	 * @return Le seul joueur humain de la partie
	 */
	private Joueur getJoueurHumain() {
		for (Joueur joueur : getJoueursInitiation()) {
			if (!(joueur instanceof JoueurArtificiel)) {
				return joueur;
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private Joueur jouerTourJoueursArtificiels() {
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

	/**
	 * Appelle {@link #initCarteManche()} puis notifie avec un message de type nouvelleManche et enfin appelle {@link #jouerManche()}
	 */
	public void commencerNouvelleManche() {
		initCarteManche();

		Message msg = new Message(Message.Types.nouvelleManche);
		msg.setNumeroManche(numManche);
		setChanged();
		notifyObservers(msg);
		jouerManche();
	}

	public void finirTour(Joueur joueurCourant) {

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
	 * Si la partie est finie, appelle la méthode finirPartie(), sinon incrémente le
	 * numéro de manche et appelle la méthode commencerNouvelleManche();
	 */
	public void finirManche() {
		compterScore();
		if (!isPartieOver()) {
			numManche++;
			commencerNouvelleManche();
		} else {
			finirPartie();
		}
	}

	public void finirPartie() {
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

	public void jouerCarte(Joueur joueurCourant, Carte carte) throws ErreurCarteInposable {
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

	public void annoncer(Joueur joueurCourant, String annonce) {
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

	public void initJoueurs() {
		setChanged();
		notifyObservers(new Message(Message.Types.initJoueurs));
	}

	/**
	 * Methode à appeler une fois setData() réaliser dans effet. Elle permet de
	 * jouer l'effet et de finir le tour du joueur humain.
	 * 
	 * @param effet
	 *            Effet qui va être jouer, doit être déja initialiser avec un appel
	 *            de sa méthode setData().
	 * @param joueurCourant
	 *            Joueur utilisant cet Effet, après cela son tour est terminé.
	 */
	public void jouerEffetAvecInputEnCours(EffetAvecInput effet, Joueur joueurCourant) {
		setChanged();
		notifyObservers(effet.action(joueurCourant));

		setChanged();
		notifyObservers(new Message(Message.Types.finTourJoueurHumain));
		finirTour(joueurCourant);
	}
	
	
	/**
	 * 
	 */
	public void initCarteManche() {

		joueurs.clear();
		gagnants.clear();
		pioche.clear();
		defausse.clear();

		for (Joueur joueur : joueursInitiation) {
			joueur.getMain().clear();
			joueurs.add(joueur);
			joueur.setPeutJouer(true);
			joueur.setPeutFinir(false);
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

	/**
	 * Inverse l'array joueurs grâce à {@link Collections#reverse(java.util.List)}
	 */
	public void changerSensJeu() {
		Collections.reverse(joueurs);

		// OBLIGATOIRE Sinon le joueur rejoue.
		joueurs.add(joueurs.removeFirst());
	}
	
	/**
	 * Place le joueurCourant au début de la liste {@link #joueurs}
	 * @param joueurCourant Le joueur qui va rejouer
	 */
	public void faireRejouer(Joueur joueurCourant) {
		while (!joueurCourant.equals(joueurs.get(0))) {
			joueurs.add(joueurs.removeFirst());
		}
	}

	/**
	 * Ajoute la carte dans la défausse et la supprimer de la main du joueur
	 * 
	 * @param joueurCourant
	 *            Le joueur qui joue la carte
	 * @param carte
	 *            La carte à défausser
	 */
	public void defausserCarte(Joueur joueurCourant, Carte carte) {
		defausse.add(carte);
		joueurCourant.getMain().remove(carte);
	}

	/**
	 * Fait piocher autant de carte que nécéssaire au joueur. Si la pioche est vide,
	 * on mélange la défausse et on l'ajoute à la pioche.
	 * 
	 * @param joueur
	 *            Le joueur piochant les cartes
	 * @param nb
	 *            Le nombre de cartes à piocher
	 */
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

	/**
	 * Indique si la carte est posable par rapport au sommet de la défausse. En mode
	 * normal, la couleur et la valeur de la carte sont regardés, en mode attaquen,
	 * seul les cartes attaques et contre sont posables.
	 * 
	 * @param carte
	 *            La carte qui va être tester
	 * @return Vrai si elle est posable, Faux sinon.
	 */
	public boolean isCartePosable(Carte carte) {
		if (defausse.isEmpty() || carte == null) {
			return true;
		}

		if (modeAttaque) {
			if ((carte.getEffet() instanceof EffetAttaque && (((EffetAttaque) carte.getEffet()).isContrable()))
					|| carte.getEffet() instanceof EffetContrerChangerCouleur) {
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

	/**
	 * Le nombre de joueurs actifs (les joueurs dans l'array joueurs)
	 * 
	 * @return le nombre de joueurs actifs
	 */
	public int getNombreJoueursActifs() {
		return getJoueurs().size();
	}

	/**
	 * Regarde en fonction de la methode de comptage si la manche est terminée.
	 * 
	 * @return Vrai si la manche est terminé, Faux sinon.
	 */
	public boolean isMancheOver() {

		if (this.methodeCompte == COMPTE_NEGATIF) { // Si un joueur n'a plus de cartes dans sa main, la manche est
													// finie.
			for (Joueur joueur : joueursInitiation) {
				if (joueur.getMain().isEmpty()) {
					return true;
				}
			}
		} else if (this.methodeCompte == COMPTE_POSITIF) { // Si un podium peut être formé, la manche est finie.
			if ((this.gagnants.size() > 2) || (getNombreJoueursActifs() < 2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Attribue pour chacun des joueurs le score adéquat en fonction de la méthode
	 * de comptage.
	 */
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

	/**
	 * Indique si la partie est terminée en fonction des scores des joueurs et de la
	 * méthode de comptage.
	 * 
	 * @return Vrai si la partie est terminée, Faux sinon.
	 */
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
