package view.interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;


import controleur.Controleur;
import view.IHM;

public class JoueurArtificiel extends IHM {
	
	private JFrame fenetre; // j'arrive pas à utiliser borderlayout sans avoir de fenetre --'
	private ImageIcon image; //pour image du personnage
	// text mis dans le nom de la fenêtre du coup
	//private JLabel texte; //pour son pseudo et son nombre de carte
	private JButton button; // pour pouvoir le selectionner (effet donner par exemple)
	private JCheckBox checkBox; // coché s'il a bien annoncé "carte" (pas besoin de préciser pour les autres annonces ?)

	public JoueurArtificiel(Controleur ctrl, String pathImage, String pseudo, int nombreCarte) {
		super(ctrl);
		this.fenetre = new JFrame(pseudo + " : "+nombreCarte+" cartes");
        this.image = new ImageIcon(pathImage);
        //this.texte = new JLabel(pseudo + " : "+nombreCarte+" cartes");
        this.button = new JButton("selectionner");
        this.checkBox = new JCheckBox("a annoncé 'Carte'");
        
        Container reservoir = fenetre.getContentPane();
        //reservoir.add(this.image, BorderLayout.CENTER); marche pas je ne sais pas pourquoi
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
