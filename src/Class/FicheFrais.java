package Class;

import java.util.ArrayList;

import Controler.Controler;

public class FicheFrais {
	private int nbJustificatifs;
	private float montantValid;
	private int indiceDate;
	private Date derniereModif;
	private Date CleDate;
	
	private Etat unEtat;
	private Visiteur unVisiteur;
	private ArrayList<LigneFraisForfait> lesLigneFraisForfait;
	private ArrayList<LigneFraisHorsForfait> lesLigneFraisHorsForfait;
	public FicheFrais(int unNbJustificatifs, float unMontantValidn, String dateChaine, Date uneDerniereModif, Date uneCleDate, Etat uneEtat, Visiteur leVisiteur){
		this.nbJustificatifs = unNbJustificatifs;
		this.montantValid = unMontantValidn;
		this.indiceDate =  uneDerniereModif.rechercheChaineIndice(dateChaine);
		this.derniereModif = uneDerniereModif;
		this.CleDate = uneCleDate;
		
		this.unEtat = uneEtat;
		this.unVisiteur = leVisiteur;
		this.lesLigneFraisForfait = new ArrayList<LigneFraisForfait>();
		this.lesLigneFraisHorsForfait = new ArrayList<LigneFraisHorsForfait>();
	}
	
	public FicheFrais factureExiste(Visiteur leVisiteur, Date uneCleDate){
		FicheFrais uneFicheDeFrais = null;
		if(this.unVisiteur==leVisiteur && this.CleDate==uneCleDate){
			uneFicheDeFrais = this;
		}
		return uneFicheDeFrais;
	}
	public int getNbJustificatifs() {
		return nbJustificatifs;
	}


	public void setNbJustificatifs(int nbJustificatifs) {
		this.nbJustificatifs = nbJustificatifs;
	}


	public float getMontantValid() {
		return montantValid;
	}


	public void setMontantValid(float montantValid) {
		this.montantValid = montantValid;
	}


	public int getIndiceDate() {
		return indiceDate;
	}


	public void setIndiceDate(int indiceDate) {
		this.indiceDate = indiceDate;
	}


	public Date getDerniereModif() {
		return derniereModif;
	}


	public void setDerniereModif(Date derniereModif) {
		this.derniereModif = derniereModif;
	}


	public Date getCleDate() {
		return CleDate;
	}


	public void setCleDate(Date cleDate) {
		CleDate = cleDate;
	}


	public Etat getUnEtat() {
		return unEtat;
	}


	public void setUnEtat(Etat unEtat) {
		this.unEtat = unEtat;
	}


	public Visiteur getUnVisiteur() {
		return unVisiteur;
	}


	public void setUnVisiteur(Visiteur unVisiteur) {
		this.unVisiteur = unVisiteur;
	}


	public ArrayList<LigneFraisForfait> getLesLigneFraisForfait() {
		return lesLigneFraisForfait;
	}
	
	public LigneFraisForfait extraireFraisForfait(int i){
		
		return this.lesLigneFraisForfait.get(i);
	}

	public ArrayList<LigneFraisHorsForfait> getLesLigneFraisHorsForfait() {
		return lesLigneFraisHorsForfait;
	}
	/**
	 * montantTotal des facture
	 * @return le montant total
	 */
	public float montantTotal(){
		float total=0;
		for(int i=0;i<tailleFraisHorsForfait();i++){
			total = total + extraireFraisHorsForfait(i).getMontant();
		}
		for(int i=0;i<tailleFrais();i++){
	
			total = total + extraireFraisForfait(i).montant();
		}
		return total;
	}
	public LigneFraisHorsForfait extraireFraisHorsForfait(int i){
		return lesLigneFraisHorsForfait.get(i);
	}
	/**
	 * taille hors frais
	 * @return la taille des hors forfait
	 */
	public int tailleFraisHorsForfait(){
		return lesLigneFraisHorsForfait.size();
	}
	/**
	 * taille  frais
	 * @return la taille des frais
	 */
	public int tailleFrais(){
		return lesLigneFraisForfait.size();
	}
	public void setLesLigneFraisForfait(ArrayList<LigneFraisForfait> lesFicheFraisForfaits){
		this.lesLigneFraisForfait = lesFicheFraisForfaits;
	}
	
	public void setLesLigneFraisHorsForfait(ArrayList<LigneFraisHorsForfait> lesLignes){
		this.lesLigneFraisHorsForfait = lesLignes;
	}
	
}
