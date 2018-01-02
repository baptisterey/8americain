package view.interfaceConsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import controleur.Controleur;
import model.Message;
import view.IHM;
import model.Carte;

/**
 * Classe représentant une Interface en ligne de commande, elle hérite de IHM et
 * implémente donc Observer.
 */
public class InterfaceConsole extends IHM {

	/**
	 * Le thread associé à la classe. Avant de créer un nouveau Thread, on assure
	 * que ce dernier soit bien terminé afin d'éviter une incohérence dans les
	 * Entrées fournies par l'utilisateur.
	 */
	private static Thread th;

	public InterfaceConsole(Controleur ctrl) {
		super(ctrl);
	}

	/**
	 * Utilise la méthode lireChaine(String msg), et tente de convertir la chaîne de
	 * caractères entrée par l'utilisateur, répéte jusqu'a succès ou que lireChaine
	 * ait renvoyé null (thread interrompu).
	 * 
	 * @param msg
	 *            Le message à afficher avant de demander un Entier.
	 * @return L'Entier entré par l'utilisateur ou null si le Thread se fait
	 *         interrompre.
	 */
	protected Integer lireInteger(String msg) {

		String numString = lireChaine(msg);
		if (numString != null) {
			boolean conversionok;
			do {
				conversionok = true;
				try {
					return Integer.parseInt(numString);
				} catch (NumberFormatException e) {
					numString = lireChaine(msg);
					if (numString == null) {
						return null;
					}
					conversionok = false;
				}
			} while (!conversionok);
		}

		return null;
	}

	/**
	 * Envoie la chaîne de caractères entrée par l'utilisateur. L'appel de la
	 * méthode interrupt sur l'attribut th interrompt termine cette méthode en la
	 * faisant retourner null.
	 * 
	 * @param msg
	 *            Le message à afficher avant de demander une Chaîne.
	 * @return La chaîne de caractères entrée par l'utilisateur ou null si le Thread
	 *         se fait interrompre.
	 */
	protected String lireChaine(String msg) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(msg);
		while (!th.isInterrupted()) {
			try {
				if (stdin.ready()) {
					String resultat = stdin.readLine();
					return resultat;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	/**
	 * Implementation de l'interface Observer. L'objet passé est la classe Message,
	 * on réalise alors la bonne action en fonction de son type. Lorsqu'une Action
	 * de la part de l'utilisateur est requise, on créer la Classe correspondante
	 * (Voir suite du package) et on l'éxecute dans le Thread th grâce à la méthode
	 * commencerThread().
	 */
	public void update(Observable jeu, Object msg) {
		if (msg instanceof Message) {
			switch (((Message) msg).getType()) {
			case effetAttaque:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " oblige "
						+ ((Message) msg).getJoueurVictime().getPseudo() + " à piocher "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s)!");
				break;

			case effetClassique:
				break;

			case effetRejouer:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " rejoue!");
				break;

			case effetSauterTour:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " empéche "
						+ ((Message) msg).getJoueurVictime().getPseudo() + " de jouer!");
				break;

			case effetContrerChangerCouleur:
				System.out.println(
						((Message) msg).getJoueurCourant().getPseudo() + " a arreté une attaque et a choisi la couleur "
								+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetChangerCouleur:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " a choisi la couleur "
						+ Carte.COULEURS[((Message) msg).getNouvelleCouleur()] + "!");
				break;

			case effetDonner:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " ajoute un(e) "
						+ ((Message) msg).getCarteADonner().toString() + " dans la main de "
						+ ((Message) msg).getJoueurVictime().getPseudo());
				break;

			case effetModeAttaque:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " ajoute "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s) au tas d'attaque!");
				break;

			case effetChangerSensJeu:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " inverse le sens de jeu!");
				break;

			case nePeutPasJouer:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " ne peut pas jouer!");
				break;

			case joueurAnnonce:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " dit : \""
						+ ((Message) msg).getAnnonce() + "\"!");
				break;

			case annonceContreCarteReussi:
				System.out.println(((Message) msg).getJoueurVictime().getPseudo()
						+ " pioche deux cartes grâce à un Contre Carte réussi de "
						+ ((Message) msg).getJoueurCourant().getPseudo() + "!");
				break;

			case annonceContreCarteEchoue:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Contre Carte sans aucune raison!");
				break;

			case annonceInconnue:
				System.out.println("Rien ne se passe!");
				break;

			case piocherCarte:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " pioche "
						+ ((Message) msg).getNbCartesAttaque() + " carte(s)!");
				break;

			case choixChangerCouleur:
				commencerThread(new InterfaceConsoleEffetChangerCouleur(getControleur(),
						((Message) msg).getJoueurCourant(), ((Message) msg).getEffetAvecInputEnCours()));
				break;

			case choixDonnerCarte:
				commencerThread(new InterfaceConsoleEffetDonner(getControleur(), ((Message) msg).getJoueurCourant(),
						((Message) msg).getEffetAvecInputEnCours()));
				break;

			case cartePosee:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " pose un(e) "
						+ ((Message) msg).getCarteADonner().toString() + "!");
				break;

			case annonceCarteTropTot:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo()
						+ " pioche deux cartes pour avoir annoncer un Carte trop tôt!");
				break;

			case joueurAFiniManche:
				System.out.println(
						((Message) msg).getJoueurCourant().getPseudo() + " vient de poser sa dernière carte! Bravo!");
				break;

			case afficherTour:
				System.out.println("------- TOUR DE " + ((Message) msg).getJoueurCourant().getPseudo() + " -------");
				break;

			case tourJoueurHumain:
				commencerThread(new InterfaceConsoleJouerTour(getControleur(), ((Message) msg).getJoueurCourant()));
				break;

			case initJoueurs:
				commencerThread(new InterfaceConsoleInitJoueurs(getControleur()));
				break;

			case finTourJoueurHumain:
				arreterThread();
				break;

			case annonceCarte:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + " a bien annoncé Carte!");
				break;

			case nouvelleManche:
				System.out.println("---------- MANCHE N°" + ((Message) msg).getNumeroManche() + " ----------");
				break;

			case finPartie:
				System.out.println(((Message) msg).getJoueurCourant().getPseudo() + "gagne la partie!");
				break;

			case initPartie:
				commencerThread(new InterfaceConsoleInitPartie(getControleur()));
				break;
				
			case debutPartie:	
				break;
			default:
				System.out.println("MESSAGE NON PRIS EN CHARGE : " + ((Message) msg).getType().toString());
				break;
			}
		}

	}

	/**
	 * Interrompt le thread en cours et execute le Runnable passé en paramêtre dans
	 * le Thread th.
	 * 
	 * @param runnable
	 *            L'interface à executer.
	 */
	private void commencerThread(Runnable runnable) {
		arreterThread();
		th = new Thread(runnable);
		th.start();
	}

	/**
	 * Si th n'est pas null, l'interrompt et attend la fin de son execution.
	 */
	private void arreterThread() {
		if (th != null) {
			th.interrupt();
			try {
				th.join();
			} catch (InterruptedException e) {
			}
		}
	}

}
