package Class;

public class LigneFraisForfait {
	private int quantite;
	private FraisForfait unFraisForfait;
	private Etat unEtat;
	public LigneFraisForfait(int uneQuantite, FraisForfait leFraisForfait, Etat uneEtat){
		this.quantite = uneQuantite;
		this.unFraisForfait = leFraisForfait;
		this.unEtat = uneEtat;
	}
	public int getQuantite(){
		return this.quantite;
	}
	public Etat getUnEtat(){
		return this.unEtat;
	}
	public float montant(){
		return quantite*unFraisForfait.getMontant();
	}
	public String toString(){
		return this.quantite+"";
	}
}
