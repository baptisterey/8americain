package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public class Jeu extends Observable {
    
    private int nbCarteModeAttaque = 0;
    private boolean modeAttaque = false;
    private List<Joueur> joueurs = new ArrayList<Joueur> ();
    private List<Carte> pioche = new ArrayList<Carte> ();
    private List<Carte> defausse = new ArrayList<Carte> ();
    
    private static Jeu instance;
    
    private Jeu() {
    	
    }
    
    public void initCarte(int variante) {
    	
    }
    
    public Joueur getJoueurCourant() {
    	return null;
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
    		Carte carteDefausse = defausse.get(defausse.size()-1);
    		defausse.clear();
    		defausse.add(carteDefausse);
    		
    		Collections.shuffle(pioche);
    	}
    	
    	for(int i = 0; i< nb; i++) {
    		joueur.getMain().add(pioche.get(pioche.size()-1));
    		pioche.remove(pioche.size()-1);
    	}
    	
    }

    
    public boolean isCartePosable(Carte carte) {
    	if(modeAttaque) {
    		
    	}else {
    		
    	}
    	
		return true;
    }

   
    public static Jeu getInstance() {
    	if(instance !=null) {
			return instance;
		}else {
			return new Jeu();
		}
    }

    
    public void jouerPartie() {
    	
    }

    
    public boolean isPartieOver() {
		return false;
    }

    
    public Joueur getJoueurSuivant() {
		return null;
    }


    public void setModeAttaque(boolean bool) {
    	this.modeAttaque = bool;
    }

    public void addCarteAttaque(int nbCarteAPiocher) {
    	this.nbCarteModeAttaque+=nbCarteAPiocher;
    }

    
    public void setNbCarteModeAttaque(int nbCarte) {
    	this.nbCarteModeAttaque = nbCarte;
    }

}
