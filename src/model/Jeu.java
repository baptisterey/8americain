package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Jeu {
	
    //DEFINITION DES VARIABLES REPRESENTANTS CHAQUE VARIANTE
	public final static int BASIQUE = 0;
    public final static int MINIMALE = 1;
    public final static int MONCLAR = 2;
    
    //DEFINITION DES VARIABLES REPRESENTANTS CHAQUE METHODE DE COMPTAGE
    public final static int COMPTEPOSITIF = 0;
    public final static int COMPTENEGATIF = 1;
	
    
    private int variante = BASIQUE;
    private int methodeCompte = COMPTEPOSITIF;
    private int nbCarteModeAttaque = 0;
    private int carteAPiocher = 5;
    
    private boolean modeAttaque = false;
    private LinkedList<Joueur> joueurs = new LinkedList<Joueur> ();
    private LinkedList<Carte> pioche = new LinkedList<Carte> ();
    private LinkedList<Carte> defausse = new LinkedList<Carte> ();
   
    
    private static Jeu instance;
   
    private Jeu() {
    	
    }
    
    /**
     * Implementation du design patern SINGLETON
     */
    public static Jeu getInstance() {
    	if(instance !=null) {
			return instance;
		}else {
			Jeu.instance = new Jeu();
			return Jeu.instance;
		}
    }
    
    public List<Joueur> getJoueurs(){
    	return joueurs;
    }
    

    public List<Carte> getDefausse(){
    	return defausse;
    }
    
    public void initCarteManche() {
    	
    	for(Joueur joueur : joueurs) {
    		joueur.getMain().clear();
    	}
    	
    	pioche.clear();
    	defausse.clear();
    	
    	//Création des 32 cartes (TODO faire avec 52)
    	for (int valeur = 3; valeur < 13; valeur++) {
    		for (int couleur = 0; couleur < 4; couleur++) {
    			Carte carte = new Carte(valeur,couleur);
    			gererVariante(carte); // Application des effets en fonction de la variante
    			
    			
    			pioche.add(carte);	
			}
		}
    	
    	Collections.shuffle(pioche);
    	
    	for (int i = 0; i <this.carteAPiocher ; i++){
    		for(Joueur joueur : getJoueurs()){
        		piocherCarte(joueur, 1);
        	}
    	}
    }
    
    private void gererVariante(Carte carte) {
    	int valeur = carte.getValeur();
    	// TODO Gérer les variantes
    	switch (valeur) {
			case Carte.CINQ:
				carte.setEffet(new EffetDonner());
				break;
			case Carte.SEPT:
				carte.setEffet(new EffetSauterTour());
				break;
			case Carte.HUIT:
				carte.setEffet(new EffetContrerChangerCouleur());
				break;
			case Carte.DIX:
				carte.setEffet(new EffetRejouer());
				break;
			case Carte.VALET:
				carte.setEffet(new EffetChangerSensJeu());
				break;
			case Carte.AS:
				carte.setEffet(new EffetAttaque(2, true));
				break;	
    	}
    }
    
  
    public boolean isModeAttaque() {
    	return this.modeAttaque;
    }
    
    public void setModeAttaque(boolean bool) {
    	this.modeAttaque = bool;
    }
    
    public int getNbCarteAttaque() {
    	return this.nbCarteModeAttaque;
    }

    public void addCarteAttaque(int nbCarteAPiocher) {
    	this.nbCarteModeAttaque+=nbCarteAPiocher;
    }
 
    public void setNbCarteModeAttaque(int nbCarte) {
    	this.nbCarteModeAttaque = nbCarte;
    }
    
    public Joueur getJoueurCourant() {
    	Joueur joueurCourant = joueurs.get(0);
    	joueurs.add(joueurs.removeFirst());
    	return joueurCourant;
    }
    
    public Joueur getJoueurSuivant(Joueur joueurCourant) {
		return joueurs.get((joueurs.indexOf(joueurCourant)+1) % joueurs.size());
    }
    
   
    public void changerSensJeu() {
    	Collections.reverse(joueurs);
    	
    	//OBLIGATOIRE Sinon le joueur rejoue.
    	joueurs.add(joueurs.removeFirst());
    }
    
    public void faireRejouer(Joueur joueurCourant) {
    	while(!joueurCourant.equals(joueurs.get(0))) {
    		joueurs.add(joueurs.removeFirst());
    	}
    }
    
    
    public void defausserCarte(Joueur joueurCourant, Carte carte) {
    	defausse.add(carte);
    	joueurCourant.getMain().remove(carte);
    }
    
    public void defausserCarte(Joueur joueurCourant, int indexCarte) {   
    	defausse.add(joueurCourant.getMain().get(indexCarte));
    	joueurCourant.getMain().remove(indexCarte);   	
    }

    public void piocherCarte(Joueur joueur, int nb) {
    	
    	if(pioche.isEmpty()) {
    		for(int i=0 ; i < defausse.size()-1 ; i++){
    			pioche.add(defausse.get(i));
    		}
    		Carte carteDefausse = defausse.getLast();
    		defausse.clear();
    		defausse.add(carteDefausse);
    		
    		Collections.shuffle(pioche);
    	}
    	
    	for(int i = 0; i< nb; i++) {
    		joueur.getMain().add(pioche.removeLast());
    	}
    }

    
    public boolean isCartePosable(Carte carte) {
    	if(defausse.isEmpty() || carte == null) {
			return true;
		}
    	
    	if(modeAttaque) {
    		if(carte.getEffet() instanceof EffetAttaque || carte.getEffet() instanceof EffetContrerChangerCouleur) {
    			return true;
    		}
    	}else {
    		
    		Carte carteTapis = defausse.getLast();
    		if(carteTapis.getCouleur() == carte.getCouleur() || carteTapis.getEffet() == carte.getEffet()) {
    			return true;
    		}
    	}
    	
		return false;
    }

   
    public boolean isMancheOver() {
    	for(Joueur joueur : joueurs) {
    		if(joueur.getMain().isEmpty()) {
    			return true;
    		}
    	}
		return false;
    }
    
    public void compterScore() {
    	switch (methodeCompte) {
	    	case COMPTENEGATIF:
	    		// COMPTENEGATIF 
	    		
				break;
	
			default:
				// COMPTEPOSITIF
				
				break;
		
    	}
    	
    }

    public boolean isPartieOver() {
    	
    	return true;
    }
}
