package view;

import java.util.Observer;

import controleur.Controleur;

public abstract class IHM implements Observer{

	private Controleur controleur;
	
	public IHM(Controleur ctrl) {
		this.setControleur(ctrl);
		//ctrl.setObservateur(this);
	}
	
	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

}
