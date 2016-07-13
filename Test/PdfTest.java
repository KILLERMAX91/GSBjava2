import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Class.FicheFrais;
import Controler.Controler;
import Controler.SousControllerDate;
import Vue.Pdf;


public class PdfTest {
	private Pdf lePdf;
	private Controler leController;
	@Before
	public void setUp() throws Exception {
		this.leController = new Controler("");
		this.leController.getLeModele().connexion();
		this.leController.getCVPersonne().callVisiteur();
		this.leController.getCE().allEtat();
		this.leController.getCFraisForfait().allFraisForfait();
		this.leController.getCFF().allFicheFrais();
		this.leController.getCVPersonne().connectComptableBDD("agest", "123456");
		this.leController.getLeModele().deconnexion();
		
		this.lePdf = new Pdf(this.leController, this.leController.getCFF().extraire(0));
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		
	}

}
