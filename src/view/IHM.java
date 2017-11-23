package view;

import controleur.Controleur;

public abstract class IHM implements Observateur{

	private Controleur controleur;
	
	public IHM(Controleur ctrl) {
		this.setControleur(ctrl);
		ctrl.setObservateur(this);
		this.initJoueurs();
	}
	
	public Controleur getControleur() {
		return controleur;
	}

	public void setControleur(Controleur controleur) {
		this.controleur = controleur;
	}

	public abstract void initJoueurs();
	
	 
	
}
