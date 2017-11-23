package model;

public interface Strategie {
  
    Carte choisirCarteStrategie(Joueur joueurCourant);
    
    int[] choisirDataDonner(Joueur joueurCourant);
}
