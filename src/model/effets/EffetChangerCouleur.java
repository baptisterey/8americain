package model.effets;

import model.Carte;
import model.Jeu;
import model.Joueur;
import model.Message;

/**
 * Effet représentant une Carte qui peut changer de Couleur lorsqu'on la joue.
 * Elle a besoin d'être initialiser avec setData avant d'être jouer.
 */
public class EffetChangerCouleur extends EffetAvecInput {

	/**
	 * Indique la nouvelle couleur choisie.
	 */
	protected int nouvelleCouleur = -1;

	/**
	 * Constante privée indiquant le score de cet Effet.
	 */
	private static final int score = 50;

	public EffetChangerCouleur() {
		super(EffetChangerCouleur.score);
	}

	/**
	 * Change la couleur de la Carte en fonction de l'attribut nouvelleCouleur (doit
	 * être initialiser d'abord avec setData).
	 * Renvoie un message de type effetChangerCouleur.
	 */
	@Override
	public Message action(Joueur joueurCourant) {
		Carte carte = Jeu.getInstance().getDefausse().getLast();
		carte.setCouleur(nouvelleCouleur);

		Message msg = new Message(Message.Types.effetChangerCouleur);
		msg.setJoueurCourant(joueurCourant);
		msg.setNouvelleCouleur(nouvelleCouleur);

		resetData();
		return msg;
	}

	protected String getMessage(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo() + " a choisi la couleur " + Carte.COULEURS[nouvelleCouleur] + "!";
		return str;
	}

	public void setData(Integer[] data, Joueur joueurCourant) throws ErreurDonneesEffet {
		if (data[0] >= Carte.PIC && data[0] <= Carte.TREFLE) {
			nouvelleCouleur = data[0];
		} else {
			throw new ErreurDonneesEffet();
		}

	}

	@Override
	public void resetData() {
		this.nouvelleCouleur = -1;
	}

}
