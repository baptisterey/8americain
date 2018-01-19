package model.effets;

import model.Carte;
import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Donne une carte de la main du joueur courant e un joueur de son choix.
 */
public class EffetDonner extends EffetAvecInput {
	/**
	 * Le joueur e qui on va donner la Carte.
	 */
	Joueur joueurADonner;

	/**
	 * La carte choisi qui va etre donner.
	 */
	Carte carteADonner;

	/**
	 * Constante privee indiquant le score de cet Effet.
	 */
	private static final int score = 20;

	public EffetDonner() {
		super(EffetDonner.score);
	}

	/**
	 * Donne la carteADonner au joueurADonner et renvoie un msg de type effetDonner.
	 */
	public Message action(Joueur joueurCourant) {
		joueurADonner.getMain().add(carteADonner);
		joueurCourant.getMain().remove(carteADonner);

		Message msg = new Message(Message.Types.effetDonner);
		msg.setJoueurCourant(joueurCourant);
		msg.setJoueurVictime(joueurADonner);
		msg.setCarteADonner(carteADonner);

		resetData();
		return msg;
	}

	@Override
	public void setData(Integer[] data, Joueur joueurCourant) throws ErreurDonneesEffet {
		try {
			joueurADonner = Jeu.getInstance().getJoueurs().get(data[1]);
			carteADonner = joueurCourant.getMain().get(data[0]);
		} catch (IndexOutOfBoundsException e) {
			throw new ErreurDonneesEffet();
		}
	}

	@Override
	public void resetData() {
		joueurADonner = null;
		carteADonner = null;
	}

}
