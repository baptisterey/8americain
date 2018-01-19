package model.variantes;

import model.Carte;
import model.effets.EffetChangerCouleur;

public class Minimale extends Variante {

	public void gererVariante(Carte carte) {

		int valeur = carte.getValeur();
		switch (valeur) {
		case Carte.HUIT:
			carte.setEffet(new EffetChangerCouleur());
			carte.getEffet().setAlwaysPosable(true);
			break;
		}

	}

	/**
	 * Permet de savoir qu'elles sont les cartes modifiees par la Variante.
	 * 
	 * @return Renvoie la liste des cartes modifiees par la Variante.
	 */
	public static String getStringVariante() {
		String str = "";

		str += "HUIT : \n \t Le Joueur choisi une nouvelle couleur.\n";

		return str;
	};
}
