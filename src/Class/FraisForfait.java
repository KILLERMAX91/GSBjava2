package Class;

public class FraisForfait {
	private String numFraisForfaits;
	private String libelle;
	private float montant;
	
	public FraisForfait(String unNumFraisForfaits, String uneLibelle, float unMontant){
		this.numFraisForfaits = unNumFraisForfaits;
		this.libelle = uneLibelle;
		this.montant = unMontant;
	}
	public String getNumFraisForfaits() {
		return numFraisForfaits;
	}
	public void setNumFraisForfaits(String numFraisForfaits) {
		this.numFraisForfaits = numFraisForfaits;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
}
