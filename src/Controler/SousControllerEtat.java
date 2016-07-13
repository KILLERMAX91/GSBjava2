package Controler;
import java.util.ArrayList;

import Class.Etat;
public class SousControllerEtat {
	private ArrayList<Etat> lesEtats;
	private Controler unContole;
	public SousControllerEtat(Controler leControle){
		this.lesEtats = new ArrayList<Etat>();
		this.unContole = leControle;
	}
	/**
	 * ajouter tous les etats
	 */
	public void allEtat(){
		this.unContole.getLeModele().connexion();
		
		this.lesEtats = this.unContole.getLeModele().recupererEtat();
		
		this.unContole.getLeModele().deconnexion();
	}
	/**
	 * fonction qui capture une etat
	 * @param numEtat
	 * @return une Etat
	 */
	public Etat captureEtat(String numEtat){
		
		Etat uneEtat = null;
		int i=0;
		while(i<this.lesEtats.size() && !numEtat.equals(this.lesEtats.get(i).getNumEtat())){
			i++;
		}
		if(i<this.lesEtats.size()){
			uneEtat = this.lesEtats.get(i);
		}
		return uneEtat;
	}
	/**
	 * taille de la collection
	 * @return taille
	 */
	public int taille(){
		return this.lesEtats.size();
	}
	public ArrayList<Etat> getLesEtats(){
		return this.lesEtats;
	}
	public Etat extraire(int i){
		return this.lesEtats.get(i);
	}
}
