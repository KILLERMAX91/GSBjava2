
import static org.junit.Assert.*;

import java.text.Normalizer;

import org.junit.Before;
import org.junit.Test;

import Include.Modele;
import junit.framework.Assert;


public class ModeleTest {
	private Modele leModele;
	
	
	@Before
	public void setUp() throws Exception {
		this.leModele = new Modele();
		this.leModele.connexion();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void cryptTest(){
		Assert.assertEquals("Le cryptage ne fonctionnent pas pour (123456)","e10adc3949ba59abbe56e057f20f883e", this.leModele.crypt("123456"));
		
		
	}
	@Test
	public void connectComptableTest(){
		String[] tab = leModele.connectComptable("agest", "123456");
		Assert.assertEquals("Le connection du comptable a foir�","Gest", tab[1]);
	}
}
