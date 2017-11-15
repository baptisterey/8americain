package view;

import java.util.Observable;
import java.util.Scanner;

import controleur.Controleur;
import model.JoueurArtificiel;

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
	public int choixIndexCarte() {
		// TODO Auto-generated method stub
		return 0;
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
			
			nom = sc.nextLine();
			
			System.out.print("Entrer stratégie joueur"+i+" (taper 0 pour passif, 1 pour agréssif) :");
			
			strategie = sc.nextInt();
			
			j = new JoueurArtificiel(nom, strategie);
			
			
			this.getControleur().getJeu().getJoueurs().add(j);
		}	
			
		
	}

	
}
