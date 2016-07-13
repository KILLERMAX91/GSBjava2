package Controler;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Vue.VueConnecte;

import Class.Date;
import Class.Etat;
import Class.FicheFrais;
import Class.LigneFraisForfait;
import Class.LigneFraisHorsForfait;
import Class.Visiteur;

public class SousControllerFacture {
	private ArrayList<FicheFrais> lesFicheDeFrais;
	private Controler unControle;
	public SousControllerFacture(Controler leControler){
		this.unControle = leControler;
		this.lesFicheDeFrais = new ArrayList<FicheFrais>();
	}
	public int totalFacture(){
		return this.lesFicheDeFrais.size();
	}
	public FicheFrais extraire(int i){
		return this.lesFicheDeFrais.get(i);
	}
	/**
	 * capture une fiche utilisateur a partir Visiteur est Date
	 * @param unVisiteur
	 * @param mois
	 * @return une ficheFrais
	 */
	public boolean captureFicheFraisHorsFrais(Visiteur unVisiteur, int mois){
		boolean ok=false;
		int i=0;
		
		Date maDate = this.unControle.getDate().captureDateInt(mois);
		//getCleDate
		
		while(i<totalFacture() && this.lesFicheDeFrais.get(i).factureExiste(unVisiteur, maDate)==null){
			i++;
		}
		if(i<totalFacture()){
			ok=true;
		}
		return ok;
	}
	public FicheFrais captureFicheFrais(Visiteur unVisiteur, Date uneDate){
		FicheFrais maFiche=null;
		int i=0;
		while(i<totalFacture() && this.lesFicheDeFrais.get(i).factureExiste(unVisiteur, uneDate)==null){
			i++;
		}
		if(i<totalFacture()){
			maFiche = this.lesFicheDeFrais.get(i).factureExiste(unVisiteur, uneDate);
		}
		return maFiche;
	}
	/**
	 * cette fonction permet de r�cupere tous les factures
	 */
	public void allFicheFrais(){
		ArrayList<Object[]> lesInfos = new ArrayList<Object[]>();
		Object[] uneInfo = new Object[6];
		FicheFrais uneFiche;
		this.lesFicheDeFrais.clear(); //suppression totaliter des fiches de frais afin de renouveler de nouvelle fiche de frais;
		int i;
		this.unControle.getLeModele().connexion();
		lesInfos  = this.unControle.getLeModele().recupererFicheFraisVisiteur();
		for(i=0;i<lesInfos.size();i++){
			uneInfo = lesInfos.get(i); //ATTENTION OBJECT[]
			
			uneFiche = new FicheFrais(Integer.parseInt(uneInfo[2].toString()), Float.parseFloat(uneInfo[3].toString()), uneInfo[4].toString(), 
					this.unControle.getDate().captureDateString(uneInfo[4].toString()), 
					this.unControle.getDate().captureDateInt(Integer.parseInt(uneInfo[1].toString())), 
					this.unControle.getCE().captureEtat(uneInfo[5].toString()), 
					this.unControle.getCVPersonne().captureVisiteur(uneInfo[0].toString())
					);
			FicheFraisAllFicheFrais(uneFiche, this.unControle.getLeModele().recupererLigneFraisForfait(uneInfo[0].toString(), uneInfo[1].toString()) );
			HorsFrais(uneFiche, this.unControle.getLeModele().recupererLigneFraisHorsForfait(uneInfo[0].toString(), uneInfo[1].toString()));
			this.lesFicheDeFrais.add(uneFiche);
		}
		
		this.unControle.getLeModele().deconnexion();
	}
	/**
	 * 
	 * @param uneFiche
	 * @param lesInfos
	 */
	public void FicheFraisAllFicheFrais(FicheFrais uneFiche, ArrayList<Object[]> lesInfos){
		ArrayList<LigneFraisForfait> lesFraisForfait = new ArrayList<LigneFraisForfait>();
		for(int i=0;i<lesInfos.size();i++){
			lesFraisForfait.add(
						new LigneFraisForfait(
								Integer.parseInt(lesInfos.get(i)[1].toString()), 
							this.unControle.getCFraisForfait().captureFraisForfait(lesInfos.get(i)[0].toString()),
							this.unControle.getCE().captureEtat(lesInfos.get(i)[2].toString())
						)
					);
					
		}
		uneFiche.setLesLigneFraisForfait(lesFraisForfait);
	}
	/**
	 * pemret d'actuliser les hors farsi de la facture
	 * @param uneFiche
	 * @param lesInfos
	 */
	public void HorsFrais(FicheFrais uneFiche, ArrayList<Object[]> lesInfos){
		//Date maDate = this.unControle.getDate().captureDateString(info[7]);
		ArrayList<LigneFraisHorsForfait> lesLigneFraisHorsForfait = new ArrayList<LigneFraisHorsForfait>();
		for(int i=0;i<lesInfos.size();i++){
			lesLigneFraisHorsForfait.add(
		
					new LigneFraisHorsForfait(
							Integer.parseInt(lesInfos.get(i)[0].toString()), //id
							Float.parseFloat(lesInfos.get(i)[3].toString()), //montant
							lesInfos.get(i)[1].toString(), //libelle
							this.unControle.getDate().captureDateString(lesInfos.get(i)[2].toString()), //date object
							this.unControle.getCE().captureEtat(lesInfos.get(i)[4].toString()), //etat object
							lesInfos.get(i)[2].toString()));
			
		}
		uneFiche.setLesLigneFraisHorsForfait(lesLigneFraisHorsForfait);
	}
	/**
	 * ajout un hors frais;
	 * @return une valeur qui permet de voir l'erreur
	 */
	public String ajoutHorsFrais(Visiteur leVisit, String unLibelle, String dateChaine, String unMontant){
		String valid = "";
		Object[] tabDate = this.unControle.getCHF().ajoutDateHorsFrais(dateChaine);
		Object[] tabChamp = this.unControle.getCHF().ajoutLibelle(unLibelle);
		Object[] tabMontant = this.unControle.getCHF().ajoutMontant(unMontant);
		if((boolean) tabDate[0]){
			if((boolean) tabChamp[0]){
				if((boolean) tabMontant[0]){
					//ajoutHorsFrais(String idVisiteur, int mois, String libelle, String date, float montant, String idEtat)
					//System.out.println("==>"+(String) tabChamp[1]+"__----"+(float) tabMontant[1]+"))))))");
					this.unControle.getLeModele().connexion();
					//recuperationFacture(this.unControle.getDate().dateEntierHorsFrais()
					if(!captureFicheFraisHorsFrais(leVisit, this.unControle.getDate().dateEntierHorsFrais())){
						this.unControle.getLeModele().creeNouvellesFicheFrais(leVisit, this.unControle.getDate().dateEntierHorsFrais(), 
								this.unControle.getCFraisForfait().getLesFraisForfait());
						
					}
					this.unControle.getLeModele().ajoutHorsFrais(leVisit.getNumPersonne(), this.unControle.getDate().dateEntierHorsFrais(), (String) tabChamp[1], (String) tabDate[1], (float) tabMontant[1], "CR");
					
					this.unControle.getCFF().allFicheFrais();
					
					this.unControle.getLeModele().deconnexion();
				}
			}
		}
		return valid;
	}
	/**
	 * permet de valider les frais
	 * @param input
	 * @param select
	 * @return un objet;
	 */
	public Object[] validFrais(JTextField[] input, JComboBox select){
		Object[] tab = new Object[3];
		int[] tabInput = new int[input.length];
		tab[1] = false;
		Etat uneEtat = (Etat) select.getSelectedItem();
		for(int i=0;i<input.length;i++){
			//tabInput[i] = input[i].getText();
			try{
				tabInput[i] = Integer.parseInt(input[i].getText());
			}catch(Exception e){		
				tabInput[i] = 0;
			}
		}
		tab[0] = true;
		tab[1] = tabInput;
		tab[2] = uneEtat;
		return tab;
	}
	/**
	 * permet de valider les hors frais
	 * @param inputLibelle
	 * @param inputDate
	 * @param inputMonatant
	 * @param selectHors
	 * @return un tableau d'objet
	 */
	public Object[] validHorsFrais(JTextField[] inputLibelle, JTextField[] inputDate, JTextField[] inputMonatant, JComboBox[] selectHors){
		Object[] tab = new Object[5];
		tab[0] = true;
		Object[] tabDate;
		String[] libelle = new String[inputLibelle.length];
		String[] lesdates = new String[inputDate.length];
		float[] lesMontant = new float[inputMonatant.length];
		Etat[] lesEtats = new Etat[selectHors.length];
		for(int i=0;i<inputLibelle.length;i++){
			libelle[i] = inputLibelle[i].getText();
			try{
				lesMontant[i] = Float.parseFloat(inputMonatant[i].getText());
			}catch(Exception e){		
				lesMontant[i] = 0;
			}
			tabDate = this.unControle.getCHF().ajoutDateHorsFrais(inputDate[i].getText());
			if((boolean) tabDate[0]){
				lesdates[i] = inputDate[i].getText();
			}else{
				
				tab[0] = false;
			}
			lesEtats[i] = (Etat) selectHors[i].getSelectedItem();
		}
		tab[1] = libelle;
		tab[2] = lesdates;
		tab[3] = lesMontant;
		tab[4] = lesEtats;
		return tab;
	}
	/**
	 * activer le bouton
	 * @param ficheEnCours
	 * @return un boolean
	 */
	public boolean boutonValider(FicheFrais ficheEnCours){
		boolean  ok = true;
		if(ficheEnCours.getUnEtat().getNumEtat().equals("RB")){
			ok = false;
		}
		if(ficheEnCours.getUnEtat().getNumEtat().equals("PR")){
			ok = false;
		}
		if(!this.unControle.getDate().validerFactureDate(ficheEnCours.getCleDate())){
			ok = false;
		}
		return ok;
	}
	/*
	 * c'est chiant GSB et JAVA
	 * 
	 */
	/**
	 *  permet de valider une facture generale
	 * @param input
	 * @param inputLibelle
	 * @param inputDate
	 * @param inputMonatant
	 * @param select
	 * @param selectHors
	 * @param selectFicheFrais
	 * @param ficheEnCours
	 * @return une valeur qui permet de voir l'erreur
	 */
	public String valid(JTextField[] input, JTextField[] inputLibelle, JTextField[] inputDate, JTextField[] inputMonatant, JComboBox select, JComboBox[] selectHors, JComboBox selectFicheFrais, FicheFrais ficheEnCours){
		String valid = "";
		
		Object[] ficheFrais = validFrais(input, select);
		Object[] ficheHorsFrais = validHorsFrais(inputLibelle, inputDate, inputMonatant, selectHors);
		if((boolean) ficheFrais[0]){
			
			if((boolean) ficheHorsFrais[0]){
				
				this.unControle.getLeModele().connexion();
				this.unControle.getLeModele().validFacture(ficheFrais, ficheHorsFrais, ficheEnCours, this.unControle, (Etat) selectFicheFrais.getSelectedItem(), this.unControle.getDate().dateStringTodays());
				this.unControle.getCFF().allFicheFrais();
				this.unControle.getLeModele().deconnexion();
			}
		}
		return valid;
	}
	
}
