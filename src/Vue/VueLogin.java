package Vue;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Controler.Controler;


public class VueLogin extends JFrame implements ActionListener{

	
	private Controler unControler;
	
	/*Composant IHM*/
	
		/*Panels*/
	
	private JPanel panel = new JPanel ( ) ;
	private JPanel panelInfoLogin = new JPanel();
	
		/*Labels*/
	
	private JLabel lblNom = new JLabel("Bienvenue sur GSB-Frais");;
	private JLabel labelLogin = new JLabel("Login : ");
	private JLabel labelMDP = new JLabel("Mot de passe : ");
	private JLabel lblerror = new JLabel("");
	private JLabel image = new JLabel(new ImageIcon(getClass().getResource("/image/logo.jpg")));

		/*JTextFields*/
	
	private JTextField login = new JTextField("");
	private JPasswordField mdp = new JPasswordField("");
	
	
		/*Boutons -> JButton*/
	
	private JButton btnValide = new JButton("Valider");
	private JButton btnQuitter = new JButton("Quitter");
		
	/*Constructeur*/
	
	public VueLogin(Controler v) {
		unControler = v;
		/*Gestion de la Police et des couleurs*/
		
		Font police = new Font("Arial", Font.BOLD, 14);
		Font policeTitre = new Font("Arial", Font.BOLD, 20);
		
		lblNom.setFont(policeTitre);
		login.setFont(police);
		mdp.setFont(police);
		mdp.setForeground(Color.BLUE);
		login.setForeground(Color.BLUE);
		
		
		//Ecoute des Boutons
		
		btnValide.addActionListener(this);
		btnQuitter.addActionListener(this);
		
		
		//Positionnement des Elements
		
		this.panel.setLayout(null);
					    
		this.lblNom.setBounds(425, 25, 400, 50);
		this.panelInfoLogin.setLayout(new GridLayout(1, 1));
		this.panelInfoLogin.setBackground(Color.white);
		this.panelInfoLogin.add(lblerror);
		
		//this.lblerror.setBounds(435, 100, 400, 50);
		
		this.btnValide.setBounds(550, 425, 200, 35);
		this.panelInfoLogin.setBounds(400, 80, 650, 50);
		this.btnQuitter.setBounds(320, 425, 200, 35);
		this.labelLogin.setBounds(375, 200, 200, 35);
		this.login.setBounds(525, 203, 150, 25);
		this.labelMDP.setBounds(375, 250, 200, 35);
		this.mdp.setBounds(525, 253, 150, 25);
		this.image.setBounds(50, 25, 250, 200);
		
		/*Panel*/
		
		this.panel.setBackground(Color.white);
		this.panel.add(lblNom);
		
		this.panel.add(this.panelInfoLogin);
		
		this.panel.add(btnValide);
		this.panel.add(btnQuitter);
		this.panel.add(labelLogin);
		this.panel.add(login);
		this.panel.add(labelMDP);
		this.panel.add(mdp);
		this.panel.add(image);
		
		//Le Panel est mis dans le getContentPane()
		
		this.setContentPane(panel); 
		
		/*Configuration de la Fenetre*/
		
		this.setTitle("GSB-Connexion"); 
		this.setLocation(240,120);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024,550);
		this.setResizable(false);  
		this.setAlwaysOnTop(false); 
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if ((JButton)e.getSource() == btnQuitter) {
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.dispose();
			
		}else{
			if (unControler.getCVPersonne().connectComptableBDD(login.getText(), mdp.getText()) == true) {
				
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.dispose();
				this.unControler.getCVPersonne().ficheComptable();
			}else{ //affiche message d'erreur
				this.panelInfoLogin.removeAll();
				this.lblerror = new JLabel("Une erreur d'authentification est survenu. Le login et/ou le mot de passe est incorrect.");
				this.lblerror.setForeground(new Color(255,0,0));
				
				this.panelInfoLogin.add(lblerror);
				this.panelInfoLogin.repaint();
				printAll(this.panelInfoLogin.getGraphics());
			
			}
		}
	}
}