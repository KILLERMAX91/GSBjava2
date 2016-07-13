import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Controler.Controler;


public class ControlerTest {
	private Controler leControle;
	@Before
	public void setUp() throws Exception {
		leControle = new Controler();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void connectComptableBDDTest(){
		//connectComptableBDD
		//Assert.assertEquals("Le comptable est mort", true, leControle.connectComptableBDD("agest", "123456"));
	}
}
