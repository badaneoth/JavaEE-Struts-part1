package ili.jai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ili.jai.model.Entreprise;
import ili.jai.model.EntrepriseImp;
import ili.jai.model.Promotion;
import ili.jai.model.PromotionImpl;

public class PersistanceEntrepriseImp implements PersistanceEntreprise {
	private ServicePersistanceImp service = new ServicePersistanceImp();
	private Connection conn = service.getConnection();

	@Override
	public boolean maj(Entreprise t) throws SQLException {
		// TODO Auto-generated method stub
		try {

			PreparedStatement ps = conn
					.prepareStatement("update ENTREPRISE set Nom = ?, Codepostale = ?, Ville = ? WHERE id = ?");

			ps.setString(1, t.getNom());
			ps.setString(2, t.getCodePostal());
			ps.setString(3, t.getVille());
			ps.setInt(4, t.getId());
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.err.println("ERROR modification");
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean effacer(Entreprise t) throws SQLException {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = conn.prepareStatement("delete from ENTREPRISE where id=?");
			ps.setInt(1, t.getId());

			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.err.println("ERROR SUPPRESSION");
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public List<Entreprise> tous() throws SQLException {
		// TODO Auto-generated method stub
		List<Entreprise> ents = new ArrayList<Entreprise>();

		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery("select * from ENTREPRISE");

		while (rs.next()) {
			Entreprise ent = new EntrepriseImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Codepostale"),
					rs.getString("Ville"));

			ents.add(ent);
		}

		return ents;

	}

	@Override
	public Entreprise persiste(String nom, String codePostal, String ville) throws SQLException {

		// TODO Auto-generated method stub
		PreparedStatement ps = conn.prepareStatement("insert into ENTREPRISE (Nom,CodePostale,Ville) values(?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, nom);
		ps.setString(2, codePostal);
		ps.setString(3, ville);
		ps.executeUpdate();

		Entreprise ent = null;

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			int id = rs.getInt(1);

			ent = new EntrepriseImp(id, nom, codePostal, ville);
		}

		return ent;
	}

	@Override
	public List<Entreprise> toutesParVille(String ville) {
		// TODO Auto-generated method stub
		List<Entreprise> ents = new ArrayList<Entreprise>();

		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from ENTREPRISE");
			System.out.println("taille" + ents.size());

			while (rs.next()) {
				System.out.println(">>" + rs.getString("Nom") + ":" + rs.getString("Codepostale"));
				Entreprise ent = new EntrepriseImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Codepostale"),
						rs.getString("Ville"));
				if (rs.getString("Ville").equals(ville)) {
					// System.out.println(">>"+rs.getString("Nom"));

					ents.add(ent);
				}
			}
			System.out.println("taille" + ents.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ents;

	}

	@Override
	public Entreprise findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		if (id >= 1000) {
			throw new IllegalArgumentException("erro");
		} else if (id < 0) {
			return Entreprise.NONE;
		} else if (id == 0) {
			return Entreprise.CRIL;
		} else {
			Entreprise ent = null;
			Statement st;
			try {
				st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from ENTREPRISE where id=" + id + "");

				while (rs.next()) {

					ent = new EntrepriseImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Codepostale"),
							rs.getString("Ville"));

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return ent;
		}
	}

	@Override
	public Entreprise findByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		Entreprise ent = null;
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from ENTREPRISE where Nom='" + name + "'");

			while (rs.next()) {

				ent = new EntrepriseImp(rs.getInt("id"), rs.getString("Nom"), rs.getString("Codepostale"),
						rs.getString("Ville"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ent;
	}
}
