package ili.jai;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import ili.jai.model.Entreprise;
import ili.jai.model.EntrepriseImp;
import ili.jai.model.Promotion;
import ili.jai.model.PromotionImpl;

public class PersistancePromotionImp implements PersistancePromotion {
	private ServicePersistanceImp service = new ServicePersistanceImp();
	private Connection conn = service.getConnection();

	@Override
	public List<Promotion> tous() throws SQLException {
		// TODO Auto-generated method stub
		List<Promotion> promos = new ArrayList<Promotion>();

		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery("select * from PROMOTION");

		while (rs.next()) {
			Promotion pro = new PromotionImpl(rs.getInt("Annee"), rs.getString("Nom"));

			promos.add(pro);
		}

		return promos;
	}

	@Override
	public Promotion persiste(int annee, String nom) throws SQLException {
		if (annee < 2005) {
			throw new IllegalArgumentException("error");
		} else {
			Promotion pro;
			PreparedStatement ps = conn.prepareStatement("insert into PROMOTION(Annee,Nom) values(?,?)");
			ps.setInt(1, annee);
			ps.setString(2, nom);

			pro = new PromotionImpl(annee, nom);

			ps.executeUpdate();

			return pro;
		}
	}

	@Override
	public List<Promotion> toutesDepuis(int annee) {
		List<Promotion> promos = new ArrayList<Promotion>();

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from PROMOTION");
			System.out.println("taille" + promos.size());

			while (rs.next()) {
				System.out.println(">>" + rs.getString("Nom") + ":" + rs.getInt("Annee"));
				Promotion pro = new PromotionImpl(rs.getInt("Annee"), rs.getString("Nom"));
				if (rs.getInt("Annee") >= annee) {
					// System.out.println(">>"+rs.getString("Nom"));

					promos.add(pro);
				}
			}
			System.out.println("taille" + promos.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return promos;
	}

	@Override
	public Promotion findById(int id) throws SQLException {
		Promotion promo = null;
		Statement st;
		try {
			System.out.println("77777777777777777777777");
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from PROMOTION where Annee=" + id + "");
			System.out.println("88888888888888888888");

			while (rs.next()) {

				promo = new PromotionImpl(rs.getInt("Annee"), rs.getString("Nom"));
				System.out.println("9999999999999999999999");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return promo;
	}

}
