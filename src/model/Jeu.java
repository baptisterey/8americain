package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Jeu extends Observable {
    
    private int nbCarteModeAttaque;

    
    private boolean modeAttaque;

    
    private List<Joueur> joueurs = new ArrayList<Joueur> ();

   
    private List<Carte> pioche = new ArrayList<Carte> ();

    
    private List<Carte> defausse = new ArrayList<Carte> ();

    
    public Joueur getJoueurCourant() {
		return null;
    }

    
    public void defausserCarte(Joueur joueurCourant, Carte carte) {
    }

    
    public void piocherCarte(Joueur joueur, int nb) {
    }

    
    public boolean isCartePosable(Carte carte) {
		return true;
    }

   
    public static Jeu getInstance() {
		return null;
    }

    
    public void jouerPartie() {
    }

    
    public boolean isPartieOver() {
		return false;
    }

    
    public void choisirCarte(Joueur joueurCourant) {
    }

   
    public Joueur getJoueurSuivant() {
		return null;
    }


    public void setModeAttaque(boolean p1) {
    }

    public void addCarteAttaque(int nbCarteAPiocher) {
    }

    
    public void setNbCarteModeAttaque(int p1) {
    }

}
