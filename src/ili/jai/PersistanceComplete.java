package ili.jai;

import java.sql.SQLException;

import ili.jai.model.Persistable;

/**
 * Un service de persistance générique qui permet de modifier et d'effacer des
 * objets persistables.
 * 
 * @author leberre
 *
 * @param <T> un objet persistable (qui implémente l'interface {@link Persistable}
 */
public interface PersistanceComplete<T extends Persistable> extends PersistanceSimple<T> {

	boolean maj(T t) throws SQLException;
	
	boolean effacer(T t) throws SQLException;

}
