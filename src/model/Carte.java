package model;

public class Carte {
	
	public final static int PIC = 0;
    public final static int COEUR = 1;
    public final static int CARREAU = 2;
    public final static int TREFLE = 3;

    public final static int AS = 0;
    public final static int DEUX = 1;
    public final static int TROIS = 2;
    public final static int QUATRE = 3;
    public final static int CINQ = 4;
    public final static int SIX = 5;
    public final static int SEPT = 6;
    public final static int HUIT = 7;
    public final static int NEUF = 8;
    public final static int DIX = 9;
    public final static int VALET = 10;
    public final static int DAME = 11;
    public final static int ROI = 12;

    public final static String[] COULEURS = {"Pic", "Coeur", "Carreau", "Trefle"};
    public final static String[] VALEURS = {"As","Deux","Trois","Quatre","Cinq","Six","Sept", "Huit", "Neuf", "Dix", "Valet", "Dame", "Roi", "As"};

    private int couleur;
    private int valeur;

    private Effet effet = new EffetClassique();
    
    
    public Carte (int valeur, int couleur) {
		this.setCouleur(couleur);
		this.setValeur(valeur);
    }

    public Carte (String valeur, String couleur) {

		int i = 0;
		int c = 0, v = 0;
		boolean trouve = false;
	
		while (i < Carte.COULEURS.length && trouve == false) {
		    if (couleur.equals(Carte.COULEURS[i])) {
			trouve = true;
			c = i;
		    } else {
			i++;
		    }
		}
	
		i = 0;
		trouve = false;
	
		while (i < Carte.VALEURS.length && trouve == false) {
		    if (valeur.equals(Carte.VALEURS[i])) {
			trouve = true;
			v = i;
		    } else {
			i++;
		    }
		}
	
		this.setCouleur(c);
		this.setValeur(v);
    }

    public int getCouleur() {
    	return couleur;
    }

    public void setCouleur(int couleur) {
		if (couleur >= Carte.PIC && couleur <= Carte.TREFLE) {
		    this.couleur = couleur;
		}
    }

    public int getValeur() {
    	return valeur;
    }

    public void setValeur(int valeur) {
		if (valeur >= Carte.AS && valeur <= Carte.ROI) {
		    this.valeur = valeur;
		}
    }

    public Effet getEffet() {
		return effet;
	}

	public void setEffet(Effet effet) {
		this.effet = effet;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Carte.VALEURS[this.valeur]);
		sb.append(" de ");
		sb.append(Carte.COULEURS[this.couleur]);
		return sb.toString();
    }
    
}
