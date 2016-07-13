package Class;

public class Etat {
	private String numEtat;
	private String libelle;
	public Etat(String unNumEtat, String uneLibelle){
		this.numEtat = unNumEtat;
		this.libelle = uneLibelle;
	}
	public String getNumEtat() {
		return numEtat;
	}
	public void setNumEtat(String numEtat) {
		this.numEtat = numEtat;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String toString(){
		return this.libelle;
	}
}
