package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

import Class.Date;
import Class.FicheFrais;
import Class.LigneFraisHorsForfait;
import Class.Visiteur;
import Controler.Controler;

public class VueConnecteFacture extends JFrame implements ActionListener{
	
	private Controler unControler;
	private FicheFrais ficheEnCours;
	private Visiteur leVisiteur;
	private Date leMois;
	
	
		/*Composants IHM*/
	
			/*Panels*/
	
	private JPanel panel, panelCle, panelFacture;
			
	
			/*Labels*/
	
	private JLabel lblVisit;
	private JLabel lblMois;
	
	
			/*JButton*/
	
	private JButton btnCle, valider;
	private JButton bouton; //Correspond au bouton ajouter 
	private JButton btnMenu = new JButton("Menu");
	
	
			/*JTextField*/
	
	private JTextField mois = new JTextField("");
	private JTextField input[], inputLibelle[], inputDate[], inputMonatant[], ajoutLibelle, ajoutDate, ajoutMontant;
	
	
			/*JComboBox*/
	
	private JComboBox comboMois, comboUser;
	private JComboBox select, selectHors[], selectFicheFrais;
	
	
	public VueConnecteFacture(Controler leControler){
		this.unControler = leControler;
		this.ficheEnCours = this.unControler.getCVPersonne().recuperationFactureDefaut();
		
		if(ficheEnCours!=null){
			afficheFenetre();
		}else{
			afficheFenetreNotExist();
		}
		
		
	}
	public VueConnecteFacture(Controler leControler, FicheFrais frais, Visiteur leVisiteur){
		this.unControler = leControler;
		this.ficheEnCours = frais;
		
		if(ficheEnCours!=null){
			afficheFenetre();
			this.leVisiteur = leVisiteur;
		}else{
			afficheFenetreNotExist();
			this.leVisiteur = leVisiteur;
		}
	}
	
	
	public JPanel panelCle(){
		this.panelCle = new JPanel();
		this.panelCle.setPreferredSize(new Dimension(1024, 100));
		
		this.comboMois = this.unControler.getCVPersonne().menuDeroulantDate();
		
		this.panelCle.add(comboMois);
		
		this.comboUser = this.unControler.getCVPersonne().menuDeroulantVisiteur();
		
		
		
		this.panelCle.add(comboUser);
		this.btnCle = new JButton("envoyer");
	
		this.btnCle.addActionListener(this);
		this.panelCle.add(btnCle);
		return panelCle;
	}
	
	public JPanel panelFacture(){
		this.panelFacture = new JPanel();
		this.panelFacture.setPreferredSize(new Dimension(1024, 600));
		this.panelFacture.setLayout(new GridLayout(3, 1));
		
		this.panelFacture.add(this.FraisAuForfait());
		
		this.panelFacture.add(this.HorsFraisAUFrais());
		this.panelFacture.add(this.ficheFrais());
		return panelFacture;
	}
	public void afficheFenetre(){ //Affiche la Fenetre lorsqu'il existe une facture
		panel = new JPanel();
		
		panel.add(panelCle(), BorderLayout.SOUTH);
		if(this.ficheEnCours!=null){
			panel.add(panelFacture());
		}else{
			
		}
		this.setContentPane(panel); 
		this.setLocation(180,120);	
		this.setSize(1075,750);
		this.setResizable(false);  
		this.setAlwaysOnTop(false); 
		this.setVisible(true);
	}
	public void afficheFenetreNotExist(){ //Affiche la Fenetre lorsqu'il n'existe aucune facture
		panel = new JPanel();
		
		panel.add(panelCle(), BorderLayout.SOUTH);
		panel.add(ajoutHorsForfait());
	
		this.setContentPane(panel); 
		
		this.setTitle("GSB-Validation Fiches"); 
		this.setLocation(180,120);	
		this.setSize(1075,700);
		this.setResizable(false);  
		this.setAlwaysOnTop(false); 
		this.setVisible(true);
	}
	public JPanel FraisAuForfait(){
		FicheFrais element = this.ficheEnCours; 
		JPanel panel = new JPanel();
		
		JLabel lbl;
		panel.setLayout(new GridLayout(this.unControler.getCFraisForfait().taille()+1, 2)); 
		//panel.setPreferredSize(new Dimension(100, 90));
		
		this.input = new JTextField[this.unControler.getCFraisForfait().taille()];
		this.select = new JComboBox();
		
		for(int i=0;i<this.unControler.getCFraisForfait().taille();i++){
			
			lbl = new JLabel(this.unControler.getCFraisForfait().extraire(i).getLibelle());
			panel.add(lbl);
			
			
			
			input[i] = new JTextField(element.extraireFraisForfait(i)+"");
			panel.add(input[i]);
		}
		lbl = new JLabel("validation frais");
		panel.add(lbl);
		if(!element.extraireFraisForfait(0).getUnEtat().getNumEtat().equals("CR")){
			select.addItem(element.extraireFraisForfait(0).getUnEtat());
		}
		for(int i=0;i<this.unControler.getCE().taille();i++){
			if(!this.unControler.getCE().extraire(i).equals("CR") && this.unControler.getCE().extraire(i)!=element.extraireFraisForfait(0).getUnEtat()){
				select.addItem(this.unControler.getCE().extraire(i));
			}
		}
		
		//etatInput = new JTextField(element.extraireFraisForfait(0).getUnEtat().getLibelle()+"");
		panel.add(select);
		return panel;
	}
	
