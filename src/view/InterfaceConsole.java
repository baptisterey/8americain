package view;

import java.util.InputMismatchException;
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
		if (msg != "") {
			System.out.println(msg);
		}
	}

	@Override
	public int choixIndexCarte(Joueur joueurCourant) {
		String carteDefausse = "Carte sommet défausse : ";
		if (Jeu.getInstance().getDefausse().isEmpty()) {
			carteDefausse += "aucune!";

		} else {
			carteDefausse += Jeu.getInstance().getDefausse().get(Jeu.getInstance().getDefausse().size() - 1);
		}
		System.out.println(carteDefausse);

		System.out.println("-- MAIN DE " + joueurCourant.getPseudo() + " --");
		for (Carte carte : joueurCourant.getMain()) {
			System.out.println("(" + joueurCourant.getMain().indexOf(carte) + ")" + carte.toString());
		}
		System.out.println("=====================");
		System.out.println("(-1)Piocher une carte");
		
		return readInt("Choisir index Carte : ");
	}

	@Override
	public void initJoueurs() {
		System.out.println("---- CREATION AUTO DES JOUEURS (POUR TESTER LE RESTE) ----");
		this.getControleur().getJeu().getJoueurs().clear();
		this.getControleur().getJeu().getJoueurs().add(new Joueur("Civetdelapin"));
		this.getControleur().getJeu().getJoueurs().add(new JoueurArtificiel("AI_1", 0));
		this.getControleur().getJeu().getJoueurs().add(new JoueurArtificiel("AI_2", 1));
		this.getControleur().getJeu().getJoueursInitiation().clear();
		for (int i = 0; i < this.getControleur().getJeu().getJoueurs().size(); i++) {
			this.getControleur().getJeu().getJoueursInitiation().add(this.getControleur().getJeu().getJoueurs().get(i));
		}
		/*
		 * Scanner sc = new Scanner(System.in);
		 * System.out.println("---- CREATION DU JOUEUR ----");
		 * System.out.print("Entrer votre nom : "); String nom = sc.nextLine(); Joueur j
		 * = new Joueur(nom); this.getControleur().getJeu().getJoueurs().add(j);
		 * System.out.println("---- CREATION DES JOUEURS ARTIFICELS ----");
		 * System.out.print("Combien de joueurs artificiels ? "); int nbJoueur =
		 * sc.nextInt(); int strategie; for (int i = 1 ; i <= nbJoueur ; i++) {
		 * 
		 * System.out.print("Entrer nom joueur"+i+": ");
		 * 
		 * nom = sc.next();
		 * 
		 * System.out.print("Entrer stratÃ©gie joueur"
		 * +i+" (taper 0 pour passif, 1 pour agrÃ©ssif) :");
		 * 
		 * strategie = sc.nextInt();
		 * 
		 * j = new JoueurArtificiel(nom, strategie);
		 * 
		 * 
		 * this.getControleur().getJeu().getJoueurs().add(j); } for (int i = 0 ; i <
		 * this.getControleur().getJeu().getJoueurs().size() ; i++) {
		 * this.getControleur().getJeu().getJoueursInitiation().add(this.getControleur()
		 * .getJeu().getJoueurs().get(i)); }
		 */

		this.getControleur().commencerPartie();
	}

	@Override
	public int[] choixIndexDonner(Joueur joueurCourant) {
		int[] data = new int[2];
		System.out.println("-- CHOIX EFFET DONNER --");

		System.out.println("-- MAIN DE " + joueurCourant.getPseudo() + " --");
		for (Carte carte : joueurCourant.getMain()) {
			System.out.println("(" + joueurCourant.getMain().indexOf(carte) + ")" + carte.toString());
		}

	
		data[0] = readInt("Choisir index Carte : ");

		System.out.println("-- CHOIX DU JOUEUR A QUI DONNER LA CARTE --");
		for (int i = 0; i < Jeu.getInstance().getJoueurs().size() - 1; i++) {
			if (!joueurCourant.equals(Jeu.getInstance().getJoueurs().get(i))) {
				System.out.println("(" + i + ")" + Jeu.getInstance().getJoueurs().get(i).getPseudo());
			}
		}

		data[1] = readInt("Choisir index Joueur : ");

		return data;
	}

	@Override
	public int[] choixChangerCouleur(Joueur joueurCourant) {

		System.out.println("-- CHOIX CHANGER COULEUR --");
		for (int i = 0; i < Carte.COULEURS.length; i++) {
			System.out.println("(" + i + ")" + Carte.COULEURS[i]);
		}

		int couleur = readInt("Choisir couleur : ");

		int[] data = new int[1];
		data[0] = couleur;
		return data;
	}

	private int readInt(String msg) {
		int num = 0;
		boolean loop = true;
		
		while (loop) {
			try {
				System.out.print(msg);
				Scanner sc = new Scanner(System.in);
				num = sc.nextInt();
				loop = false;
			} catch (InputMismatchException e) {
				
			}
		}
		return num;
	}

	@Override
	public int getChoixAction(Joueur joueurCourant) {
		System.out.println("(0) Annoncer");
		System.out.println("(1) Jouer carte");
		System.out.println("=====================");
		return readInt("Choisir action : ");
	}

	@Override
	public String getAnnonce(Joueur joueurCourant) {
		System.out.println("Carte sommet défausse : "+Jeu.getInstance().getDefausse().getLast());
		System.out.println("--- MAIN DE "+joueurCourant.getPseudo()+" ---");
		for(Carte carte : joueurCourant.getMain()) {
			System.out.println(carte.toString());
		}
		System.out.println("======");
		System.out.print("Annonce : ");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

}
