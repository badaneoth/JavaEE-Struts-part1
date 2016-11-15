package ili.jai;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ili.jai.model.Entreprise;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestJDBC2 {

	private ServicePersistance sp;
	private PersistanceEntreprise spentreprise;

	abstract protected ServicePersistance getServicePersistance();

	@Before
	public void init() {
		sp = getServicePersistance();
		spentreprise = sp.servicePersistanceEntreprise();
	}

	@Test
	public void test01CreateTables() throws SQLException {
		assertTrue(sp.creerTables());
	}

	@Test
	public void test02CreationEntreprise() throws SQLException {
		assertThat(spentreprise.tous().size(), is(0));
		Entreprise atos = spentreprise.persiste("Atos", "59113", "Seclin");
		assertThat(spentreprise.tous().size(), is(1));
		Entreprise gfi = spentreprise.persiste("GFI", "59000", "Lille");
		List<Entreprise> toutes = spentreprise.tous();
		assertThat(toutes.size(), is(2));
		assertThat(toutes, hasItem(atos));
		assertThat(toutes, hasItem(gfi));
	}

	@Test
	public void test03ModifierEntreprise() throws SQLException {
		List<Entreprise> lilloises = spentreprise.toutesParVille("Lille");
		assertThat(lilloises.size(), is(1));
		Entreprise notgfi = lilloises.get(0);
		notgfi.setNom("Proxiad");
		spentreprise.maj(notgfi);
		lilloises = spentreprise.toutesParVille("Lille");
		assertThat(lilloises.size(), is(1));
		notgfi = lilloises.get(0);
		assertThat(notgfi.getNom(), is("Proxiad"));
	}

	@Test
	public void test04EffacerEntreprise() throws SQLException {
		assertThat(spentreprise.tous().size(), is(2));
		List<Entreprise> lilloises = spentreprise.toutesParVille("Lille");
		assertThat(lilloises.size(), is(1));
		Entreprise gfi = lilloises.get(0);
		spentreprise.effacer(gfi);
		assertThat(spentreprise.tous().size(), is(1));
		assertThat(spentreprise.toutesParVille("Lille").size(), is(0));
	}

	@Test
	public void test05FindByIdOk() throws SQLException {
		assertThat(spentreprise.tous().size(), is(1));
		Entreprise nextoo = spentreprise.persiste("Nextoo", "59100", "Roubaix");
		Entreprise surprise = spentreprise.findById(nextoo.getId());
		assertThat(surprise, is(nextoo));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test06FindByIdKo() throws SQLException {
		spentreprise.findById(1000);
	}

	@Test
	public void test07FindByIdNone() throws SQLException {
		// tout id négatif doit retourner NONE
		Entreprise entreprise = spentreprise.findById(-200);
		assertThat(entreprise, is(Entreprise.NONE));
	}

	@Test
	public void test08FindByIdCRIL() throws SQLException {
		// un id de 0 doit retourner CRIL
		Entreprise entreprise = spentreprise.findById(0);
		assertThat(entreprise, is(Entreprise.CRIL));
	}

	@Test
	public void test99EffaceTables() throws SQLException {
		assertTrue(sp.effacerTables());
	}
}