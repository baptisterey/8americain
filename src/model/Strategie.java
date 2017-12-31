package model;

/**
 * Implèmentation du pattern Stratégie. Utilisé ici pour créer deux style de
 * jeu, le style Agressif ou Passif. Cette classe contient les méthodes
 * utilisées par les JoueurArtificiel lorsqu'un choix doit être fait.
 */
public interface Strategie {

	/**
	 * Retourne la carte qui va être jouer.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return La Carte choisie.
	 */
	public Carte choisirCarteStrategie(Joueur joueurCourant);

	/**
	 * Retourne le choix du joueur et de la carte à donner.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return Un tableau d'entier représentant l'index du joueur à qui donner la
	 *         carte et l'index de la carte à donner.
	 */
	public Integer[] choisirDataDonner(Joueur joueurCourant);

	/**
	 * Retourne la nouvelle couleur choisie.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return Un tableau d'entier représentant la nouvelle couleur choisie.
	 */
	public Integer[] choisirDataChangerCouleur(Joueur joueurCourant);

	/**
	 * Retourne une annonce, si ne veut plus faire d'annonce, renvoie null.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return L'annonce choisi.
	 */
	public String choisirAnnonce(Joueur joueurCourant);
}
