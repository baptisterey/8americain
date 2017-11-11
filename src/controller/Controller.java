package controller;

import model.*;

public class Controller {

    private Jeu jeu;
    
    public Controller () {
    	this.jeu = Jeu.getInstance();
    }
    
    // Méthode MAIN du logiciel.
    public static void main(String [] args) {
    	
    	Jeu jeu = Jeu.getInstance();
    	
    	jeu.getJoueurs().add(new Joueur("TEST1"));
    	jeu.getJoueurs().add(new Joueur("TEST2"));
    	jeu.getJoueurs().add(new Joueur("TEST3"));
    	
    	
    	
    	//TEST getJoueurCourant();
    	Joueur joueur = Jeu.getInstance().getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	
    	// changerSensJeu()
    	System.out.println("--- changerSensJeu() ---");
    	
    	Jeu.getInstance().changerSensJeu();
    	
    	
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	joueur = jeu.getJoueurCourant();
    	System.out.println(joueur.getPseudo());
    	
    	
    }

}
