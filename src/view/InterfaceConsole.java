package view;

import java.util.Observable;
import java.util.Scanner;

import controleur.Controleur;
import model.JoueurArtificiel;
import model.Carte;
import model.Jeu;
import model.Joueur;


public class InterfaceConsole extends IHM {

	public InterfaceConsole(Controleur ctrl) {
		super(ctrl);
	}

	@Override
	public void notifier(String msg) {
		System.out.println(msg);
	}

	@Override
	public int choixIndexCarte(Joueur joueurCourant) {
		String carteDefausse = "Carte sommet d�fausse : ";
		if(Jeu.getInstance().getDefausse().isEmpty()) {
			carteDefausse+="aucune!";
			
		}else {
			carteDefausse+=Jeu.getInstance().getDefausse().get(Jeu.getInstance().getDefausse().size()-1);
		}
		System.out.println(carteDefausse);
		
		System.out.println("-- MAIN DE "+joueurCourant.getPseudo()+" --");
		for(Carte carte : joueurCourant.getMain()){
			System.out.println("("+joueurCourant.getMain().indexOf(carte)+")"+carte.toString());
		}
		Scanner sc = new Scanner(System.in);
		System.out.print("Choisir index Carte : ");
		int index = sc.nextInt();
		
		return index;
	}

	@Override
	public void initJoueurs() {
		
		this.getControleur().getJeu().getJoueurs().clear();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("---- CREATION DU JOUEUR ----");
		System.out.print("Entrer votre nom : ");
		String nom = sc.nextLine();
		Joueur j = new Joueur(nom);
		this.getControleur().getJeu().getJoueurs().add(j);
		System.out.println("---- CREATION DES JOUEURS ARTIFICELS ----");
		System.out.print("Combien de joueurs artificiels ? ");
		int nbJoueur = sc.nextInt();
		int strategie;
		for (int i = 1 ; i <= nbJoueur ; i++) {
			
			System.out.print("Entrer nom joueur"+i+": ");
			
			nom = sc.next();
			
			System.out.print("Entrer stratégie joueur"+i+" (taper 0 pour passif, 1 pour agréssif) :");
			
			strategie = sc.nextInt();
			
			j = new JoueurArtificiel(nom, strategie);
			
			
			this.getControleur().getJeu().getJoueurs().add(j);
		}	
			
		this.getControleur().commencerPartie();
	}

	
}
