package model;

import model.effets.EffetAttaque;
import model.effets.EffetContrerChangerCouleur;

public class Agressif implements Strategie {

	@Override
	public Carte choisirCarteStrategie(Joueur joueurCourant) {

		Carte carteChoisie = null;
		for (int i = 0 ; i <= joueurCourant.getMain().size()-1 ; i++) {
			if (Jeu.getInstance().isCartePosable(joueurCourant.getMain().get(i))) {	
				if (carteChoisie == null || !(carteChoisie.getEffet() instanceof EffetAttaque)) {
					carteChoisie = joueurCourant.getMain().get(i);
				}	
			}
		}
		
		return carteChoisie;
	}
	
	
	@Override
	public Integer[] choisirDataDonner(Joueur joueurCourant) {
		int indexCarteChoisie = 0;
		for (int i = 1 ; i < joueurCourant.getMain().size() ; i++) {
			if (joueurCourant.getMain().get(indexCarteChoisie).getEffet() instanceof EffetAttaque || joueurCourant.getMain().get(indexCarteChoisie).getEffet() instanceof EffetContrerChangerCouleur) {
				indexCarteChoisie = i;
			}
		}
		
		int indexJoueurChoisi = 0;
		for (int j = 1 ; j < Jeu.getInstance().getJoueurs().size() - 1 ; j++) { //le joueur courant est en derni�re position de cette linkedList
			if (Jeu.getInstance().getJoueurs().get(indexJoueurChoisi).getMain().size() > Jeu.getInstance().getJoueurs().get(j).getMain().size()) {
				indexJoueurChoisi = j;
			}
		}
		
		Integer [] data = new Integer [2];
		data[0] = indexCarteChoisie;
		data[1] = indexJoueurChoisi;
		return data;
	}
	
	public Integer[] choisirDataChangerCouleur(Joueur joueurCourant) {
		int pic = 0;
		int coeur = 0;
		int carreau = 0;
		int trefle = 0;
		
		for (int i = 0 ; i < joueurCourant.getMain().size() ; i++) {
			if (joueurCourant.getMain().get(i).getCouleur() == Carte.PIC) {
				pic++;
			} else if (joueurCourant.getMain().get(i).getCouleur() == Carte.COEUR) {
				coeur++;
			}else if (joueurCourant.getMain().get(i).getCouleur() == Carte.CARREAU) {
				carreau++;
			} else if (joueurCourant.getMain().get(i).getCouleur() == Carte.TREFLE) {
				trefle++;
			}
		}
		
		int max = Math.max(pic, coeur);
		max = Math.max(max, carreau);
		max = Math.max(max, trefle);
		
		Integer [] data = new Integer [1];

		if (max == pic) {
			data[0] = Carte.PIC;
		} else if (max == coeur) {
			data[0] = Carte.COEUR;
		} else if (max == carreau) {
			data[0] = Carte.CARREAU;
		}else if (max == trefle) {
			data[0] = Carte.TREFLE;
		}
		
		return data;
	}


	@Override
	public String choisirAnnonce(Joueur joueurCourant) {
		String annonce = null;
		
		if(joueurCourant.getMain().size() == 2 && !joueurCourant.isPeutFinir()) {
			for(Carte carte : joueurCourant.getMain()) {
				if(Jeu.getInstance().isCartePosable(carte)) {
					annonce = Jeu.ANNONCE_CARTE;
				}
			}	
		}
		
		for(Joueur joueur : Jeu.getInstance().getJoueurs()) {
			
			if(joueur.getMain().size()==1 && !joueur.isPeutFinir() && joueur != joueurCourant) {
				annonce = Jeu.ANNONCE_CONTRE_CARTE;
			}	
		}
		
		
		return annonce;
	}


 
}
