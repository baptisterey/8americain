package view;
import model.*;

public interface Observateur {
	
	public void notifier(String msg);
	public int choixIndexCarte(Joueur joueurCourant);
	
	public int[] choixIndexDonner(Joueur joueurCourant);
	
}
