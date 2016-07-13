package Class;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
public class Date {
	private int moisEntier;
	private ArrayList<String> moisChaine;
	private SimpleDateFormat sdf;
	private java.util.Date uDate;
	/**
	 * constructeur entier
	 * @param uneDate
	 */
	public Date(int uneDate){
		this.moisChaine = new ArrayList<String>();
		this.moisChaine.add(this.convertirEntierEnChaine(uneDate));
		this.moisEntier = uneDate;
	}
	/**
	 * vérifier si la date existe
	 * <br/>format yyyy/MM/dd
	 * @param uneDate
	 * @param format
	 * @return un booleen
	 */
	public boolean verifDateExist(String uneDate, String format){
		boolean exist = true;
		this.sdf = new SimpleDateFormat(format);
		try {
			this.uDate = this.sdf.parse(uneDate);
			//System.out.println(uDate);
		} catch (ParseException e) {
			exist = false;
		}
		return exist;
	}
	/**
	 * constructeur en chaine de caractere
	 * @param uneDate
	 */
	public Date(String uneDate){
		this.moisChaine = new ArrayList<String>();
		this.moisChaine.add(uneDate);
		this.moisEntier = this.convertirChaineEnEntier(uneDate);
	}
	/**
	 * convertisseur du mois en entier en chaine de caractere
	 * @param unMoisEntier
	 * @return le mois en chaine de caractére ex: 201510 ===> 2015-10-01
	 */
	public String convertirEntierEnChaine(int unMoisEntier){
		String moisEnChaine, chaine;
		moisEnChaine = Integer.toString(unMoisEntier);
		//System.out.print("===>"+moisEnChaine);
		if(new String(""+unMoisEntier).length()==6){
			chaine = moisEnChaine.substring(0,4)+"-"+moisEnChaine.substring(4,6)+"-01";
		}else{
			chaine = moisEnChaine.substring(0,4)+"-"+"0"+moisEnChaine.substring(4,5)+"-01";
		}
		
		return chaine;
	}
	/**
	 * convertisseur du mois en chaine de caractere en entier 
	 * @param unMoisChaine
	 * @return le mois en entier ex: 2015-10-01 ===> 201510
	 */
	public int convertirChaineEnEntier(String unMoisChaine){
		int moisEnEntier;
		String[] tab;
		tab = unMoisChaine.split("-");
		if(tab.length == 0){
			tab = unMoisChaine.split("/");
			
		}
		
		moisEnEntier = Integer.parseInt(tab[0]+""+tab[1]);
		return moisEnEntier;
	}
	/**
	 * 
	 * @return le mois en entier
	 */
	public int getMoisEntier() {
		return moisEntier;
	}
	/**
	 * 
	 * @param moisEntier
	 */
	public void setMoisEntier(int moisEntier) {
		this.moisEntier = moisEntier;
	}
	/**
	 * 
	 * @return date en mois
	 */
	public String getMoisFrancais(){
		String[] tabMois = {"Janvier", "Février", "Mars", "Avril", "Mais", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
		String moisEnChaine;
		moisEnChaine = Integer.toString(this.moisEntier);
		return tabMois[Integer.parseInt(moisEnChaine.substring(4,6))-1];
	}
	/**
	 * 
	 * @return collection de chaine
	 */
	public ArrayList<String> getMoisChaine() {
		return moisChaine;
	}
	/**
	 * 
	 * @param moisChaine
	 */
	public void setMoisChaine(ArrayList<String> moisChaine) {
		this.moisChaine = moisChaine;
	}
	/**
	 * Verifier si le mois existe
	 * @param unMoisChaine
	 * @return un booleen 
	 */
	public boolean rechercheUnMoisChaineCaractere(String unMoisChaine){
		boolean trouver = false;
		int i=0;
		while(i<this.moisChaine.size() && !this.moisChaine.get(i).equals(unMoisChaine)){
			i++;
		}
		if(i<this.moisChaine.size()){
			trouver = true;
		}
		return trouver;
	}
	/**
	 * 
	 * @param uneDateChaine
	 * @return indice moisChaine
	 */
	public int rechercheChaineIndice(String uneDateChaine){
		int i=0;
		while(i<this.moisChaine.size() && !this.moisChaine.get(i).equals(uneDateChaine)){
			i++;
		}
		if(i==this.moisChaine.size()){
			i = -1; //date enixistant
		}
		return i;
	}
	/**
	 * permet d'ajouter une date
	 * @param maDate
	 */
	public void ajoutDateEnChaine(String maDate){
		if(!this.rechercheUnMoisChaineCaractere(maDate)){
			this.moisChaine.add(maDate);
		}
		
	}
	public String dateFR(int i){
		String date = this.moisChaine.get(i);
		String[] dateCoupe =date.split("-");
		return dateCoupe[2]+"-"+dateCoupe[1]+"-"+dateCoupe[0];
	}
	/**
	 * return toString
	 */
	public String toString(){
		String chaine="";
		chaine+=getMoisEntier();
		return chaine;
	}
}
