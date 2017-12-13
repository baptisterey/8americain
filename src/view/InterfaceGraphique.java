package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import controleur.Controleur;
import model.Message;

public class InterfaceGraphique extends IHM {
	
	private JTextArea textArea = new JTextArea(10, 5);
	
	public InterfaceGraphique(Controleur ctrl) {
		super(ctrl);
		
		JFrame fenetre = new JFrame();
		fenetre.setSize(500,850);
		
		fenetre.add(textArea);
		
		
		JButton bouton = new JButton("OK");
		bouton.setSize(10, 10);
        fenetre.add(bouton);
		
        bouton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
            	System.out.println("BOUTON APPUYEE");
            	getControleur().getJeu().annoncer(getControleur().getJeu().getJoueurs().getFirst(), "Contre Carte");
                        
            }
        }
        );
        fenetre.setVisible(true);
	}

	@Override
	public void update(Observable jeu, Object msg) {
		if(msg instanceof Message) {
			afficherConsole(((Message) msg).getType().toString());
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initJoueurs() {
		// TODO Auto-generated method stub
		
	}
	
	public void afficherConsole(String str) {
        textArea.append(str + "\n");
    }

}
