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

	// Méthode MAIN du logiciel.
	public static void main(String [] args) {
		
		Controleur controleur = new Controleur();
        IHM ihm = new InterfaceConsole(controleur);
        
	}

	
	public void commencerPartie(){
		
		while(true){
			getJeu().initCarteManche();
			
			while(!getJeu().isMancheOver()){
				
				Joueur joueurCourant = getJeu().getJoueurCourant();
				
				if(joueurCourant.isPeutJouer()) {
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
						getJeu().piocherCarte(joueurCourant, 1);
						String messagePiocherCarte = joueurCourant.getPseudo()+" pioche une carte!";
						getObservateur().notifier(messagePiocherCarte);
					}else { // On joue la carte
						String messagePoserCarte = joueurCourant.getPseudo()+" pose un(e) "+ carte.toString()+"!";
						getObservateur().notifier(messagePoserCarte);
						
						String messageEffet = carte.getEffet().action(joueurCourant);
						getObservateur().notifier(messageEffet);
						
						getJeu().defausserCarte(joueurCourant, carte);
					}
					
				}else {
					String messageSauterTour = joueurCourant.getPseudo()+ " ne peut pas jouer!";
					getObservateur().notifier(messageSauterTour);
					joueurCourant.setPeutJouer(true);
				}	
			}
			
		}
		
	}
	
}
