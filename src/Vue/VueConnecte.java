package Vue;
/**
 * @author mbarata
 * @author sgrey
 * @author gleveau
 *  
 * **/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Controler.Controler;


public class VueConnecte extends JFrame implements ActionListener{

	//Attributs
	
	private Controler leControler;
		
	
		/*Composant IHM*/
	
			/*Panels*/

	private JPanel panel = new JPanel(); //Panel General
	
			/*Labels*/
	
			/*Boutons*/
	
	private JButton boutonValide = new JButton("Validation des Fiches de Frais"); 
	private JButton boutonSuivis = new JButton("Suivis des Fiches de Frais");
	
	private JButton aPropos = new JButton("A Propos"); 
	
	
	//deconnexion est le bouton permettant a l'utilisateur de revenir a la page de connexion
	
	private JButton boutonDeco = new JButton("Deconnexion"); 
	private JButton quitter = new JButton("Quitter");
	
	private JLabel image = new JLabel(new ImageIcon(getClass().getResource("/image/logo.jpg")));
	
	
	public VueConnecte(Controler unControle) {
		
		
		//Instanciation d'un Controleur
		
		this.leControler = unControle;
				
		//Taille Ecriture Boutons
	
		Font f = new Font("Arial", Font.BOLD, 13); 
		
		boutonValide.setFont(f); 
		boutonSuivis.setFont(f); 
		boutonDeco.setFont(f); 
		aPropos.setFont(f); 
		quitter.setFont(f); 
		
		
		//Positionnement des Elements
		
		this.panel.setLayout(null);
		
		this.boutonValide.setBounds(375, 125, 250, 35);
		this.boutonSuivis.setBounds(375, 175, 250, 35);
		this.aPropos.setBounds(375, 225, 250, 35);
		this.boutonDeco.setBounds(375, 275, 250, 35);		
		this.quitter.setBounds(375, 325, 250, 35);
		
		this.image.setBounds(25, 10, 250, 200);
		
		
		//Ecoute des boutons
		
		boutonSuivis.addActionListener(this);
		boutonValide.addActionListener(this);
	    
	    aPropos.addActionListener(this);
	    boutonDeco.addActionListener(this);
		quitter.addActionListener(this);
		
		//Elements mis dans les Panels
		
		this.panel.add(boutonValide);
		this.panel.add(boutonSuivis);
		
		this.panel.add(aPropos);
		this.panel.add(boutonDeco);
		this.panel.add(quitter);
		
		this.panel.add(image);
		this.panel.setBackground(Color.white);
		
		this.setContentPane(panel); 
		
		
		/*Configuration de la Fenetre*/
		
		this.setTitle("GSB-Menu"); 
		this.setLocation(240,120);	
		this.setSize(1024,550);
		this.setResizable(false);  
		this.setAlwaysOnTop(false); 
		this.setVisible(true); 
	}
	
		
	
	//Action du Menu
	
	public void actionPerformed(ActionEvent e) {
		
		/*Fermeture du Programme a partir du sous-menu Quitter*/
		
		if ((JButton)e.getSource() == quitter) {
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.dispose();
		}
		else {
			
			/*Deconnexion de l'utilisateur et renvoie sur la page de connexion*/
			
			if ((JButton)e.getSource() == boutonDeco) {
			
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.dispose();
				this.leControler.getCVPersonne().login();
			}
			else { /*Renvoie sur la page de suivis des differentes fiche*/
				if ((JButton)e.getSource() == boutonSuivis) {
					this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					this.dispose();
					this.leControler.getCVPersonne().ficheValide();
				}
				else {
					if ((JButton)e.getSource() == boutonValide) { /*Renvoie sur la page de validation des fiches de frais*/
						this.leControler.getCVPersonne().demarrerInfoFacture();
						this.dispose();
					}
					else {	
						if ((JButton)e.getSource() == aPropos) { /*Renvoie sur la page d'information du programme*/
							this.dispose();
							this.leControler.getCVPersonne().demarrerInfosProgramme();
						
						}
					}
				}
			}
		}
	}

}
