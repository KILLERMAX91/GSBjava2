package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controler.Controler;

public class Vue_aPropos extends JFrame implements ActionListener {
	
	private Controler leControleur;
	
			/*Composant IHM*/
	
	/*Panels*/

	private JPanel panel = new JPanel(); //Panel General
	
	
			/*Labels*/
	
	private JLabel image = new JLabel(new ImageIcon(getClass().getResource("/image/logo.jpg")));
	private JLabel txt = new JLabel("Ce programme a ete conçus par : ");
	private JLabel auteur1 = new JLabel("M. BARATA Mathieu");
	private JLabel auteur2 = new JLabel("M. GREY Steven");
	private JLabel auteur3 = new JLabel("M. LEVEAU Guillaume");
	
	
			/*JButton*/
	
	private JButton btnMenu = new JButton("Menu");
	
			/*Constructeur*/
	
	public Vue_aPropos(Controler unControle)  {
		
		this.leControleur = unControle;
		
		//Police de l'ecriture sur les boutons
		
		Font f = new Font("Arial", Font.BOLD, 13); 
		Font e = new Font("Arial", Font.BOLD, 20); 
		
		this.btnMenu.setFont(f); 
		this.txt.setFont(e);
		this.auteur1.setFont(e);
		this.auteur2.setFont(e);
		this.auteur3.setFont(e);
		
			//Ecoute des boutons
		
		this.btnMenu.addActionListener(this);
				
				
			//Positionnement des Elements
				
		this.panel.setLayout(null);
				
		this.btnMenu.setBounds(80, 420, 100, 35);	
				
		this.image.setBounds(25, 10, 250, 200);
		
		this.txt.setBounds(400,25,400,200);
		this.auteur1.setBounds(450,100,300,200);
		this.auteur2.setBounds(475,150,300,200);
		this.auteur3.setBounds(450,200,300,200);
		
		//Elements mis dans le panel
		
		this.panel.add(image);
		this.panel.add(btnMenu);
		this.panel.add(txt);
		this.panel.add(auteur1);
		this.panel.add(auteur2);
		this.panel.add(auteur3);
		
		this.panel.setBackground(Color.white);
		
		this.setContentPane(panel); 
		
		
		/*Configuration de la Fenetre*/
	
		this.setTitle("GSB-A Propos"); 
		this.setLocation(240,120);	
		this.setSize(1024,550);
		this.setResizable(false);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setAlwaysOnTop(false); 
		this.setVisible(true); 
	}

	
	public void actionPerformed(ActionEvent e) {
		
		if((JButton)e.getSource() == this.btnMenu){
			this.dispose();
			this.leControleur.getCVPersonne().retourMenu();
		}
		
	}
	
}
