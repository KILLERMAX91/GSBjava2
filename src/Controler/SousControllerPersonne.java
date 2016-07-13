package Controler;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Vue.VueConnecte;
import Vue.VueConnecteFacture;
import Vue.VueFicheValide;
import Vue.VueLogin;
import Vue.Vue_aPropos;
import Class.Comptable;
import Class.Date;
import Class.FicheFrais;
import Class.LigneFraisHorsForfait;
import Class.Visiteur;


public class SousControllerPersonne {
	private ArrayList<Visiteur> lesVisiteur;
	private VueConnecte connecter;
	private VueConnecteFacture infoFacture; //VUE Panel
	private Comptable unComptable;
	private Controler unContole;
	private Vue_aPropos infosProg;
	private VueFicheValide valide;
	public SousControllerPersonne(Controler cool){
		this.lesVisiteur = new ArrayList<Visiteur>();
		this.unContole = cool;
	}
	public Controler getUnContole(){
		return this.unContole;
	}
	/**
	 * le fonction permet initialiser l'object Comptable avec le login et le mot passe
	 * 
	 * CALL COMPTABLE
	 * 
	 * 
	 * @param login
	 * @param mdp
	 * @return un booleen 
	 */
	public boolean connectComptableBDD(String login, String mdp){
		this.unContole.getLeModele().connexion();
		boolean ok=false;
		Date maDate;
		String[] info = this.unContole.getLeModele().connectComptable(login, mdp);
		if(info[0]!=null){
			ok=true;
			//ajoutDateString(info[7]);
			maDate = this.unContole.getDate().captureDateString(info[7]); //fonction qui capture est ajoute dans la collection
			this.unComptable = new Comptable(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[8], maDate, info[7]); //CALL COMPTABLE
		}
		this.unContole.getLeModele().deconnexion();
		return ok;
	}
	/**
	 * 
	 * @return un Comptable
	 */
	public Comptable getUnComptable(){
		return unComptable;
	}
	/**
	 * cette methode permet d'apeller tous les visiteur dans une collection
	 * 
	 * CALL VISITEUR
	 * 
	 */
	public void callVisiteur(){
		this.unContole.getLeModele().connexion();
		String[] info = new String[9];
		Date maDate;
		int i;
		ArrayList<String[]> lesInfosVisiteur = this.unContole.getLeModele().recupererVisiteur();
		for(i=0;i<lesInfosVisiteur.size();i++){
			info = lesInfosVisiteur.get(i);
			//System.out.println(lesInfosVisiteur.get(i)[0]);
			
			maDate = this.unContole.getDate().captureDateString(info[7]);
			this.lesVisiteur.add(new Visiteur(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[8], maDate, info[7]));
			
		}
		this.unContole.getLeModele().deconnexion();
	}
	public Visiteur captureVisiteur(String cle){
		int i=0;
		Visiteur unVisiteur = null;
		while(i<this.lesVisiteur.size() && !cle.equals(this.lesVisiteur.get(i).getNumPersonne())){
			i++;
		}
		if(i<this.lesVisiteur.size()){
			unVisiteur = this.lesVisiteur.get(i);
		}
		return unVisiteur;
	}
	
