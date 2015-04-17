import java.awt.*;

import javax.swing.*;


public class Afficheur 
{
	private JFrame fenetre;
	
	/**
	 * constructeur d'un afficheur de graphique
	 * @param data tableau de donnees a representer dans le graphique
	 */
	public Afficheur(Tableau donnees, boolean fitOnX,String title,String labelY)
	{
		fenetre = new JFrame(title);
		fenetre.setSize(700, 700);
		
		Graphique pane = new Graphique(donnees, 700,700, fitOnX, labelY);
		fenetre.setContentPane(pane);
		
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * montre le graphique de T(n) en ms pour les donnees fournies dans le constructeur
	 */
	public void show()
	{
		fenetre.setVisible(true);
	}
}
