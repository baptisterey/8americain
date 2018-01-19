package model;

/**
 * Implementation du pattern Strategie. Utilise ici pour creer deux style de
 * jeu, le style Agressif ou Passif. Cette classe contient les methodes
 * utilisees par les JoueurArtificiel lorsqu'un choix doit etre fait.
 */
public interface Strategie {

	/**
	 * Retourne la carte qui va etre jouer.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return La Carte choisie.
	 */
	public Carte choisirCarteStrategie(Joueur joueurCourant);

	/**
	 * Retourne le choix du joueur et de la carte e donner.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return Un tableau d'entier representant l'index du joueur e qui donner la
	 *         carte et l'index de la carte e donner.
	 */
	public Integer[] choisirDataDonner(Joueur joueurCourant);

	/**
	 * Retourne la nouvelle couleur choisie.
	 * 
	 * @param joueurCourant
	 *            Le joueur qui choisi.
	 * @return Un tableau d'entier representant la nouvelle couleur choisie.
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
