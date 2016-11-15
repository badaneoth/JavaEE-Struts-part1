package ili.jai;

import java.sql.SQLException;
import java.util.List;

import ili.jai.model.Persistable;

/**
 * Un service de persistance simple, qui va simplement stocker des
 * objets dans la base de données et les récupérer.
 * 
 * @author leberre
 *
 * @param <T>
 */
public interface PersistanceSimple<T extends Persistable> {

	/**
	 * Retourne un objet d'un identifiant particulier
	 * @param id un identifiant
	 * @return l'objet de type T ayant cet identifiant.
	 * @throws SQLException  si un problème de persistance est détecté.
	 * @throws IllegalArgumentException si l'identifiant n'est pas valide.
	 */
	T findById(int id) throws SQLException ;
	
	/**
	 * Retourne tous les objets présents en base de données.
	 * 
	 * @return la liste de tous les objets présents en base de données.
	 * @throws SQLException  si un problème de persistance est détecté.
	 */
	List<T> tous() throws SQLException;
}
