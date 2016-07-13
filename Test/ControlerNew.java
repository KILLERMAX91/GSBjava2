import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Controler.Controler;


public class ControlerNew {
	private Controler leControle;
	
	@Before
	public void setUp() throws Exception {
		leControle = new Controler();
	}

	
	@Test
	public void connecte(){
	
		Assert.assertEquals("le comptable est perdue", true, this.leControle.getCVPersonne().connectComptableBDD("agest", "123456"));
	}
	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}
