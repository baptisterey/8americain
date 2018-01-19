package view.interfaceGraphique;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Joueur;

/**
 * Interface graphique qui resume l'ensemble des donnees d'un joueur artificiel
 * necessaire pour joueur la partie.
 */

public class InterfaceGraphiqueJoueurArtificiel extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Coche si le joueur artificiel e annonce "Carte !".
	 */
	private javax.swing.JCheckBox jCheckBoxAnnonceCarte;
	/**
	 * Affiche le nombre de cartes dans la main du joueur.
	 */
	private javax.swing.JLabel jLabelCartes;
	/**
	 * Affiche le nom du joueur artificiel.
	 */
	private javax.swing.JLabel jLabelNomBot;
	/**
	 * Affiche le score du joueur artificiel.
	 */
	private javax.swing.JLabel jLabelScore;
	
	/**
	 * Constructeur. Appel la methode {@link}{@link #initialize(Joueur)}.
	 * @param j
	 */
	public InterfaceGraphiqueJoueurArtificiel(Joueur j) {
		initialize(j);
	}

	/**
	 * Initialise le contenun de la fenetre.
	 * @param j
	 */
	private void initialize(Joueur j) {

		jLabelNomBot = new javax.swing.JLabel();
		jCheckBoxAnnonceCarte = new javax.swing.JCheckBox();
		jLabelScore = new javax.swing.JLabel();
		jLabelCartes = new javax.swing.JLabel();

		this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jLabelNomBot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelNomBot.setText(j.getPseudo());

		jCheckBoxAnnonceCarte.setText("a annonce 'Carte'");
		jCheckBoxAnnonceCarte.setSelected(j.isPeutFinir());
		jCheckBoxAnnonceCarte.setEnabled(false);
		
		jLabelScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelScore.setText("Score : " + j.getScore());

		jLabelCartes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabelCartes.setText(j.getMain().size() + " Cartes");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(this);
		this.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabelNomBot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jLabelScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jLabelCartes, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(jCheckBoxAnnonceCarte, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap().addComponent(jLabelNomBot)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
						.addComponent(jLabelCartes).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabelScore).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jCheckBoxAnnonceCarte).addContainerGap()));

	}

}
