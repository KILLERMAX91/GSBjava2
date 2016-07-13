import static org.junit.Assert.*;

import org.junit.*;

import Class.Date;
import Controler.SousControllerDate;

public class DateTest {
	
	private Date uneDate;	
	private SousControllerDate dateController;
	@Before
	public void setUp() throws Exception {
		uneDate = new Date(201511);
		dateController = new SousControllerDate();
	}
	
	@Test
	public void ConvertirEntierEnChaineTest() {
		Assert.assertEquals("Le format n'est pas celui demandé","2015-11-01", uneDate.convertirEntierEnChaine(uneDate.getMoisEntier()));
	
	}
	@Test
	public void  getMoisFrancaisTest(){
		Assert.assertEquals("Le format de la date en français n'est pas valide","Novembre", uneDate.getMoisFrancais());
	}
	@Test
	public void verifDateExistTest(){
		Assert.assertEquals("Le format est valide", true, uneDate.verifDateExist("2015-11-01", "yyyy-MM-dd"));
	}
	/*@Test
	public void ConvertirEntierEntierTest(){
		
	}*/
	@Test
	public void dateEntierTodaysTest(){
		Assert.assertEquals("Le format est valide", 201511, dateController.dateEntierTodays());
	}
	@Test
	public void dateEntierHorsFraisTest(){
		//dateEntierHorsFrais
		Assert.assertEquals("Le format est valide", 201512, dateController.dateEntierHorsFrais());
	}
	@Test
	public void isValidTest(){
		Assert.assertEquals("Le format est valide", true, dateController.isValid("01/12/2015"));
	}
	@Test
	public void differenceTest(){
		Assert.assertEquals("Le format est valide", true, dateController.difference("11/12/2014"));
	}
	/*
	
	@Test
	public void testRechercheUnMoisChaineCaractere() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAjoutDateEnChaine() {
		fail("Not yet implemented");
	}*/	

}
