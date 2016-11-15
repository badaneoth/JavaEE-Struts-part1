package ili.jai;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ili.jai.model.Ancien;
import ili.jai.model.Entreprise;
import ili.jai.model.Promotion;

public class ServicePersistanceImp implements ServicePersistance {
	private Connection connection;

	public ServicePersistanceImp() {
		try {
			connection = DriverManager.getConnection("jdbc:derby:Mydb;create=true");
			System.out.println("Connection etablie");
		} catch (SQLException e) {
			System.out.println("Connection non");
		}
	}

	@Override
	public boolean creerTables() throws SQLException {
		try {
			System.out.println("--------------------creertables------------------");
			Statement st = connection.createStatement();
			st.executeUpdate("CREATE TABLE PROMOTION (Annee INT, Nom VARCHAR(25))");

			st.executeUpdate(
					"CREATE TABLE ENTREPRISE (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),Nom VARCHAR(25),Codepostale VARCHAR(25),Ville VARCHAR(25), constraint primary_key_entreprise primary key (id))");
			st.executeUpdate(
					"CREATE TABLE ANCIEN (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Nom VARCHAR(25), Prenom VARCHAR(25),Promotionid INT , entsm1id INT  , entsm2id INT , constraint primary_key_ancien primary key (id))");

			return true;

		} catch (SQLException e) {
			System.err.println("ERROR CREATION");
			e.printStackTrace();
		}
		return false;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean effacerTables() throws SQLException {

		try {

			Statement st = connection.createStatement();
			System.out.println("dropPromotion:" + st.execute("DROP TABLE PROMOTION"));
			System.out.println("dropEntreprise:" + st.execute("DROP TABLE ENTREPRISE"));
			System.out.println("dropAncien:" + st.execute("DROP TABLE ANCIEN"));

			return true;

		} catch (SQLException e) {
			System.err.println("ERROR SUPPRESSION");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PersistanceAncien servicePersistanceAncien() {
		return new PersistanceAncienImp();
	}

	@Override
	public PersistancePromotion servicePersistancePromotion() {
		return new PersistancePromotionImp();
	}

	@Override
	public PersistanceEntreprise servicePersistanceEntreprise() {
		return new PersistanceEntrepriseImp();
	}

}
