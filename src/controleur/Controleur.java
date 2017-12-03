package controleur;

import model.*;
import model.effets.EffetAvecInput;
import model.effets.EffetDonner;
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
		int nbManche = 1;
		Joueur gagnant = getJeu().getJoueursInitiation().get(0);
		while(!getJeu().isPartieOver()){
				
				getJeu().initCarteManche();
				jouerManche(nbManche);
				
				getJeu().compterScore();
				nbManche++;	
				
				for (int i = 0 ; i < getJeu().getJoueursInitiation().size() ; i++) {
					System.out.println(getJeu().getJoueursInitiation().get(i).getPseudo() + " a " + getJeu().getJoueursInitiation().get(i).getScore() + " points.");
				}
		}
		if (getJeu().getMethodeCompte() == Jeu.COMPTE_POSITIF) {
			for (int i = 1 ; i < getJeu().getJoueursInitiation().size() ; i++) {
				if (getJeu().getJoueursInitiation().get(i).getScore() > gagnant.getScore()) {
					gagnant = getJeu().getJoueursInitiation().get(i);
				}
			}
		} else if (getJeu().getMethodeCompte() == Jeu.COMPTE_NEGATIF) {
			for (int i = 1 ; i < getJeu().getJoueursInitiation().size() ; i++) {
				if (getJeu().getJoueursInitiation().get(i).getScore() < gagnant.getScore()) {
					gagnant = getJeu().getJoueursInitiation().get(i);
				}
			}
		}
		System.out.println(gagnant.getPseudo() + "gagne la partie !!");
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
		if(joueurCourant.isPeutJouer()) {
			Carte carte;
			 do {
					if(joueurCourant instanceof JoueurArtificiel){
						carte = ((JoueurArtificiel) joueurCourant).choisirCarte();
					}else {
						for (int i = 0 ; i < getJeu().getJoueurs().size()-1 ; i++) {
							getObservateur().notifier(getJeu().getJoueurs().get(i).getPseudo()+" a "+getJeu().getJoueurs().get(i).getMain().size()+" cartes.");
						}
						int indexCarte = obs.choixIndexCarte(joueurCourant);
						if(indexCarte==-1) {
							carte = null;
						}else {
							carte = joueurCourant.getMain().get(indexCarte); 
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
					int [] data;
					
					if(carte.getEffet() instanceof EffetDonner){
						
						if (joueurCourant instanceof JoueurArtificiel){
							data = ((JoueurArtificiel) joueurCourant).choisirDataDonner();
						}else {
							data = getObservateur().choixIndexDonner(joueurCourant);
						}
							
					}else { // Effet EffetChangerCouleur
						if (joueurCourant instanceof JoueurArtificiel){
							data = ((JoueurArtificiel) joueurCourant).choisirDataChangerCouleur();
						}else {
							data = getObservateur().choixChangerCouleur(joueurCourant);
						}
							
					}
					
					
					((EffetAvecInput) carte.getEffet()).setData(data);
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
			
			getObservateur().notifier(joueurCourant.getPseudo()+ " a fini !");
			
			Jeu.getInstance().getGagnants().add(joueurCourant);
			Jeu.getInstance().getJoueurs().remove(joueurCourant);
			
			//TODO REMOVE DE LA LISTE JOUEURS DANS JEU (FAUT CREER UNE AUTRE LISTE QUI CONTIENT TOUT LE TEMPS TOUT LES JOUEURS)
		}
	}
	
}
