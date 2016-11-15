package ili.jai;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ili.jai.model.Ancien;
import ili.jai.model.Entreprise;
import ili.jai.model.Promotion;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestJDBC3 {

	private ServicePersistance sp;
	private PersistanceAncien spancien;
	private PersistancePromotion sppromo;
	private PersistanceEntreprise spentreprise;

	private Promotion neon;
	private Promotion europa;

	private Entreprise nextoo;
	private Entreprise gfi;

	protected abstract ServicePersistance getServicePersistance();

	@Before
	public void init() throws SQLException {
		sp = getServicePersistance();
		spancien = sp.servicePersistanceAncien();
		sppromo = sp.servicePersistancePromotion();
		spentreprise = sp.servicePersistanceEntreprise();
	}

	@Test
	public void test01CreateTables() throws SQLException {
		assertTrue(sp.creerTables());
		neon = sppromo.persiste(2015, "Neon");
		assertNotNull(neon);
		europa = sppromo.persiste(2007, "Europa");
		assertNotNull(europa);
		nextoo = spentreprise.persiste("Nextoo", "59100", "Roubaix");
		assertNotNull(nextoo);
		gfi = spentreprise.persiste("GFI", "59000", "Lille");
		assertNotNull(gfi);
	}

	@Test
	public void test02CreateAncien() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		gfi = spentreprise.findByName("GFI");
		spancien.persiste("Jean", "Letudiant", neon, gfi, nextoo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test03CreateAncienWithPrenomNull() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		spancien.persiste(null, "Letudiant", neon, Entreprise.NONE, nextoo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test03CreateAncienWithNomNull() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		spancien.persiste("Jean", null, neon, Entreprise.NONE, nextoo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test03CreateAncienWithPromoNull() throws SQLException {
		nextoo = spentreprise.findByName("Nextoo");
		spancien.persiste("Jean", "Letudiant", null, Entreprise.NONE, nextoo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test03CreateAncienWithM1Null() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		spancien.persiste("Jean", "Letudiant", neon, null, nextoo);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test03CreateAncienWithM2Null() throws SQLException {
		spancien.persiste("Jean", "Letudiant", neon, Entreprise.NONE, null);
	}

	@Test
	public void test04CreateAncienWithNONE() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		spancien.persiste("Jean", "Letudiant", neon, Entreprise.NONE, nextoo);
	}

	@Test
	public void test05CreateAncienWithCRIL() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		spancien.persiste("Jean", "Letudiant", neon, Entreprise.CRIL, nextoo);
	}

	@Test
	public void test06AjoutEntrepriseDansCarriere() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		gfi = spentreprise.findByName("GFI");
		Ancien ancien = spancien.persiste("Jean", "Letudiant", neon, Entreprise.NONE, nextoo);
		assertTrue(ancien.getCarriere().isEmpty());
		assertThat(ancien.getEntrepriseActuelle(), is(Entreprise.NONE));
		ancien.addEntreprise(nextoo);
		assertFalse(ancien.getCarriere().isEmpty());
		assertThat(ancien.getEntrepriseActuelle(), is(nextoo));
		ancien.addEntreprise(gfi);
		assertThat(ancien.getEntrepriseActuelle(), is(gfi));
		List<Entreprise> carriere = ancien.getCarriere();
		assertThat(carriere.size(), is(2));
		assertThat(carriere.get(0), is(nextoo));
		assertThat(carriere.get(1), is(gfi));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test07NePasAjouterDirectementEntrepriseDansCarriereVide() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		gfi = spentreprise.findByName("GFI");
		Ancien ancien = spancien.persiste("Jean", "Letudiant", neon, Entreprise.NONE, nextoo);
		assertTrue(ancien.getCarriere().isEmpty());
		ancien.getCarriere().add(nextoo);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void test08NePasAjouterDirectementEntrepriseDansCarriereNonVide() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		gfi = spentreprise.findByName("GFI");
		Ancien ancien = spancien.persiste("Jean", "Letudiant", neon, Entreprise.NONE, nextoo);
		assertTrue(ancien.getCarriere().isEmpty());
		ancien.addEntreprise(nextoo);
		assertFalse(ancien.getCarriere().isEmpty());
		assertThat(ancien.getEntrepriseActuelle(), is(nextoo));
		ancien.getCarriere().add(gfi);
	}

	@Test
	public void test09AjouterPlusieursFoisLaMemeEntreprise() throws SQLException {
		neon = sppromo.findById(2015);
		nextoo = spentreprise.findByName("Nextoo");
		gfi = spentreprise.findByName("GFI");
		Ancien ancien = spancien.persiste("Jean", "Letudiant", neon, Entreprise.NONE, nextoo);
		assertTrue(ancien.getCarriere().isEmpty());
		ancien.addEntreprise(nextoo);
		assertFalse(ancien.getCarriere().isEmpty());
		assertThat(ancien.getEntrepriseActuelle(), is(nextoo));
		assertThat(ancien.getCarriere().size(), is(1));
		ancien.addEntreprise(nextoo);
		assertThat(ancien.getEntrepriseActuelle(), is(nextoo));
		assertThat(ancien.getCarriere().size(), is(2));
	}

	@Test
	public void test99EffaceTables() throws SQLException {
		assertTrue(sp.effacerTables());
	}
}
