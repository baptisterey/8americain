package model;

import java.util.ArrayList;
import java.util.List;


public class Joueur {
    
    private String pseudo;

  
    private int score;


    private List<Carte> main = new ArrayList<Carte> ();

    public Carte choisirCarte() {
		return null;
    }

	public List<Carte> getMain() {
		return main;
	}

}
