package view.interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import model.Joueur;
import model.JoueurArtificiel;

/**
 * Interface graphique pour l'initialisation des joueurs d'une nouvelle partie.
 * Permet de choisir le pseudo du joueur, et d'ajouter des bots en choisissant
 * leur pseudo et leur stratégie attitré.
 */

public class InterfaceGraphiqueInitJoueurs extends InterfaceGraphique {
	/**
	 * Constructeur. Appel la méthode {@link}{@link #menuInitJoueurs()}
	 * @param ctrl
	 */
	public InterfaceGraphiqueInitJoueurs(Controleur ctrl) {
		super(ctrl);
		menuInitJoueurs();
	}

	private JTextField jTfNouvJoueur;
	private JFrame menuInitJFrame;
	private JList jListNewJoueur;
	private JButton jButCommencerJeu;
	private LinkedList<Joueur> joueursInit = new LinkedList<Joueur>();
	private ArrayList<String> dataJList = new ArrayList<>();
	private JTextField jTfJoueurHumain;

	public JFrame getMenuInitJFrame() {
		return menuInitJFrame;
	}
	
	/**
	 * Initialise le contenu de la fenêtre et permet d'initier les joueurs.
	 */
	
	public void menuInitJoueurs() {
		joueursInit.clear();

		menuInitJFrame = new JFrame();
		menuInitJFrame.setTitle("8 Américain");
		menuInitJFrame.setSize(400, 600);
		menuInitJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuInitJFrame.setResizable(false);
		menuInitJFrame.setLocation(30, 30);

		JPanel jPanelFond = new JPanel();
		menuInitJFrame.add(jPanelFond);

		jPanelFond.setLayout(new BorderLayout());

		JLabel titre = new JLabel("Création des Joueurs");
		titre.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		titre.setHorizontalAlignment(JLabel.CENTER);
		jPanelFond.add(titre, BorderLayout.NORTH);

		JPanel jPanelCentreChampFond = new JPanel();
		jPanelFond.add(jPanelCentreChampFond, BorderLayout.CENTER);

		jPanelCentreChampFond.setLayout(new BorderLayout());
		ImageIcon image = new ImageIcon("images/icon.png");
		JLabel label = new JLabel("", image, JLabel.CENTER);

		jPanelCentreChampFond.add(label, BorderLayout.NORTH);
		JPanel jPanelChamp = new JPanel();
		jPanelChamp.setLayout(new BorderLayout());

		jPanelCentreChampFond.add(jPanelChamp, BorderLayout.CENTER);

		JPanel jpanelchampNordJoueurHumain = new JPanel();
		jpanelchampNordJoueurHumain.setLayout(new BorderLayout());
		jPanelChamp.add(jpanelchampNordJoueurHumain, BorderLayout.NORTH);
		jpanelchampNordJoueurHumain.add(new JLabel("  Entrer votre Nom : ", null, JLabel.LEFT), BorderLayout.NORTH);

		jTfJoueurHumain = new JTextField(10);

		jpanelchampNordJoueurHumain.add(jTfJoueurHumain, BorderLayout.CENTER);

		JPanel jpanelchampJoueursArtificiels = new JPanel();
		jpanelchampJoueursArtificiels.setLayout(new BorderLayout());
		jPanelChamp.add(jpanelchampJoueursArtificiels, BorderLayout.CENTER);

		JPanel jpanelchampNordJoueursArtificiels = new JPanel();
		jpanelchampJoueursArtificiels.add(jpanelchampNordJoueursArtificiels, BorderLayout.NORTH);

		jpanelchampNordJoueursArtificiels.setLayout(new BorderLayout());
		jpanelchampNordJoueursArtificiels.add(
				new JLabel("  Entrer le nom d'un nouveau Joueur Artificiel :", null, JLabel.LEFT), BorderLayout.NORTH);

		jTfNouvJoueur = new JTextField(10);

		jpanelchampNordJoueursArtificiels.add(jTfNouvJoueur, BorderLayout.CENTER);

		String[] strategies = { "Passif", "Agressif" };
		JComboBox choixStrategie = new JComboBox(strategies);

		jpanelchampNordJoueursArtificiels.add(choixStrategie, BorderLayout.EAST);

		jListNewJoueur = new JList();

		jpanelchampNordJoueursArtificiels.add(jListNewJoueur, BorderLayout.SOUTH);

		JPanel jPanelSudFond = new JPanel(); // fond avec les champs
		jPanelFond.add(jPanelSudFond, BorderLayout.SOUTH);
		jPanelSudFond.setLayout(new GridLayout(3, 1));

		JButton jButAjouter = new JButton("Ajouter le Joueur Artificiel");
		jButCommencerJeu = new JButton("Commencer le jeu");
		JButton jButExit = new JButton("Quitter");

		jPanelSudFond.add(jButAjouter);
		jPanelSudFond.add(jButCommencerJeu);
		jPanelSudFond.add(jButExit);

		menuInitJFrame.setVisible(true);
		menuInitJFrame.getRootPane().setDefaultButton(jButAjouter);

		jButAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (jTfNouvJoueur.getText().equals("")) {
					JOptionPane.showMessageDialog(menuInitJFrame,
							"Merci d'entrer un nom pour le nouveau Joueur Artificiel!");

				} else if (jTfNouvJoueur.getText().length() <= 10) {
					if (joueursInit.size() < 6) {
						switch (choixStrategie.getSelectedIndex()) {
						case 0:
							joueursInit.add(new JoueurArtificiel(jTfNouvJoueur.getText(), JoueurArtificiel.PASSIF));
							break;
						case 1:
							joueursInit.add(new JoueurArtificiel(jTfNouvJoueur.getText(), JoueurArtificiel.AGRESSIF));
							break;

						default:
							joueursInit.add(new JoueurArtificiel(jTfNouvJoueur.getText(), JoueurArtificiel.PASSIF));
							break;
						}

						dataJList.add(jTfNouvJoueur.getText() + " : " + choixStrategie.getSelectedItem().toString());
						jListNewJoueur.setListData(dataJList.toArray());
					} else {
						JOptionPane.showMessageDialog(menuInitJFrame, "Impossible d'ajouter plus de six joueurs ");
					}

					jTfNouvJoueur.setText(null);

				} else {
					JOptionPane.showMessageDialog(menuInitJFrame, "Merci d'entrer un nom inférieur à  10 caractéres ");
				}

			}

		});

		jButCommencerJeu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (joueursInit.size() > 0 && !jTfJoueurHumain.getText().equals("")) {
					joueursInit.add(new Joueur(jTfJoueurHumain.getText()));

					menuInitJFrame.setVisible(false);
					menuInitJFrame.dispose();

					// ON COMMENCE LA PARTIE
					getControleur().getJeu().setJoueursInitiation(joueursInit);
					getControleur().getJeu().commencerPartie();

				} else {
					JOptionPane.showMessageDialog(menuInitJFrame,
							"Merci de choisir votre nom et de créer au moins un Joueur Artificiel! ");
				}
			}
		});

		jButExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
