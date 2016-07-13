package Controler;

import java.text.DecimalFormat;

import Class.Date;
import Class.LigneFraisHorsForfait;

public class SousControleHorsFrais {
	
	private Controler unControle;
	public SousControleHorsFrais(Controler leControle){
		this.unControle = leControle;
	}
	public Object[] ajoutLibelle(String champ){
		Object[] ok  = new Object[2];	
		ok[0] = false;
		if(champ.length()<=100){
			ok[0] = true;
			ok[1] = champ;
			
		}
		return ok;
	}
	public Object[] ajoutDateHorsFrais(String date){
		//boolean ok=false;
		Object[] tab;
		Object[] ok = new Object[2];
		ok[0]=false;
		
		if(this.unControle.getDate().isValid(date)){
			//tab = this.unControle.getDate().difference(date);
			
			if(this.unControle.getDate().difference(date)){
				tab = this.unControle.getDate().dateFrEndateEnglais(date);
				
				if((boolean) tab[0]){
					
					ok[0]= true;
					ok[1] = (String) tab[1];
					
				}
				
			}
		}
		return ok;
	}
	public Object[] ajoutMontant(String montant){
		Object[] ok = new Object[2];
		ok[0]=false;
		float f=0;
		montant = montant.replace(",", ".");
		try{
			f = Float.parseFloat(montant);
			ok[0]=true;
		}catch(Exception e){
			
		}
		ok[1]=(float) Math.round(f * 100) / 100;
		
		return ok;
		
	}
}
