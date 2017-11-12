package controller;

import model.*;
import view.Observateur;

public class Controller {

	
	private Observateur obs;
    private Jeu jeu;
    
    public Controller () {
    	this.jeu = Jeu.getInstance();
    }
    
    // Méthode MAIN du logiciel.
    public static void main(String [] args) {
    	
    	
    }

}
