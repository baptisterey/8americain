package controleur;

import model.*;
import view.IHM;
import view.InterfaceConsole;
import view.Observateur;

public class Controleur {

	private Observateur obs;
    private Jeu jeu;
    
	public Controleur () {
		this.setJeu(Jeu.getInstance());
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public void setObservateur(Observateur obs) {
		this.obs = obs;
	}
	
	public Observateur getObservateur() {
		return obs;
	}

	// MÃ©thode MAIN du logiciel.
	public static void main(String [] args) {
		
		Controleur controleur = new Controleur();
        IHM ihm = new InterfaceConsole(controleur);
        
	}

	
	public void commencerPartie(){
		
		while(true){
				int nbManche = 1;
				getJeu().initCarteManche();
				jouerManche(nbManche);
				
				getJeu().compterScore();
				nbManche++;		
		}
		
	}
	
	public void jouerManche(int nbManche) {
		
		getObservateur().notifier("--- MANCHE N°"+nbManche+" ---");
		while(!getJeu().isMancheOver()){
			Joueur joueurCourant = getJeu().getJoueurCourant();
			jouerTour(joueurCourant);
			
		}
	}
	
	public void jouerTour(Joueur joueurCourant) {
		getObservateur().notifier("-- TOUR DE "+joueurCourant.getPseudo()+" --");
		if(joueurCourant.isPeutJouer() && !joueurCourant.isAFini() ) {
			Carte carte;
			 do {
					if(joueurCourant instanceof JoueurArtificiel){
						carte = ((JoueurArtificiel) joueurCourant).choisirCarte();
					}else {
						int indexCarte = obs.choixIndexCarte(joueurCourant);
						if(indexCarte==-1) {
							carte = null;
						}else {
							carte = getJeu().getJoueurs().get(getJeu().getJoueurs().indexOf(joueurCourant)).getMain().get(indexCarte); 
						}
							}
			} while (!getJeu().isCartePosable(carte));
			 
			if(carte == null) {
				if(getJeu().isModeAttaque()) {
					int nbCarteAttaque = getJeu().getNbCarteAttaque();
					getJeu().piocherCarte(joueurCourant, nbCarteAttaque);
					getJeu().setModeAttaque(false);
					String messagePiocherCarte = joueurCourant.getPseudo()+" pioche "+nbCarteAttaque+" cartes!";
					getObservateur().notifier(messagePiocherCarte);
				}else {
					getJeu().piocherCarte(joueurCourant, 1);
					String messagePiocherCarte = joueurCourant.getPseudo()+" pioche une carte!";
					getObservateur().notifier(messagePiocherCarte);
				}
				
			}else { // On joue la carte
				
				getJeu().defausserCarte(joueurCourant, carte);
				String messagePoserCarte = joueurCourant.getPseudo()+" pose un(e) "+ carte.toString()+"!";
				getObservateur().notifier(messagePoserCarte);
				
				
				if(carte.getEffet() instanceof EffetAvecInput){ // On doit d'abord init l'effet avec les données nécessaires
					
					if(carte.getEffet() instanceof EffetDonner){
						int [] data;
						if (joueurCourant instanceof JoueurArtificiel){
							data = ((JoueurArtificiel) joueurCourant).choisirDataDonner();
						}else {
							data = getObservateur().choixIndexDonner(joueurCourant);
						}
						
						((EffetAvecInput) carte.getEffet()).setData(data);
					}
					
					
				}
				
				String messageEffet = carte.getEffet().action(joueurCourant);
				getObservateur().notifier(messageEffet);
				
				
			}
			
		}else {
			String messageSauterTour = joueurCourant.getPseudo()+ " ne peut pas jouer!";
			getObservateur().notifier(messageSauterTour);
			joueurCourant.setPeutJouer(true);
		}
		
		if (joueurCourant.getMain().isEmpty()) {
			Jeu.getInstance().getGagnants().add(joueurCourant);
			joueurCourant.setAFini(true);
		}
	}
	
}
