package ili.jai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ili.jai.model.Promotion;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestJDBC1 {

	private ServicePersistance sp;
	private PersistancePromotion sppromo;
	
	abstract protected ServicePersistance getServicePersistance();

	@Before
	public void init() {
		sp = getServicePersistance();
		sppromo = sp.servicePersistancePromotion();
	}

	@Test
	public void test01CreateTables() throws SQLException {
		assertTrue(sp.creerTables());
	}

	@Test
	public void test02CreationPromotions() throws SQLException {
		assertTrue(sppromo.tous().isEmpty());
		Promotion jupiter = sppromo.persiste(2005, "Jupiter");
		assertEquals("Jupiter", jupiter.getNom());
		assertEquals(2005, jupiter.getAnnee());
		assertEquals(1, sppromo.tous().size());
		Promotion callisto = sppromo.persiste(2006, "Callisto");
		assertEquals("Callisto", callisto.getNom());
		assertEquals(2006, callisto.getAnnee());
		List<Promotion> promos = sppromo.tous();
		assertEquals(2, promos.size());
		assertTrue(promos.contains(jupiter));
		assertTrue(promos.contains(callisto));
	}

	@Test(expected=IllegalArgumentException.class)
	public void test03PromosAvant2005() throws SQLException {
		sppromo.persiste(2003, "Les mangeurs de quiche");
	}
	
	@Test
	public void test04PromosDepuis() throws SQLException {
		sppromo.persiste(2007, "Europa");
		sppromo.persiste(2008, "Ganymede");
		sppromo.persiste(2009, "Galileo");
		sppromo.persiste(2010, "Helios");
		sppromo.persiste(2011, "Indigo");
		sppromo.persiste(2012, "Juno");
		sppromo.persiste(2013, "Kepler");
		sppromo.persiste(2014, "Luna");
		sppromo.persiste(2015, "Mars");
		sppromo.persiste(2016, "Neon");
		assertEquals(12,sppromo.tous().size());
		assertEquals(0,sppromo.toutesDepuis(2017).size());
		assertEquals(3,sppromo.toutesDepuis(2014).size());
		assertEquals(12,sppromo.toutesDepuis(2005).size());
	}
	
	@Test
	public void test99EffaceTables() throws SQLException {
		assertTrue(sp.effacerTables());
	}
}
