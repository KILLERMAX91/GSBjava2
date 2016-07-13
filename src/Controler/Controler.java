package Controler;

import Include.Modele;
import java.util.ArrayList;
import Class.Comptable;
import Include.Modele;
import Vue.VueConnecte;
import Vue.VueLogin;

public class Controler {
	//attribut priv��
	private Comptable unComptable; //le comptable
	private Modele leModele; //
	private SousControllerDate date;
	private SousControllerPersonne CVPersonne;
	private VueLogin login;
	private SousControllerEtat CE;
	private SousControllerFacture CFF;
	private SousControllerFraisForfait CFraisForfait;
	private SousControleHorsFrais CHF;
	//controler
	public Controler(){
		this.leModele = new Modele();
		this.date = new SousControllerDate();
		this.CVPersonne = new SousControllerPersonne(this); //permet que SousControllerPersonne puisse appeler les controle
		//this.login = new VueLogin(this);
		this.CVPersonne.login();
		this.CE = new SousControllerEtat(this);
		this.CFF = new SousControllerFacture(this);
		this.CFraisForfait = new SousControllerFraisForfait(this);
		this.CHF = new SousControleHorsFrais(this);
	}
	public Controler(String test){
		this.leModele = new Modele();
		this.date = new SousControllerDate();
		this.CVPersonne = new SousControllerPersonne(this); //permet que SousControllerPersonne puisse appeler les controle
		//this.login = new VueLogin(this);
		//this.CVPersonne.login();
		this.CE = new SousControllerEtat(this);
		this.CFF = new SousControllerFacture(this);
		this.CFraisForfait = new SousControllerFraisForfait(this);
		this.CHF = new SousControleHorsFrais(this);
	}
	public SousControleHorsFrais getCHF() {
		return CHF;
	}
	public void setCHF(SousControleHorsFrais cHF) {
		CHF = cHF;
	}
	public SousControllerFraisForfait getCFraisForfait(){
		return this.CFraisForfait;
	}
	public SousControllerFacture getCFF() {
		return CFF;
	}
	public SousControllerEtat getCE() {
		return CE;
	}
	public SousControllerDate getDate(){
		return this.date;
	}
	public VueLogin getLogin(){
		return this.login;
	}
	public void setLogin(VueLogin log){
		this.login = log;
	}
	public Modele getLeModele(){
		return this.leModele;
	}
	public Comptable getUnComptable() {
		return unComptable;
	}
	public SousControllerPersonne getCVPersonne() {
		return this.CVPersonne;
	}
}
