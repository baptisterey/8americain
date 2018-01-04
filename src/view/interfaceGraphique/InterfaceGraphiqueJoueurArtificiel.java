package view.interfaceGraphique;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Joueur;

public class InterfaceGraphiqueJoueurArtificiel extends JPanel {
	
	private JLabel image; //pour image du personnage
	private JLabel texte; //pour son pseudo et son nombre de carte
	private JCheckBox checkBox; // coché s'il a bien annoncé "carte"

	public InterfaceGraphiqueJoueurArtificiel(Joueur j) {
		initialize(j);
	}
	
	private void initialize(Joueur j ) {
	
		
		//Center
		this.image = new JLabel(new ImageIcon("./bot.png"));
        this.add(this.image);
        //
        
        
        String pseudo = j.getPseudo();
        int nombreCarte = j.getMain().size();
        
        //North
        this.texte = new JLabel(pseudo + " : "+nombreCarte+" cartes");
        this.add(texte, BorderLayout.NORTH);
        //
        
        //South
        
        //
        
        //West
        this.checkBox = new JCheckBox("a annoncé 'Carte'");
        this.checkBox.setEnabled(false);
        this.checkBox.setSelected(j.isPeutFinir());
        this.add(checkBox, BorderLayout.WEST);
        //
        this.setVisible(true);
	}
	
}
