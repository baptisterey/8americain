package model;

public interface Strategie {
  
    Carte choisirCarteStrategie(Joueur joueurCourant);
    
    Integer[] choisirDataDonner(Joueur joueurCourant);
    
    Integer[] choisirDataChangerCouleur(Joueur joueurCourant);
    
    String choisirAnnonce(Joueur joueurCourant);
}
