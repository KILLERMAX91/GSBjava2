package Include;
import java.security.*;
import java.sql.*;
import java.util.ArrayList;

import Class.Etat;
import Class.FicheFrais;
import Class.FraisForfait;
import Class.Visiteur;
import Controler.Controler;

public class Modele {
	private Connection uneConnexion;
	private Statement st;
	public  Modele(){ 
		
	}
	public  void connexion(){ // Connexion au driver + a� la BDD
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.uneConnexion = DriverManager.getConnection("jdbc:mysql://localhost/gsb_frais", "root", "");
			this.st = uneConnexion.createStatement();
			
		}catch (SQLException e) {
			 System.out.println("problème de connexion à la base...");
		}catch(ClassNotFoundException e){
			
		}
	}
	/**
	 * vérification login du comptable + réupération des élément du comptable
	 * la requete sera préperer afin de prévoir contre les injections SQL
	 * @param unLogin
	 * @param mdp (mot de passe)
	 * @return un tableau de String avec les valeur du comptable
	 */
	public String[] connectComptable(String unLogin, String mdp){
		String[] info = new String[9];
		PreparedStatement preparation;
		ResultSet rs;
		try {
			preparation = this.uneConnexion.prepareStatement("SELECT id, nom, prenom, adresse, cp, ville, mail, dateEmbauche, tel " +
					"FROM Visiteur " +
					"WHERE login = ? " +
					"AND mdp = ? " +
					"AND typeClient='comptable';"); //requéte préparré
			preparation.setString(1, unLogin); //premiere paramétre
			preparation.setString(2, this.crypt(mdp)); //deuxiéme paramétre + avec cryptage
			rs = preparation.executeQuery(); //éxécution
			
			
			rs.next();
			info[0] = rs.getString("id");
			info[1] = rs.getString("nom");
			info[2] = rs.getString("prenom");
			info[3] = rs.getString("adresse");
			info[4] = rs.getString("cp");
			info[5] = rs.getString("ville");
			info[6] = rs.getString("mail");
			info[7] = rs.getString("dateEmbauche");
			info[8] = rs.getString("tel");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			info[1] = e.getMessage();
			//System.out.print("==>"+e.printStackTrace());
			//e.printStackTrace();
				
		}
		
		return info;
	}
	/**
	 * r�cuparation de tous les visteurs
	 * @return ArrayList<Visiteur>
	 */
	public ArrayList<String[]> recupererVisiteur(){
		ArrayList<String[]> lesInfosVisiteurs = new ArrayList<String[]>();
		String[] info = new String[9];
		PreparedStatement preparation;
		ResultSet rs;
		try {
			preparation = this.uneConnexion.prepareStatement("SELECT id, nom, prenom, adresse, cp, ville, mail, dateEmbauche, tel " +
					"FROM Visiteur " +
					"WHERE typeClient='Visiteur';"); //requéte préparré
		
			rs = preparation.executeQuery(); //éxécution
			
			
			while(rs.next()){
				info = new String[9];
				info[0] = rs.getString("id");
				info[1] = rs.getString("nom");
				info[2] = rs.getString("prenom");
				info[3] = rs.getString("adresse");
				info[4] = rs.getString("cp");
				info[5] = rs.getString("ville");
				info[6] = rs.getString("mail");
				info[7] = rs.getString("dateEmbauche");
				info[8] = rs.getString("tel");
				
				lesInfosVisiteurs.add(info);
				
			}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			info[1] = e.getMessage();
			e.printStackTrace();
				
		}
		
		return lesInfosVisiteurs;
	}
	/**
	 * fonction qui recuper tous les Etats
	 * @return collection Etat
	 */
	public ArrayList<Etat> recupererEtat(){
		ArrayList<Etat> lesEtats = new ArrayList<Etat>();
		PreparedStatement preparation;
		ResultSet rs;
		try {
			preparation = this.uneConnexion.prepareStatement("SELECT * FROM Etat;");
			rs = preparation.executeQuery();
			while(rs.next()){
				lesEtats.add(new Etat(rs.getString("id"), rs.getString("libelle")));
			}
		} catch (SQLException e) {
			
		}
		return lesEtats;
	}
	/**
	 * fonction qui recupere tous les FraisForfaits
	 * @return collection FraisForfait
	 */
	public ArrayList<FraisForfait> recupereFraisForfait(){
		ArrayList<FraisForfait> lesFraisForfait = new ArrayList<FraisForfait>();
		PreparedStatement preparation;
		ResultSet rs;
		try {
			preparation = this.uneConnexion.prepareStatement("SELECT * FROM FraisForfait;");
			rs = preparation.executeQuery();
			while(rs.next()){
				lesFraisForfait.add(new FraisForfait(rs.getString("id"), rs.getString("libelle"), rs.getFloat("libelle")));
			}
		} catch (SQLException e) {
			
		}
		return lesFraisForfait;
	}
	/**
	 * recuperation des fiches de frais
	 * @return une collection d'objet avec les valeur des fiche de frais
	 */
	public ArrayList<Object[]> recupererFicheFraisVisiteur(){
		ArrayList<Object[]> lesInfos = new ArrayList<Object[]>();
		Object[] uneInfo = new Object[6];
		PreparedStatement preparation;
		ResultSet rs;
		try {
			preparation = this.uneConnexion.prepareStatement("SELECT * FROM FicheFrais;");
			rs = preparation.executeQuery();
			while(rs.next()){
				uneInfo = new Object[6];
				uneInfo[0] = rs.getString("idVisiteur");
				uneInfo[1] = rs.getInt("mois");
				uneInfo[2] = rs.getInt("nbJustificatifs");
				uneInfo[3] = rs.getFloat("montantValide");
				uneInfo[4] = rs.getString("dateModif");
				uneInfo[5] = rs.getString("idEtat");
				lesInfos.add(uneInfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesInfos;
	}
	/**
	 * recuperation des lignes hors frais
	 * @return lesHorsFrais
	 */
	public ArrayList<FraisForfait> recupererFraisForfait(){
		ArrayList<FraisForfait> lesFraisForfaits = new ArrayList<FraisForfait>();
		PreparedStatement preparation;
		ResultSet rs;
		try {
			preparation = this.uneConnexion.prepareStatement("SELECT * FROM FraisForfait;");
			rs = preparation.executeQuery();
			while(rs.next()){
				lesFraisForfaits.add(new FraisForfait(rs.getString("id"), rs.getString("libelle"), rs.getFloat("montant")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lesFraisForfaits;
	}
	/**
	 * recupere les LigneFraisForfaits d'un visiteur
	 * @param unLogin
	 * @param mois
	 * @return une collection d'objet avec les valeur des factures
	 */
	public ArrayList<Object[]> recupererLigneFraisForfait(String unLogin, String mois){
		ArrayList<Object[]> lesInfos = new ArrayList<Object[]>();
		Object[] uneInfo = new Object[3];
		PreparedStatement preparation;
		ResultSet rs;
		try{
			preparation = this.uneConnexion.prepareStatement("SELECT idFraisForfait, quantite, idEtat FROM LigneFraisForfait WHERE idVisiteur=? AND mois=?;");
			preparation.setString(1, unLogin);
			preparation.setString(2, mois);
			rs = preparation.executeQuery();
			while(rs.next()){
				uneInfo = new Object[3];
				uneInfo[0] = rs.getString("idFraisForfait");
				uneInfo[1] = rs.getString("quantite");
				uneInfo[2] = rs.getString("idEtat");
				lesInfos.add(uneInfo);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return lesInfos;
	}
	/*Cette Methode permet d'obtenir les Fiches de Frais Hors Forfait pour leur affichage*/
	public ArrayList<Object[]> recupererLigneFraisHorsForfait(String unLogin, String mois){
		ArrayList<Object[]> lesInfos = new ArrayList<Object[]>();
		Object[] uneInfo = new Object[5];
		PreparedStatement preparation;
		ResultSet rs;
		try{
			preparation = this.uneConnexion.prepareStatement("SELECT id, libelle, date, montant, idEtat FROM LigneFraisHorsForfait WHERE idVisiteur=? AND mois=?;");
			preparation.setString(1, unLogin);
			preparation.setString(2, mois);
			rs = preparation.executeQuery();
			while(rs.next()){
				uneInfo = new Object[5];
				uneInfo[0] = rs.getInt("id");
				uneInfo[1] = rs.getString("libelle");
				uneInfo[2] = rs.getString("date");
				uneInfo[3] = rs.getFloat("montant");
				uneInfo[4] = rs.getString("idEtat");
				lesInfos.add(uneInfo);
			}
		}catch(SQLException e){
			
		}
		return lesInfos;
	}

	public void creeNouvellesFicheFrais(Visiteur visit, int mois, ArrayList<FraisForfait> lesFRais){
		PreparedStatement preparation;
		try {
			
			preparation = this.uneConnexion.prepareStatement("INSERT INTO FicheFrais(idvisiteur,mois,nbJustificatifs,montantValide,dateModif,idEtat) VALUES (?, ?,0,0,now(),'CR')");
			preparation.setString(1, visit.getNumPersonne());
			preparation.setString(2, String.valueOf(mois));
			preparation.executeUpdate();
			
			
			for (FraisForfait unFrais : lesFRais){
				
				//$req = "insert into lignefraisforfait(idvisiteur,mois,idFraisForfait,quantite) values('$idVisiteur','$mois','$unIdFrais',0)";
				preparation = null;
				preparation = this.uneConnexion.prepareStatement("insert into LigneFraisForfait(idvisiteur, mois, idFraisForfait, quantite, idEtat) values(?, ?, ?,0, 'CR')");
				preparation.setString(1, visit.getNumPersonne());
				preparation.setString(2, String.valueOf(mois));
				preparation.setString(3, unFrais.getNumFraisForfaits());
				preparation.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ajoutHorsFrais(String idVisiteur, int mois, String libelle, String date, float montant, String idEtat){
		PreparedStatement preparation;
		try {
			preparation = this.uneConnexion.prepareStatement("INSERT INTO LigneFraisHorsForfait(idVisiteur, mois, libelle, date, montant, idEtat) VALUES (?, ?, ?, ?, ?, ?)");
			preparation.setString(1, idVisiteur);
			preparation.setString(2, String.valueOf(mois));
			preparation.setString(3, libelle);
			preparation.setString(4, date);
			preparation.setFloat(5, montant);
			preparation.setString(6, idEtat);
			preparation.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public void validFacture(Object[] ficheFrais, Object[] ficheHorsFrais, FicheFrais ficheEnCours, Controler leControle, Etat etatFicheFrais, String dateFicheFrais){
		PreparedStatement preparation;
		int i;
		float montanTotal = 0;
		int nbrJustificatif = 0;
		int[] montant = (int[]) ficheFrais[1];
		Etat unEtat;
		String[] lesLibelles = (String[]) ficheHorsFrais[1];
		String[] lesDates = (String[]) ficheHorsFrais[2];
		float[] lesMontant = (float[]) ficheHorsFrais[3];
		Etat[] lesEtats = (Etat[]) ficheHorsFrais[4];
		try{
			//fiche frais
			for(i=0;i<ficheEnCours.tailleFrais();i++){
				preparation = this.uneConnexion.prepareStatement("UPDATE LigneFraisForfait SET quantite = ?, idEtat = ? WHERE idFraisForfait = ? AND mois = ? AND idVisiteur = ?");
				preparation.setInt(1, montant[i]);
				unEtat = (Etat) ficheFrais[2];
				preparation.setString(2, (String) unEtat.getNumEtat());
				preparation.setString(3, leControle.getCFraisForfait().extraire(i).getNumFraisForfaits());
				preparation.setString(4, ""+ficheEnCours.getCleDate().getMoisEntier());
				preparation.setString(5, ""+ficheEnCours.getUnVisiteur().getNumPersonne());
				preparation.executeUpdate();
				montanTotal = montanTotal + (montant[i]*leControle.getCFraisForfait().extraire(i).getMontant());
			}
			//fiche hors frais
			for(i=0;i<ficheEnCours.tailleFraisHorsForfait();i++){
				//System.out.print("==>"+ficheEnCours.extraireFraisHorsForfait(i).getNum());
				preparation = this.uneConnexion.prepareStatement("UPDATE LigneFraisHorsForfait SET libelle = ?, date = ?, montant = ?, idEtat = ? WHERE id = ?");
				preparation.setString(1, lesLibelles[i]);
				preparation.setString(2, (String) leControle.getDate().dateFrEndateEnglais(lesDates[i])[1]);
				preparation.setFloat(3, lesMontant[i]);
				if(!lesEtats[i].getNumEtat().equals("PR")){
					montanTotal = montanTotal + lesMontant[i];
					nbrJustificatif++;
				}
				preparation.setString(4, lesEtats[i].getNumEtat());
				preparation.setInt(5, ficheEnCours.extraireFraisHorsForfait(i).getNum());
				preparation.executeUpdate();
			}
			preparation = this.uneConnexion.prepareStatement("UPDATE FicheFrais SET nbJustificatifs=?, montantValide=?, dateModif=?, idEtat=? WHERE mois = ? AND idVisiteur = ?");
			//Etat etatFicheFrais, String dateFicheFrais
			preparation.setInt(1, nbrJustificatif);
			preparation.setFloat(2, montanTotal);
			preparation.setString(3, dateFicheFrais);
			preparation.setString(4, etatFicheFrais.getNumEtat());
			preparation.setString(5, ""+ficheEnCours.getCleDate().getMoisEntier());
			preparation.setString(6, ""+ficheEnCours.getUnVisiteur().getNumPersonne());
			preparation.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.printStackTrace();
		}
	}
	/** 
	 * la fonction crypt permet de crypter le mot passe
	 * @param secret
	 * @return un mot qui est crypte en MD5
	 */
	public String crypt(String secret){
		String original = secret;
		MessageDigest md;
		StringBuffer sb; //String mais sous format object
		byte[] digest; //Bytecode c'est un code binaire 
		sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5"); //message algoriththique
			md.update(original.getBytes());
			digest = md.digest();
			
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sb.append(""); //gain temps avec StringBuffer avec String
		}
		return sb.toString(); //reoutourne le format  du tableau;
	}
	public void deconnexion() {
		// Fermeture de la connexion � la BDD
		try {
			uneConnexion.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
}
