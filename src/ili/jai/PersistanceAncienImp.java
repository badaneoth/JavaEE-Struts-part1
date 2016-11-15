package ili.jai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ili.jai.model.Ancien;
import ili.jai.model.AncienImp;
import ili.jai.model.Entreprise;
import ili.jai.model.EntrepriseImp;
import ili.jai.model.Promotion;
import ili.jai.model.PromotionImpl;

public class PersistanceAncienImp implements PersistanceAncien {
	private ServicePersistanceImp service = new ServicePersistanceImp();
	private Connection conn = service.getConnection();

	@Override
	public boolean maj(Ancien t) throws SQLException {

		Statement st;
		st = conn.createStatement();
		int rs = st.executeUpdate("UPDATE ANCIEN SET entsm1id=" + t.getEntrepriseStageM1().getId() + ",entsm2id="
				+ t.getEntrepriseStageM2().getId() + " where id=" + t.getId() + "");

		return (rs < 0);
	}

	@Override
	public boolean effacer(Ancien t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ancien findById(int id) throws SQLException {
		Ancien anc = null;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from ANCIEN where id=" + id + "");
			System.out.println("44444444444444444444444");

			while (rs.next()) {
				Promotion promo = new PersistancePromotionImp().findById(rs.getInt("Promotionid"));
				// Promotion promo = new PromotionImpl(promo.getAnnee(),
				// promo.getNom());
				System.out.println("55555555555555555555555'");

				anc = new AncienImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Prenom"), promo,
						Entreprise.NONE, Entreprise.NONE);
				System.out.println("66666666666666666666666666");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return anc;
	}

	@Override
	public List<Ancien> tous() throws SQLException {
		List<Ancien> anciens = new ArrayList<Ancien>();

		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery("select * from ANCIEN");
		while (rs.next()) {

			Promotion promo = new PersistancePromotionImp().findById(rs.getInt("Promotionid"));
			Entreprise e1 = new PersistanceEntrepriseImp().findById(rs.getInt("entsm1id"));
			Entreprise e2 = new PersistanceEntrepriseImp().findById(rs.getInt("entsm2id"));
			System.out.println("2222222222222222");

			Ancien anc = new AncienImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Prenom"), promo, e1, e2);

			anciens.add(anc);
		}

		return anciens;

	}

	@Override
	public Ancien persiste(String prenom, String nom, Promotion promo, Entreprise m1, Entreprise m2)
			throws SQLException {
		// TODO Auto-generated method stub

		if (nom == null) {
			throw new IllegalArgumentException();
		} else if (prenom == null) {
			throw new IllegalArgumentException();

		} else if (promo == null) {
			throw new IllegalArgumentException();
		} else if (m1 == null) {
			throw new IllegalArgumentException();

		} else if (m2 == null) {
			throw new IllegalArgumentException();

			// } else if (m1 == Entreprise.NONE) {
			// m1=Entreprise.NONE;
			// } else if (m1 == Entreprise.CRIL) {
			// m2= Entreprise.CRIL;
		} else {

			PreparedStatement ps = conn.prepareStatement(
					"insert into ANCIEN (Nom,Prenom,Promotionid,entsm1id,entsm2id) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, nom);
			ps.setString(2, prenom);
			ps.setInt(3, promo.getAnnee());
			ps.setInt(4, m1.getId());
			ps.setInt(5, m2.getId());

			ps.executeUpdate();

			Ancien anc = null;

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);

				anc = new AncienImp(id, nom, prenom, promo, m1, m2);

				System.out.println("================>" + m2.getNom());
				if (m1.getId() >= 0)
					anc.addEntreprise(m1);
			}

			return anc;
		}
	}

	@Override
	public List<Ancien> touteLaPromo(Promotion promo) throws SQLException {
		List<Ancien> anciens = new ArrayList<Ancien>();

		Statement st = conn.createStatement();
		System.out.println("2222222222222222");

		ResultSet rs = st.executeQuery("select * from ANCIEN where Promotionid=" + promo.getAnnee());
		while (rs.next()) {
			System.out.println("2222222222222222");

			Entreprise e1 = new PersistanceEntrepriseImp().findById(rs.getInt("entsm1id"));
			Entreprise e2 = new PersistanceEntrepriseImp().findById(rs.getInt("entsm2id"));
			System.out.println("2222222222222222");

			Ancien anc = new AncienImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Prenom"), promo, e1, e2);
			System.out.println("$$$$$$$$$MON ANCIEN : " + anc.getNom());
			anciens.add(anc);
		}

		return anciens;

	}

	@Override
	public List<Ancien> travaillentDans(Entreprise entreprise) throws SQLException {
		List<Ancien> anciens = new ArrayList<Ancien>();

		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery("select * from ANCIEN where entsm1id= " + entreprise.getId() + "or entsm2id= "
				+ entreprise.getId() + "");
		while (rs.next()) {

			Promotion promo = new PersistancePromotionImp().findById(rs.getInt("Promotionid"));
			Entreprise e1 = new PersistanceEntrepriseImp().findById(rs.getInt("entsm1id"));
			Entreprise e2 = new PersistanceEntrepriseImp().findById(rs.getInt("entsm2id"));
			System.out.println("2222222222222222");

			Ancien anc = new AncienImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Prenom"), promo, e1, e2);

			anciens.add(anc);
		}

		return anciens;

	}

}
