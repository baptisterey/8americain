package model;

public class EffetSauterTour implements Effet {

    
	public void action(Joueur joueurCourant) {
		
		Jeu.getInstance().getJoueurSuivant(joueurCourant).setPeutJoueur(false);
    
	}

	@Override
	public String getMessage(Joueur joueurCourant) {
		String str = joueurCourant.getPseudo()+" empêche "+Jeu.getInstance().getJoueurSuivant(joueurCourant).getPseudo()+" de jouer !";
		return str;
	}

}
