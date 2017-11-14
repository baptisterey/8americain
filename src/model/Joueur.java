package model;

import java.util.ArrayList;
import java.util.List;


public class Joueur {
    
    private String pseudo;
    private int score = 0;
    private boolean peutJoueur = true;

    public Joueur(String pseudo) {
    	this.pseudo = pseudo;
    }
    
    public boolean isPeutJouer() {
		return peutJoueur;
	}

	public void setPeutJoueur(boolean peutJoueur) {
		this.peutJoueur = peutJoueur;
	}

    private List<Carte> main = new ArrayList<Carte> ();
	
	public List<Carte> getMain() {
		return main;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	
	

}
