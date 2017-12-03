package model;

public interface Strategie {
  
    Carte choisirCarteStrategie(Joueur joueurCourant);
    
    int[] choisirDataDonner(Joueur joueurCourant);
    
    int[] choisirDataChangerCouleur(Joueur joueurCourant);
    
    String choisirAnnonce(Joueur joueurCourant);
}
