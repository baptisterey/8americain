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
				Carte carte;
				if(joueurCourant instanceof JoueurArtificiel){
					carte = ((JoueurArtificiel) joueurCourant).choisirCarte();
				}else {
					carte = getJeu().getJoueurs().get(getJeu().getJoueurs().indexOf(joueurCourant)).getMain().get(obs.choixIndexCarte(joueurCourant)); 
				}
				
				
				String messagePoserCarte = joueurCourant.getPseudo()+" pose un "+ carte.toString()+"!";
				getObservateur().notifier(messagePoserCarte);
				
				String messageEffet = carte.getEffet().action(joueurCourant);
				getObservateur().notifier(messageEffet);
				
				
				getJeu().defausserCarte(joueurCourant, carte);
			}
			
		}
		
	}
	
}
