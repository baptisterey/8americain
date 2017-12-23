package view.interfaceConsole;

import java.util.LinkedList;

import controleur.Controleur;
import model.Joueur;
import model.JoueurArtificiel;


public class InterfaceConsoleInitJoueurs extends InterfaceConsole {

	public InterfaceConsoleInitJoueurs(Controleur ctrl, Thread th) {
		super(ctrl);
		setThread(th);	
	}
	
	
	@Override
	public void run() {
		LinkedList<Joueur> joueursInit = new LinkedList<Joueur>();
		
		
		System.out.println("---- CREATION AUTO DES JOUEURS (POUR TESTER LE RESTE) ----");
		
		joueursInit.add(new Joueur("Civetdelapin"));
		joueursInit.add(new JoueurArtificiel("AI_1", 0));
		joueursInit.add(new JoueurArtificiel("AI_2", 1));
		
		
		/*
		 * Scanner sc = new Scanner(System.in); System.out.println(
		 * "---- CREATION DU JOUEUR ----"); System.out.print( "Entrer votre nom : ");
		 * String nom = sc.nextLine(); Joueur j = new Joueur(nom);
		 * this.getControleur().getJeu().getJoueurs().add(j);
		 * System.out.println("---- CREATION DES JOUEURS ARTIFICELS ----");
		 * System.out.print("Combien de joueurs artificiels ? "); int nbJoueur =
		 * sc.nextInt(); int strategie; for (int i = 1 ; i <= nbJoueur ; i++) {
		 * 
		 * System.out.print("Entrer nom joueur"+i+": ");
		 * 
		 * nom = sc.next();
		 * 
		 * System.out.print("Entrer stratégie joueur" +i+
		 * " (taper 0 pour passif, 1 pour agréssif) :");
		 * 
		 * strategie = sc.nextInt();
		 * 
		 * j = new JoueurArtificiel(nom, strategie);
		 * 
		 * 
		 * this.getControleur().getJeu().getJoueurs().add(j); } for (int i = 0 ; i <
		 * this.getControleur().getJeu().getJoueurs().size() ; i++) {
		 * this.getControleur().getJeu().getJoueursInitiation().add(this.
		 * getControleur() .getJeu().getJoueurs().get(i)); }
		 */

		getControleur().getJeu().setJoueursInitiation(joueursInit);
		getControleur().getJeu().commencerPartie();
	}

}
