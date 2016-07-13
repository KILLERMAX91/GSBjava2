package Class;

public class LigneFraisHorsForfait {
	private int num;
	private float montant;
	private String libelle;
	private Date uneDate;
	private Etat uneEtat;
	private int indiceDate;
	public LigneFraisHorsForfait(int unNum, float unMontant, String unLibelle, Date laDate, Etat unEtat, String dateChaine){
		this.num = unNum;
		this.libelle = unLibelle;
		this.montant = unMontant;
		this.uneDate = laDate;
		this.uneEtat = unEtat;
		this.indiceDate =  this.uneDate.rechercheChaineIndice(dateChaine);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public Date getUneDate() {
		return uneDate;
	}
	public void setUneDate(Date uneDate) {
		this.uneDate = uneDate;
	}
	public Etat getUneEtat() {
		return uneEtat;
	}
	public void setUneEtat(Etat uneEtat) {
		this.uneEtat = uneEtat;
	}
	public int getIndiceDate() {
		return indiceDate;
	}
	public void setIndiceDate(int indiceDate) {
		this.indiceDate = indiceDate;
	}
	public String toString(){
		return this.libelle;
	}
}
