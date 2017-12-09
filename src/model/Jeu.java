package model;


import java.util.Collections;
import java.util.LinkedList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import model.effets.EffetAttaque;
import model.effets.EffetContrerChangerCouleur;
import model.variantes.Basique;
import model.variantes.Variante;

public class Jeu extends java.util.Observable{

	
	// DEFINITION DES VARIABLES REPRESENTANTS CHAQUE METHODE DE COMPTAGE
	public final static int COMPTE_POSITIF = 0;
	public final static int COMPTE_NEGATIF = 1;

	private Variante variante = new Basique();
	private int methodeCompte = COMPTE_POSITIF;
	private int nbCarteModeAttaque = 0;

	public final static String ANNONCE_CARTE = "Carte";
	public final static String ANNONCE_CONTRE_CARTE = "Contre Carte";

	private boolean modeAttaque = false;
	private LinkedList<Joueur> joueurs = new LinkedList<Joueur>();
	private LinkedList<Joueur> joueursInitiation = new LinkedList<Joueur>();
	private LinkedList<Carte> pioche = new LinkedList<Carte>();
	private LinkedList<Carte> defausse = new LinkedList<Carte>();
	private LinkedList<Joueur> gagnants = new LinkedList<Joueur>();

  public int getMethodeCompte() {
		return methodeCompte;
	}
    
   public void setMethodeCompte(int methodeCompte) {
		this.methodeCompte = methodeCompte;
	}
      
	public LinkedList<Joueur> getJoueursInitiation() {

		return joueursInitiation;
	}

	public LinkedList<Joueur> getGagnants() {
		return gagnants;
	}

	private static Jeu instance;

	private Jeu() {

	}

	/**
	 * Implementation du design patern SINGLETON
	 */
	public static Jeu getInstance() {
		if (instance != null) {
			return instance;
		} else {
			Jeu.instance = new Jeu();
			return Jeu.instance;
		}
	}

	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}

	public LinkedList<Carte> getDefausse() {
		return defausse;
	}

	public void initCarteManche() {

		joueurs.clear();
		gagnants.clear();
		pioche.clear();
		defausse.clear();

		for (Joueur joueur : joueursInitiation) {
			joueur.getMain().clear();
			joueurs.add(joueur);
		}

		
		// Création des 32 cartes (TODO faire avec 52)
		for (int valeur = 5; valeur < 13; valeur++) {
			for (int couleur = 0; couleur < 4; couleur++) {
				Carte carte = new Carte(valeur, couleur);
				this.variante.gererVariante(carte); // Application des effets en fonction de la variante

				pioche.add(carte);
			}
		}

		Collections.shuffle(pioche);

		int nbpiocher = 0;
    	if (joueurs.size() == 2) {
   			nbpiocher = 10;
    	} else if (joueurs.size() == 3) {
    		nbpiocher = 8;
   		} else {
   			nbpiocher = 6;
    	}
    	for(Joueur joueur : getJoueurs()){
       		piocherCarte(joueur, nbpiocher);
       	}
    	
    	defausse.add(pioche.removeLast());
	}

    
    
	

	public boolean isModeAttaque() {
		return this.modeAttaque;
	}

	public void setModeAttaque(boolean bool) {

		if (!bool) {
			nbCarteModeAttaque = 0;
		}

		this.modeAttaque = bool;
	}

	public int getNbCarteAttaque() {
		return this.nbCarteModeAttaque;
	}

	public void addCarteAttaque(int nbCarteAPiocher) {
		this.nbCarteModeAttaque += nbCarteAPiocher;
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
		return joueurs.get((joueurs.indexOf(joueurCourant) + 1) % joueurs.size());
	}

	public void changerSensJeu() {
		Collections.reverse(joueurs);

		// OBLIGATOIRE Sinon le joueur rejoue.
		joueurs.add(joueurs.removeFirst());
	}

	public void faireRejouer(Joueur joueurCourant) {
		while (!joueurCourant.equals(joueurs.get(0))) {
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
    	for(int i = 0; i< nb; i++) {
	    	if(pioche.isEmpty()) {
	    		for(int j=0 ; i < defausse.size()-1 ; j++){
	    			pioche.add(defausse.get(j));
	    		}
	    		Carte carteDefausse = defausse.getLast();
	    		defausse.clear();
	    		defausse.add(carteDefausse);
	    		
	    		Collections.shuffle(pioche);
	    	}
    		joueur.getMain().add(pioche.removeLast());
    	}
    }

	public boolean isCartePosable(Carte carte) {
		if (defausse.isEmpty() || carte == null) {

			return true;
		}

		if (modeAttaque) {
			if (carte.getEffet() instanceof EffetAttaque || carte.getEffet() instanceof EffetContrerChangerCouleur) {
				return true;
			}
		} else {
			if (carte.getEffet().isAlwaysPosable()) {
				return true;
			} else {
				Carte carteTapis = defausse.getLast();

				if (carteTapis.getCouleur() == carte.getCouleur() || carteTapis.getValeur() == carte.getValeur()) {
					return true;
				}
			}

		}

		return false;
    }
    
    public int getNombreJoueursActifs() {
    	return getJoueurs().size();
    }

   
    public boolean isMancheOver() {

	    if (this.methodeCompte == COMPTE_NEGATIF) {	
    		for(Joueur joueur : joueursInitiation) {
	    		if(joueur.getMain().isEmpty()) {
	    			return true;
	    		}
    		}
    	} else if (this.methodeCompte == COMPTE_POSITIF) {
    		if ( (this.gagnants.size() > 2) || (getNombreJoueursActifs() < 2) ) {
    			return true;
    		}
    	}
		return false;
    }
    
    public void compterScore() {
    	switch (methodeCompte) {
	    	case COMPTE_NEGATIF:
	    		// COMPTENEGATIF 
	    		
	    		for (int i = 0 ; i < joueurs.size() ; i++) {
	    			for (int j = 0 ; j < joueurs.get(i).getMain().size() ; j++) {
	    				joueurs.get(i).addScore(joueurs.get(i).getMain().get(j).getEffet().getScoreValue());
	    			}
	    		}
	    		
				break;
	
			default:
				// COMPTEPOSITIF
				if (gagnants.size() == 1) {
				
					gagnants.getFirst().addScore(50);
					joueurs.getFirst().addScore(20);
					
				} else if (gagnants.size() == 2){
					
					gagnants.getFirst().addScore(50);
					gagnants.get(1).addScore(20);
					joueurs.getFirst().addScore(10);
					
				} else {
					gagnants.getFirst().addScore(50);
					gagnants.get(1).addScore(20);
					gagnants.get(2).addScore(10);
				}
				break;
		
    	}
    	
    }

    public boolean isPartieOver() {
    	boolean b = false;
    	if (methodeCompte == COMPTE_NEGATIF) {
    		for (int i = 0 ; i < joueursInitiation.size() ; i++) {
    			if (joueursInitiation.get(i).getScore() >= 100) {
   				b = true;
   				}
   			}
    	} else if (methodeCompte == COMPTE_POSITIF) {
    		for (int i = 0 ; i < joueursInitiation.size(); i++) {
    			if (joueursInitiation.get(i).getScore() >= 100) {
    				b = true;
   				}
 			}
    	}
    	return b;
    }




}