	/*||||||||||||||||||||||||||
	 * |||||||||||||||||||||||||
	 * || \    / |    | |--- |||
	 * ||  \  /  |    | |--  |||
	 * ||   \/   |____| |--- ||| 
	 * |||||||||||||||||||||||||
	 * |||||||||||||||||||||||||
	 */
	public ArrayList<Date> datefacture(){
		int dateTrouvee;
		ArrayList<Date> lesDates = new ArrayList<Date>();
		for(int i=0;i<this.unContole.getDate().tailleDate();i++){
			dateTrouvee = 0;
			while(dateTrouvee<this.unContole.getCFF().totalFacture() && this.unContole.getDate().extraireUneDate(i)!=this.unContole.getCFF().extraire(dateTrouvee).getCleDate()){
				dateTrouvee++;
			}
			if(dateTrouvee<this.unContole.getCFF().totalFacture()){
				lesDates.add(this.unContole.getDate().extraireUneDate(i));
			}
		}
		return lesDates;
	}
	public void demarrerInfosProgramme(){ //renvoie sur la fenêtre a propos
		infosProg = new Vue_aPropos(this.unContole);
	
	}
	public void ficheValide(){
		this.valide = new VueFicheValide(this.unContole);
	}
	public void retourMenu() {
		this.connecter = new VueConnecte(this.unContole);	
	}
	public ArrayList<Visiteur> visiteurfacture(){
		int visiteurTrouvee;
		ArrayList<Visiteur> lesVisiteurFacture = new ArrayList<Visiteur>();
		for(int i=0;i<this.lesVisiteur.size();i++){
			visiteurTrouvee = 0;
			while(visiteurTrouvee<this.unContole.getCFF().totalFacture() && this.lesVisiteur.get(i)!=this.unContole.getCFF().extraire(visiteurTrouvee).getUnVisiteur()){
				visiteurTrouvee++;
			}
			if(visiteurTrouvee<this.unContole.getCFF().totalFacture()){
				lesVisiteurFacture.add(this.lesVisiteur.get(i));
			}
		}
		return lesVisiteurFacture;
	}
	/**
	 * Menu déroulant des date des factures
	 * @return une liste deroulante de date
	 */
	public JComboBox menuDeroulantDate(){
		JComboBox combo = new JComboBox();
		ArrayList<Date> lesDates = datefacture();
		if(lesDates.size()==0){
			combo.addItem(000000);
		}else{
			for(int i=0;i<lesDates.size();i++){
				combo.addItem(lesDates.get(i));
			}
		}
		return combo;
	}
	/**
	 * Menu déroulant des visiteur des factures
	 * @return une liste deroulante de visiteur
	 */
	public JComboBox menuDeroulantVisiteur(){
		JComboBox combo = new JComboBox();
		//ArrayList<Visiteur> lesVisiteur = visiteurfacture();
		if(lesVisiteur.size()==0){
			combo.addItem("vide");
		}else{
			for(int i=0;i<lesVisiteur.size();i++){
				combo.addItem(lesVisiteur.get(i));
			}
		}
		return combo;
	}
	/**
	 * Menu déroulant des visiteur uniquement avec ses factiure factures
	 * @return une liste deroulante de visiteur si ils ont une facture
	 */
	public JComboBox menuDeroulantVisiteurFacture(){
		JComboBox combo = new JComboBox();
		int ii;
		//ArrayList<Visiteur> lesVisiteur = visiteurfacture();
		if(lesVisiteur.size()==0){
			combo.addItem("vide");
		}else{
			/*for(int i=0;i<lesVisiteur.size();i++){
				combo.addItem(lesVisiteur.get(i));
			}*/
			/*while(i<this.unContole.getCFF().totalFacture()){
				i++;
			}*/
			for(int i=0;i<lesVisiteur.size();i++){
				ii=0;
				while(ii<this.unContole.getCFF().totalFacture() && this.unContole.getCFF().extraire(ii).getUnVisiteur()!=lesVisiteur.get(i)){
					ii++;
				}
				if(ii<this.unContole.getCFF().totalFacture()){
					combo.addItem(lesVisiteur.get(i));
				}
			}
		}
		return combo;
	}
	public void login(){
		this.unContole.setLogin(new VueLogin(this.unContole));
	}
	public void ficheComptable(){
		this.callVisiteur();
		this.unContole.getCE().allEtat();
		this.unContole.getCFraisForfait().allFraisForfait();
		this.unContole.getCFF().allFicheFrais();
		this.connecter = new VueConnecte(this.unContole);
		//demarrerInfoFacture();
	}
	public void demarrerInfoFacture(){
		infoFacture = new VueConnecteFacture(this.unContole);

	}
	//public FicheFrais
	public FicheFrais recuperationFactureDefaut(){
		FicheFrais uneFiche;
		ArrayList<Visiteur> lesVisiteur = visiteurfacture();
		ArrayList<Date> lesDates = datefacture();
		
		if(this.unContole.getCFF().totalFacture()!=0){
			uneFiche = this.unContole.getCFF().captureFicheFrais(lesVisiteur.get(0), lesDates.get(0));
		}else{
			uneFiche = null;
		}
		return uneFiche;
	}
	/**
	 * 
	 * @param moisEntier
	 * @param visiteur
	 * 
	 */
	public void recuperationFacture(Date moisEntier, Visiteur visiteur){
		FicheFrais uneFiche;
		uneFiche = this.unContole.getCFF().captureFicheFrais(visiteur, moisEntier);
	
		this.infoFacture = new VueConnecteFacture(this.unContole, uneFiche, visiteur);
	}
	public void recuperationFactureSansDate(Visiteur visiteur){
		FicheFrais uneFiche;
		Date moisEntier = new Date(this.unContole.getDate().dateEntierTodays());
		uneFiche = this.unContole.getCFF().captureFicheFrais(visiteur, moisEntier);
	
		this.infoFacture = new VueConnecteFacture(this.unContole, uneFiche, visiteur);
	}
	/*public JPanel infoFacture(){
		return infoFacture.afficheFenetre();
	}*/
	
}
