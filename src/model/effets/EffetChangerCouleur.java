package model.effets;

import model.Carte;
import model.Jeu;
import model.Joueur;
import model.Message;

public class EffetChangerCouleur extends EffetAvecInput {

	protected int nouvelleCouleur = -1;

	private static final int score = 50;

	public EffetChangerCouleur() {
		super(EffetChangerCouleur.score);
	}

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

	@Override
	public void setData(int[] data) {
		this.nouvelleCouleur = data[0];
	}

	@Override
	public void resetData() {
		this.nouvelleCouleur = -1;
	}

}
