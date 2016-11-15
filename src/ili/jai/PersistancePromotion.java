package ili.jai;

import java.sql.SQLException;
import java.util.List;

import ili.jai.model.Promotion;

/**
 * Le service de persistance des promotions. Les promotions n'ont pas vocation à
 * changer : elles sont définies chaque année avec le nom de release utilisé
 * pour Eclipse.
 * 
 * On y retrouve toutes les méthodes qui retournent des promotions.
 * 
 * @author leberre
 *
 */
public interface PersistancePromotion extends PersistanceSimple<Promotion> {

	/**
	 * Persiste une promotion dans le SGBD et retourne un objet Promotion.
	 * 
	 * @param annee
	 *            l'année de la promotion (>= 2005)
	 * @param nom
	 *            le nom de la promo
	 * @return un objet promotion utilisable dans les autres couches de
	 *         l'application.
	 * 
	 * @throws SQLException
	 *             si un problème de persistance est détecté.
	 */
	Promotion persiste(int annee, String nom) throws SQLException;

	/**
	 * Retourne toutes les promotions depuis une certaine année.
	 * 
	 * @param annee
	 *            une année à partir de 2005.
	 * @return la liste des promotions depuis annee ordonnées de manière
	 *         chronologique.
	 */
	List<Promotion> toutesDepuis(int annee) throws SQLException;
}
