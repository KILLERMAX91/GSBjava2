package Class;

public class Personne {
	protected String numPersonne;
	protected String nom;
	protected String prenom;
	protected String adresse;
	protected String CP;
	protected String ville;
	protected String mail;
	protected String tel;
	protected int indiceDate;
	protected Date unEmbauche;
	public String getNumPersonne() {
		return numPersonne;
	}
	public void setNumPersonne(String numPersonne) {
		this.numPersonne = numPersonne;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCP() {
		return CP;
	}
	public void setCP(String cP) {
		CP = cP;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getIndiceDate() {
		return indiceDate;
	}
	public void setIndiceDate(int indiceDate) {
		this.indiceDate = indiceDate;
	}
	public Date getUnEmbauche() {
		return unEmbauche;
	}
	public void setUnEmbauche(Date unEmbauche) {
		this.unEmbauche = unEmbauche;
	}
	
}
