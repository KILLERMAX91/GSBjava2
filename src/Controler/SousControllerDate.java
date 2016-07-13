package Controler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import Class.Date;
import Class.Visiteur;


public class SousControllerDate {
	private ArrayList<Date> lesDates;
	private Calendar calendrier;
	private  java.util.Date dateJava;
	public SousControllerDate(){
		this.lesDates = new  ArrayList<Date>();
		this.calendrier = new GregorianCalendar();
		this.dateJava = new java.util.Date();
	}
	public int tailleDate(){
		return this.lesDates.size();
	}
	//fonction est autre chose
	/**
	* recheche une date a partir du parametre unedateInt(entier)
	* @param unedateInt
	* @return  l'indice de la classe date
	*/
	public int rechercheDate(int unedateInt){
		int i;
		i=0;
		//System.out.println(this.lesDates.size());
		while(i<this.lesDates.size() && this.lesDates.get(i).getMoisEntier()!=unedateInt){
			//System.out.println("==="+this.lesDates.get(i).getMoisEntier());
			i++;
		}
		if(i==this.lesDates.size()){
			i = -1;
		}
		
		return i;
	}
	public int dateEntierTodays(){
		calendrier.setTime(this.dateJava);
		String mois = ""+(calendrier.get(GregorianCalendar.MONTH)+1);
		if((calendrier.get(GregorianCalendar.MONTH)+1)<=9){
			mois = "0"+mois;
		}
		return  Integer.parseInt(calendrier.get(GregorianCalendar.YEAR)+""+mois);
	}
	public String dateStringTodays(){
		calendrier.setTime(this.dateJava);
		String mois = ""+(calendrier.get(GregorianCalendar.MONTH)+1);
		String jour = ""+calendrier.get(GregorianCalendar.DAY_OF_MONTH);
		
		return calendrier.get(GregorianCalendar.YEAR)+"-"+(mois)+"-"+jour;
	}
	/**
	 * permet de savoir si la facture ne depasse pas la date
	 * @param uneDate
	 * @return un boolean
	 */
	public boolean validerFactureDate(Date uneDate){
		boolean ok = false;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			java.util.Date date1 = format.parse(uneDate.convertirEntierEnChaine(uneDate.getMoisEntier()));
			java.util.Date date2 = format.parse(cal.get(GregorianCalendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-11");
			if(date1.before(date2)){
				ok=true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if(date1.before(date2))
		
		return ok;
	}
	public boolean isValid(String format) {
		// Format jj-mm-aaaa ou jj-mm-aa
		 boolean ok;
		 format = format.replaceAll("-", "/");
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 String s = format;
		 java.util.Date d = new java.util.Date();
		
	      try {
	        	sdf.setLenient(false);
	            d = sdf.parse(s);
	            String t = sdf.format(d);
	            if(t.compareTo(s) !=  0){
	            	
		            ok = false;
	            }else{
	                
	                ok = true;
	            }
	        } catch (Exception e) {
	        	
	        	System.out.println("1)"+e.getMessage());
	        	ok = false;
	        }
	      
		 return ok;
	}
	public int dateEntierHorsFrais(){
		calendrier.setTime(this.dateJava);
		if(calendrier.get(GregorianCalendar.DAY_OF_MONTH)>=10){
			calendrier.add(calendrier.MONTH, 1); //ajouter un mois
		}
		String mois = ""+(calendrier.get(GregorianCalendar.MONTH)+1);
		
		return  Integer.parseInt(calendrier.get(GregorianCalendar.YEAR)+""+mois);
	}
	
	public Object[] dateFrEndateEnglais(String uneDate){
		Object tab[] = new Object[2];
		tab[0] = false;
		uneDate = uneDate.replaceAll("-", "/");
		String[] tabDate = uneDate.split("/");
		if(tabDate.length==3){
			tab[0] = true;
			tab[1] = tabDate[2]+"/"+tabDate[1]+"/"+tabDate[0];
		}
		return tab;
		
	}
	public boolean difference(String dateChaine){
		//int ans, int mois, int jour
		boolean ok = true;
		dateChaine = dateChaine.replaceAll("-", "/");
		String[] tab = dateChaine.split("/");
		int jour =  Integer.parseInt((tab[0]));
		int mois = Integer.parseInt((tab[1]));
		int ans = Integer.parseInt((tab[2]));
		mois=mois-1;
		Calendar difference = Calendar.getInstance();
		difference.clear();
		difference.set(ans, mois, jour);
		Calendar now = Calendar.getInstance();
		Calendar clone = (Calendar) difference.clone(); // Otherwise changes are been reflected.
		int ansCompte = -1;
		int moisCompte = -1;
		int jourCompte = -1;
		while (!clone.after(now)){
			System.out.print(clone.after(now));
		    clone.add(Calendar.YEAR, 1);
		    ansCompte++;
		}
		while (!clone.after(now)){
			System.out.print(clone.after(now));
		    clone.add(Calendar.MONTH, 1);
		    moisCompte++;
		}
		while (!clone.after(now)){
			System.out.print(clone.after(now));
		    clone.add(Calendar.DAY_OF_MONTH, 1);
		    jourCompte++;
		}
		if(ansCompte==1){
			if(moisCompte==0){
				if(jourCompte!=0){
					ok = false;
				}
			}else{
				ok = false;
			}
		}else if(ansCompte>1){
			ok = false;
		}
		return ok; // 32
	}
	/**
	 * retourne 
	 * @param uneDateChaine
	 * @return uneDate
	 */
	public Date extraireChaine(String uneDateChaine){
		int i;
		Date maDate = null;
		maDate = new Date(uneDateChaine);
		i=0;
		while(i<this.lesDates.size() && this.lesDates.get(i).getMoisEntier()!=maDate.getMoisEntier()){
			i++;
		}
		if(i<this.lesDates.size()){
			maDate = this.lesDates.get(i);
		}
		return maDate;
	}
	
	/**
	 * choper une datre dans la collection
	 * @param i l'indice de la date
	 * @return un objet date
	 */
	public Date extraireUneDate(int i){
		
		return this.lesDates.get(i);
	}
	/**
	 * recheche une date a partir du parametre uneDateChaine(Chaine)
	 * @param uneDateChaine
	 * @return l'indice de la classe date
	 */
	public int rechercheDateEnEntier(String uneDateChaine){
		int i;
		Date maDate;
		maDate = new Date(uneDateChaine);
		i=0;
		while(i<this.lesDates.size() && this.lesDates.get(i).getMoisEntier()!=maDate.getMoisEntier()){
			i++;
		}
		if(i==this.lesDates.size()){
			i = -1;
		}
	
		return i;
	}
	/**
	 * procedure qui ajoute une date entier
	 * @param uneDate en entier
	 */
	public void ajoutDateEntier(int uneDate){
		if(this.rechercheDate(uneDate)==-1){
			lesDates.add(new Date(uneDate));
			
		}
	}
	/**
	 * fonction qui capture une date
	 * @param uneDate
	 * @return une Date
	 */
	public Date captureDateInt(int uneDate){
		Date maDate;
		
		this.ajoutDateEntier(uneDate);
		maDate = this.extraireUneDate(this.rechercheDate(uneDate));
		
		return maDate;
	}
	/**
	 * procedure qui ajoute une date en Chaine ou le creer
	 * @param uneDate en chaine
	 */
	public void ajoutDateString(String uneDate){
		//rechercheUnMoisChaineCaractere
		int indiceDate = this.rechercheDateEnEntier(uneDate);
		Date maDate;
		
		if(indiceDate!=-1){
			maDate = this.extraireUneDate(indiceDate); //récuperation de l'indice objet date
			maDate.ajoutDateEnChaine(uneDate);
		}else{
			lesDates.add(new Date(uneDate));
		}
	}
	/**
	 * recherche une date puis capture la date en question ou encore il ajoute si il nexiste pas.
	 * @param dateChaine
	 * @return Date
	 */
	public Date captureDateString(String dateChaine){
		int i = 0;
		Date rechercheDate, maDate;
		dateChaine = dateChaine.replace("/", "-");
		rechercheDate = new Date(dateChaine);
		
		while(i<this.lesDates.size() && this.lesDates.get(i).getMoisEntier()!=rechercheDate.getMoisEntier()){
			i=i+1;
		}
		if(i<this.lesDates.size()){
			maDate = this.extraireUneDate(i); //récuperation de l'indice objet date
			maDate.ajoutDateEnChaine(dateChaine);
			rechercheDate = maDate;
		}else{
			lesDates.add(rechercheDate);
		}
		return rechercheDate;
	}
	
}