	public JPanel ficheFrais(){
		JPanel panel, affiche, ajout;
		JLabel nbrJustificatif, total;
		panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		affiche = new JPanel();
		affiche.setLayout(new GridLayout(1, 3));
		nbrJustificatif = new JLabel(""+this.ficheEnCours.tailleFraisHorsForfait());
		affiche.add(nbrJustificatif, BorderLayout.CENTER);
		total = new JLabel(""+this.ficheEnCours.montantTotal());
		affiche.add(total, BorderLayout.CENTER);
		this.selectFicheFrais = new JComboBox();
		if(!this.ficheEnCours.getUnEtat().getNumEtat().equals("CR")){
			selectFicheFrais.addItem(this.ficheEnCours.getUnEtat());
		}
		for(int i=0;i<this.unControler.getCE().taille();i++){
			if(!this.unControler.getCE().extraire(i).equals("CR") && this.unControler.getCE().extraire(i)!=this.ficheEnCours.getUnEtat()){
				selectFicheFrais.addItem(this.unControler.getCE().extraire(i));
			}
		}
		affiche.add(this.selectFicheFrais);
		panel.add(affiche);
		
		ajout = new JPanel();
		ajout.setLayout(new GridLayout(1, 2));
		this.valider = new JButton("valider");
		//;
		//if() ficheEnCours
		if(!this.unControler.getCFF().boutonValider(this.ficheEnCours)){
			this.valider.setEnabled(false);
		}
		this.valider.addActionListener(this);
		ajout.add(this.valider);
		
		this.btnMenu.addActionListener(this);
		ajout.add(this.btnMenu);
		panel.add(ajout);
		return panel;
	}
	
