package Controler;

import java.util.ArrayList;
import Class.FraisForfait;

public class SousControllerFraisForfait {
	private ArrayList<FraisForfait> lesFraisForfait;
	private Controler unContole;
	public SousControllerFraisForfait(Controler leControle){
		this.lesFraisForfait = new ArrayList<FraisForfait>();
		this.unContole = leControle;
		
	}
	public void allFraisForfait(){
		this.unContole.getLeModele().connexion();
		this.lesFraisForfait = this.unContole.getLeModele().recupererFraisForfait();
		this.unContole.getLeModele().deconnexion();
	}
	public FraisForfait captureFraisForfait(String num){
		FraisForfait unFraisForfait = null;
		int i=0;
		while(i<lesFraisForfait.size() && !num.equals(this.lesFraisForfait.get(i).getNumFraisForfaits())){
			i++;
		}
		if(i<lesFraisForfait.size()){
			unFraisForfait = this.lesFraisForfait.get(i);
		}
		return unFraisForfait;
	}
	public int taille(){
		return lesFraisForfait.size();
	}
	public FraisForfait extraire(int i){
		return lesFraisForfait.get(i);	
	}
	public ArrayList<FraisForfait> getLesFraisForfait() {
		return lesFraisForfait;
	}
	public void setLesFraisForfait(ArrayList<FraisForfait> lesFraisForfait) {
		this.lesFraisForfait = lesFraisForfait;
	}
}
