package model.variantes;

import model.Carte;
import model.effets.EffetChangerCouleur;

public class Minimale extends Variante{

	public void gererVariante(Carte carte) {

		int valeur = carte.getValeur();
		switch (valeur) {
			case Carte.HUIT:
				carte.setEffet(new EffetChangerCouleur());
				carte.getEffet().setAlwaysPosable(true);
				break;
		}
		
	}
}
