package ili.jai;

import java.sql.SQLException;

/**
 * Le service de persistance de l'application.
 * 
 * @author leberre
 *
 */
public interface ServicePersistance {

	/**
	 * Crée toutes les tables dans le SGBD
	 * 
	 * @return true si les tables sont créées, false sinon (si elles existent
	 *         déjà par exemple).
	 * @throws SQLException
	 *             pour remonter tout problème lié à JDBC
	 */
	boolean creerTables() throws SQLException;

	/**
	 * Efface les tables du SGBD
	 * 
	 * @return true si toutes les tables sont effacées, false sinon.
	 * @throws SQLException
	 *             pour remonter tout problème lié à JDBC
	 */
	boolean effacerTables() throws SQLException;

	/**
	 * 
	 * @return le service de persistance pour les anciens.
	 */
	PersistanceAncien servicePersistanceAncien();

	/**
	 * 
	 * @return le service de persistance pour les promotions.
	 */
	PersistancePromotion servicePersistancePromotion();

	/**
	 *
	 * @return le service de persistance pour les entreprises.
	 */
	PersistanceEntreprise servicePersistanceEntreprise();
}
