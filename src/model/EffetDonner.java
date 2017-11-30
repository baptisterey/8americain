package model;


public class EffetDonner extends EffetAvecInput {
	
	int indexJoueurADonner;
	int indexCarteADonner;
	
	private static final int score = 20;
	
	public EffetDonner(){
		super(EffetDonner.score);
	}
	
	public String action(Joueur joueurCourant) {
		Joueur joueurADonner = Jeu.getInstance().getJoueurs().get(indexJoueurADonner);
		Carte carteADonner = joueurCourant.getMain().get(indexCarteADonner);
		
		joueurADonner.getMain().add(carteADonner);
		joueurCourant.getMain().remove(carteADonner);
		
		String message = getMessage(joueurCourant, joueurADonner, carteADonner);
		resetData();
		return message;
    }

	@Override
	public void setData(int [] data) {
		indexCarteADonner = data[0];
		indexJoueurADonner = data[1];
	}

	private String getMessage(Joueur joueurCourant, Joueur jouerADonner, Carte carteADonner){
		String str = joueurCourant.getPseudo()+" ajoute un(e) "+ carteADonner.toString() + " dans la main de "+ jouerADonner.getPseudo();
		return str;
	}

	@Override
	public void resetData() {
		indexJoueurADonner = -1;
		indexCarteADonner = -1;
	}
	
}
