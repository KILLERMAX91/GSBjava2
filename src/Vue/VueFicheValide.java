package Vue;
/**
 * @author mbarata
 * @author sgrey
 * @author gleveau
 *  
 * **/

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Class.Date;
import Class.FicheFrais;
import Class.Visiteur;
import Controler.Controler;



public class VueFicheValide extends JFrame implements ActionListener{
	
	private Controler leControler;
	
	private ArrayList<FicheFrais> lesFiches;
	
		/*Composant IHM*/
	
			/*Panels*/

	private JPanel panel = new JPanel(); //Panel General
	private JPanel panelTab = new JPanel();
	
			/*Labels*/
	
	private JLabel image = new JLabel(new ImageIcon(getClass().getResource("/image/logo.jpg")));
	
	
			/*JButton*/
	
	private JButton btnMenu = new JButton("Menu");
	private JButton btnOK = new JButton("Valider");
	
			/*JComboBox*/
	
	private JComboBox listePersonnes;
			
	
	//Constructeur
	
	public void afficheVisiteur(){
		panelTab.removeAll();
		
		lesFiches = new ArrayList<FicheFrais>();

		this.panelTab.setLayout(new BorderLayout());
		
		this.panelTab.setBounds(220,150, 800, 100);
		
	    this.panelTab.setBackground(new Color(170, 170, 170));
	    
		for(int i=0;i<this.leControler.getCFF().totalFacture();i++){
			
			if((Visiteur) listePersonnes.getSelectedItem() == this.leControler.getCFF().extraire(i).getUnVisiteur()){
				
				lesFiches.add(this.leControler.getCFF().extraire(i));

			}
		}
		JPanel sousTab = new JPanel();
		
		
		JPanel panel1 = new JPanel();
	
		panel1.setLayout(new GridLayout(lesFiches.size(),1));
		panel1.setSize(800, 300);
		JPanel miniPanel;
		
		for(int i=0;i<lesFiches.size();i++){
			miniPanel = new JPanel();
			miniPanel.setSize(700, 500);
			miniPanel.setLayout(new GridLayout(1, 4));
			
			if(i%2==0){
				miniPanel.setBackground(new Color(150, 200, 255));
				
			}else{
				miniPanel.setBackground(new Color(100, 200, 255));
				
			}
			JButton pdf = new JButton("pdf");
			pdf.setName(i+"");
			pdf.setName(lesFiches.get(i).getCleDate().getMoisEntier()+"");
			miniPanel.add(new JLabel(lesFiches.get(i).getCleDate().getMoisEntier()+""));//cle date
			miniPanel.add(new JLabel(lesFiches.get(i).getMontantValid()+""));
			miniPanel.add(new JLabel(lesFiches.get(i).getUnEtat()+""));
			miniPanel.add(pdf);
			pdf.addActionListener(new interactionPDF(leControler, (Visiteur) listePersonnes.getSelectedItem()));
			panel1.add(miniPanel);
			
		}
		 
		sousTab.add(panel1);
	  
		JScrollPane ScrollPane = new JScrollPane(sousTab, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
	    this.panelTab.add(ScrollPane,  BorderLayout.CENTER);
	    
	    //System.out.println(tableau);
	    
	    panel.repaint();
	    printAll(panel.getGraphics());
		
	}
	public VueFicheValide(Controler unControle) {
		
			
		this.leControler = unControle;
		
		Font f = new Font("Arial", Font.BOLD, 13); 
		
		btnMenu.setFont(f); 
		btnOK.setFont(f); 
		
		//Ajout des propositions du JComboBox
		
		//liste de tous les personnes;
		listePersonnes = this.leControler.getCVPersonne().menuDeroulantVisiteurFacture();
		
		//Ecoute des boutons
		
		this.btnMenu.addActionListener(this);
		this.btnOK.addActionListener(this);
		
		
		//Positionnement des Elements
		
		this.panel.setLayout(null);
		
		this.btnMenu.setBounds(80, 420, 100, 35);
		
		this.btnOK.setBounds(700, 70, 125, 35);
		
		this.listePersonnes.setBounds(400,70, 250, 35);
		
		this.image.setBounds(25, 10, 250, 200);
		
		
		//Elements mis dans les panels
		
		panel.add(btnMenu);
		panel.add(btnOK);
		
		this.panel.add(panelTab);
		this.panel.add(listePersonnes);
		this.panel.add(image);
		this.panel.setBackground(Color.white);
		
		this.setContentPane(panel); 
		
		/*Configuration de la Fenetre*/
		
		this.setTitle("GSB-Suivis des Fiches"); 
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
			this.leControler.getCVPersonne().retourMenu();
		}
		if((JButton)e.getSource() == this.btnOK){
			//afficheVisiteur(listePersonnes.getSelectedItem());
			afficheVisiteur();
		}
	}
	//Action du Menu
	class interactionPDF implements ActionListener{
		private Controler leControler;
		private Visiteur leVisiteur;
		public interactionPDF(Controler unControler, Visiteur unVisiteur){
			leControler = unControler;
			leVisiteur = unVisiteur;
		}
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 JButton active = (JButton)e.getSource();
			 //System.out.print(active.getName());
			 FicheFrais uneFiche = this.leControler.getCFF().captureFicheFrais(leVisiteur, 
					 this.leControler.getDate().captureDateInt(Integer.parseInt(active.getName()))
				);
			 new Pdf(this.leControler, uneFiche);
		}
	}
}