	public JPanel HorsFraisAUFrais(){
		JLabel infoLibelle, infoDate, infoMontant, infoEtat;
		JPanel ligne, ajout, panel, modif, info;
		
		panel = new JPanel();
		
		//panel.setPreferredSize(new Dimension(1024, 2004));
		//panel.setLayout(new GridLayout(4, 1));
		info = new JPanel();
		
		info.setBounds(400, 80, 400, 50);
		info.setPreferredSize(new Dimension(1024, 50));
		info.setLayout(new GridLayout(1, 4));
		
		
		infoLibelle = new JLabel("libelle");
		info.add(infoLibelle);
		
		infoDate = new JLabel("date");
		info.add(infoDate);
		
		infoMontant = new JLabel("montant");
		info.add(infoMontant);
		
		infoEtat = new JLabel("etat");
		info.add(infoEtat);
		
		panel.add(info);
		ligne = new JPanel();
		//ligne.setPreferredSize(new Dimension(50, 70));
		ligne.setPreferredSize(new Dimension(1000, 70));
		ligne.setLayout(new GridLayout(this.ficheEnCours.tailleFraisHorsForfait(), 1));
		
		this.inputLibelle = new JTextField[this.ficheEnCours.tailleFraisHorsForfait()];
		this.inputDate = new JTextField[this.ficheEnCours.tailleFraisHorsForfait()];
		this.inputMonatant = new JTextField[this.ficheEnCours.tailleFraisHorsForfait()];
		this.selectHors = new JComboBox[this.ficheEnCours.tailleFraisHorsForfait()];
		for(int i=0;i<this.ficheEnCours.tailleFraisHorsForfait();i++){
			
			modif = new JPanel();
			
			modif.setPreferredSize(new Dimension(1000, 50));
			modif.setLayout(new GridLayout(1, 4));
			
			this.inputLibelle[i] = new JTextField(this.ficheEnCours.extraireFraisHorsForfait(i)+"");
			modif.add(this.inputLibelle[i]);
			
			this.inputDate[i] = new JTextField(this.ficheEnCours.extraireFraisHorsForfait(i).getUneDate().dateFR(this.ficheEnCours.extraireFraisHorsForfait(i).getIndiceDate())+"");
			modif.add(this.inputDate[i]);
			
			this.inputMonatant[i] = new JTextField(this.ficheEnCours.extraireFraisHorsForfait(i).getMontant()+"");
			modif.add(this.inputMonatant[i]);
			
			this.selectHors[i] = new JComboBox();
			if(!this.ficheEnCours.extraireFraisHorsForfait(i).getUneEtat().getNumEtat().equals("CR")){
				selectHors[i].addItem(this.ficheEnCours.extraireFraisHorsForfait(i).getUneEtat());
			}
			for(int numEtat=0;numEtat<this.unControler.getCE().taille();numEtat++){
				if(!this.unControler.getCE().extraire(numEtat).equals("CR") && this.unControler.getCE().extraire(numEtat)!=this.ficheEnCours.extraireFraisHorsForfait(i).getUneEtat()){
					selectHors[i].addItem(this.unControler.getCE().extraire(numEtat));
				}
			}
			modif.add(selectHors[i]);
			
			ligne.add(modif);
		}
		JScrollPane scroll = new JScrollPane(ligne); 
		scroll.setPreferredSize(new Dimension(1024, 50));
		panel.add(scroll, BorderLayout.CENTER);
		//ajoutLibelle, ajoutDate, ajoutMontant
		ajout = new JPanel();
		
		ajout.setPreferredSize(new Dimension(1024, 30));
		ajout.setBounds(000, 0, 00, 0);
		ajout.setLayout(new GridLayout(1, 3));
		JPanel sousAjout = new JPanel();
		
		
		sousAjout.setLayout(new GridLayout(1, 2));
		this.ajoutLibelle = new JTextField();
		sousAjout.add(new JLabel("libelle: "));
		sousAjout.add(this.ajoutLibelle);
		ajout.add(sousAjout);
		
		sousAjout.setLayout(new GridLayout(1, 2));
		this.ajoutDate = new JTextField();
		sousAjout.add(new JLabel("date: "));
		sousAjout.add(this.ajoutDate);
		ajout.add(sousAjout);
		
		sousAjout.setLayout(new GridLayout(1, 2));
		this.ajoutMontant = new JTextField();
		sousAjout.add(new JLabel("montant: "));
		sousAjout.add(this.ajoutMontant);
		ajout.add(sousAjout);
		
		panel.add(ajout);
		JPanel boutonPanel = new JPanel();
		boutonPanel.setPreferredSize(new Dimension(1000, 50));
		bouton = new JButton("ajouter");
		boutonPanel.setBounds(000, 0, 00, 0);
		boutonPanel.add(bouton);
		bouton.addActionListener(this);
		panel.add(boutonPanel);
		
		return panel;
	}
	public JPanel ajoutHorsForfait(){
		JPanel ajout = new JPanel();
		JPanel panel = new JPanel();
		JPanel panelMenu = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		ajout.setLayout(new GridLayout(1, 3));
		JPanel sousAjout = new JPanel();
		
		
		sousAjout.setLayout(new GridLayout(1, 2));
		this.ajoutLibelle = new JTextField();
		sousAjout.add(new JLabel("libelle: "));
		sousAjout.add(this.ajoutLibelle);
		ajout.add(sousAjout);
		
		sousAjout.setLayout(new GridLayout(1, 2));
		this.ajoutDate = new JTextField();
		sousAjout.add(new JLabel("date: "));
		sousAjout.add(this.ajoutDate);
		ajout.add(sousAjout);
		
		sousAjout.setLayout(new GridLayout(1, 2));
		this.ajoutMontant = new JTextField();
		sousAjout.add(new JLabel("montant: "));
		sousAjout.add(this.ajoutMontant);
		ajout.add(sousAjout);
		
		panel.add(ajout);
		JPanel boutonPanel = new JPanel();
		boutonPanel.setPreferredSize(new Dimension(1000, 50));
		bouton = new JButton("ajouter");
		boutonPanel.setBounds(000, 0, 00, 0);
		boutonPanel.add(bouton);
		bouton.addActionListener(this);
		panel.add(boutonPanel);
		this.btnMenu.addActionListener(this);
		panelMenu.add(btnMenu);
		panel.add(panelMenu);
		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if((JButton)e.getSource() == this.btnCle) {
			//comboMois, comboUser;
			this.dispose();
			Visiteur item = (Visiteur) comboUser.getSelectedItem();
			try{
				
				Date leMois = (Date) comboMois.getSelectedItem();
				this.unControler.getCVPersonne().recuperationFacture(leMois, item);
			}catch(Exception es){
				this.unControler.getCVPersonne().recuperationFactureSansDate(item);
			}
			
			
			
			
		}else if((JButton)e.getSource() == this.bouton){
			Visiteur item;
			if(this.leVisiteur==null){
				item = (Visiteur) comboUser.getSelectedItem();
			}else{
				item = this.leVisiteur;
			}
			
			//Date leMois = (Date) comboMois.getSelectedItem();
			//ajoutMontant ajoutDate ajoutLibelle
			
			this.unControler.getCFF().ajoutHorsFrais(item, ajoutLibelle.getText(), ajoutDate.getText(), ajoutMontant.getText());
			this.dispose();
			this.unControler.getCVPersonne().recuperationFacture(leMois, item);
		}else if((JButton)e.getSource() == this.valider){
			this.dispose();
			this.unControler.getCFF().valid(input, inputLibelle, inputDate, inputMonatant, select, selectHors, selectFicheFrais, this.ficheEnCours);
			this.unControler.getCVPersonne().recuperationFacture(this.ficheEnCours.getCleDate(), this.ficheEnCours.getUnVisiteur());
		}else if((JButton)e.getSource() == this.btnMenu){
			this.dispose();
			this.unControler.getCVPersonne().retourMenu();
		}
	}
	
}
