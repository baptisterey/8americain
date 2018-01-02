package view.interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Controleur;
import model.Joueur;
import view.IHM;

public class JoueurArtificiel extends IHM {
	
	private JFrame fenetre; // j'arrive pas à utiliser borderlayout sans avoir de fenetre --'
	private ImageIcon image; //pour image du personnage
	private JLabel texte; //pour son pseudo et son nombre de carte
	private JButton button; // pour pouvoir le selectionner (effet donner par exemple)
	private JCheckBox checkBox; // coché s'il a bien annoncé "carte" (pas besoin de préciser pour les autres annonces ?)

	public JoueurArtificiel(Controleur ctrl, String pathImage, Joueur j) {
		super(ctrl);
		
		initialize(pathImage, j);
		
		
        
        
		// TODO Auto-generated constructor stub
	}
	
	private void initialize(String pathImage, Joueur j ) {
		this.fenetre = new JFrame();//faut il donner une taille ? le rendre visible ?
	
		JPanel panel = new JPanel(new BorderLayout());
		Container reservoir = fenetre.getContentPane();
		reservoir.add(panel);
		
		//Center
        this.image = new ImageIcon(pathImage);
        panel.add(this.image);//marche pas parce qu'il faut un component............
        //
        
        String pseudo = j.getPseudo();
        int nombreCarte = j.getMain().size();
        
        //North
        this.texte = new JLabel(pseudo + " : "+nombreCarte+" cartes");
        panel.add(texte, BorderLayout.NORTH);
        //
        
        //South
        this.button = new JButton("selectionner");
        checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        panel.add(button, BorderLayout.SOUTH);
        //
        
        //West
        this.checkBox = new JCheckBox("a annoncé 'Carte'");
        panel.add(checkBox, BorderLayout.WEST);
       
        //
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
