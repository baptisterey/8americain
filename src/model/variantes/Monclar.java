package model.variantes;

import model.Carte;
import model.effets.EffetAttaque;
import model.effets.EffetChangerSensJeu;
import model.effets.EffetContrerChangerCouleur;
import model.effets.EffetRejouer;
import model.effets.EffetSauterTour;

public class Monclar extends Variante {

	public void gererVariante(Carte carte) {

		int valeur = carte.getValeur();
		switch (valeur) {
		case Carte.SEPT:
			carte.setEffet(new EffetSauterTour());
			break;

		case Carte.HUIT:
			carte.setEffet(new EffetContrerChangerCouleur());
			break;

		case Carte.NEUF:
			carte.setEffet(new EffetAttaque(1, false));
			break;

		case Carte.DIX:
			carte.setEffet(new EffetRejouer());
			break;

		case Carte.VALET:
			carte.setEffet(new EffetChangerSensJeu());
			break;

		case Carte.AS:
			carte.setEffet(new EffetAttaque(3, true));
			break;
		}
	}

	/**
	 * Permet de savoir qu'elles sont les cartes modifiées par la Variante.
	 * 
	 * @return Renvoie la liste des cartes modifiées par la Variante.
	 */
	public static String getStringVariante() {
		String str = "";

		str += "SEPT : \n \t Saute le tour du joueur suivant.\n";

		str += "HUIT : \n \t Contre une attaque puis le joueur change la couleur de la carte.\n";

		str += "NEUF : \n \t Saute le tour du joueur suivant.\n";

		str += "DIX : \n \t Fait rejouer le joueur.\n";

		str += "VALET : \n \t Change le sens du jeu.\n";

		str += "AS : \n \t Attaque avec une valeur de 3.\n";

		return str;
	};

}
