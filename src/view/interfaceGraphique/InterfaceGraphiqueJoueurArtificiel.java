package view.interfaceGraphique;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Joueur;

public class InterfaceGraphiqueJoueurArtificiel extends JPanel {
	
	private JLabel image; //pour image du personnage
	private JLabel texte; //pour son pseudo et son nombre de carte
	private JButton button; // pour pouvoir le selectionner (effet donner par exemple)
	private JCheckBox checkBox; // coché s'il a bien annoncé "carte" (pas besoin de préciser pour les autres annonces ?)

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
        this.button = new JButton("selectionner");
        checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        this.add(button, BorderLayout.SOUTH);
        //
        
        //West
        this.checkBox = new JCheckBox("a annoncé 'Carte'");
        this.add(checkBox, BorderLayout.WEST);
       
        //
	}
	
}
