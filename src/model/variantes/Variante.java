package model.variantes;

import model.Carte;

/**
 * Classe qui represente une Variante qui definie les effets de toutes les
 * Cartes.
 */
public abstract class Variante {

	/**
	 * Modifie l'effet de la carte passee en parametre en fonction de sa valeur et
	 * de la variante.
	 * 
	 * @param carte
	 *            Carte qui va se voir attribuer un Effet.
	 */
	public abstract void gererVariante(Carte carte);

}
