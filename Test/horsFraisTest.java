import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Controler.*;


public class horsFraisTest {
	private SousControleHorsFrais horsFraisController;
	private Controler unControle;
	@Before
	public void setUp() throws Exception {
		unControle = new Controler();
		horsFraisController = new SousControleHorsFrais(unControle);
	}

	
	@Test
	public void ajoutDateHorsFraisTest(){
		Assert.assertEquals("Le format est valide", true, (boolean) horsFraisController.ajoutDateHorsFrais("01/11/2015")[0]); //attention tableau
	}
	
}
