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

	// Méthode MAIN du logiciel.
	public static void main(String [] args) {
		
		Controleur controleur = new Controleur();
        IHM ihm = new InterfaceConsole(controleur);
        
	}

	
}
