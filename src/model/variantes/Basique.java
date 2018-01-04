package model.variantes;

import model.Carte;
import model.effets.EffetAttaque;
import model.effets.EffetChangerSensJeu;
import model.effets.EffetContrerChangerCouleur;
import model.effets.EffetDonner;
import model.effets.EffetRejouer;
import model.effets.EffetSauterTour;

public class Basique extends Variante {

	public void gererVariante(Carte carte) {
		int valeur = carte.getValeur();
		
		switch (valeur) {
			case Carte.CINQ:
				carte.setEffet(new EffetDonner());
				break;
				
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
				carte.setEffet(new EffetAttaque(2, true));
				break;
		}
	}
	
	public static String getStringVariante() {
		String str = "";
		
		str+="CINQ : \n \t Le joueur donne une de ses cartes à un joueur choisi.\n";	
		
		str+="SEPT : \n \t Saute le tour du joueur suivant.\n";	
		
		str+="HUIT : \n \t Contre une attaque puis le joueur change la couleur de la carte.\n";	
		
		str+="NEUF : \n \t Fait piocher une carte au joueur suivant.\n";	
		
		str+="DIX : \n \t Fait rejouer le joueur.\n";	
		
		str+="VALET : \n \t Change le sens du jeu.\n";	
		
		str+="AS : \n \t Attaque avec une valeur de 2.\n";	
		
		return str;
	};
}
