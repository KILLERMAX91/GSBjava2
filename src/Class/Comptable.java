package Class;


public class Comptable extends Personne{
	//HÉRITAGE
	public Comptable(String numPersonne, String unNom, String unPrenom, String unAdresse, String unCP, String uneVille, String unMail, String unTel, Date uneDate, String dateChaine){
		this.numPersonne = numPersonne;
		this.nom = unNom;
		this.prenom = unPrenom;
		this.adresse = unAdresse;
		this.CP = unCP;
		this.ville = uneVille;
		this.mail = unMail;
		this.tel = unTel;
		this.unEmbauche = uneDate;
		this.indiceDate = this.unEmbauche.rechercheChaineIndice(dateChaine); //capture l'indice de la date enregistrer
	}
}
