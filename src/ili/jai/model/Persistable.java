package ili.jai.model;

/**
 * Une interface marqueur pour limiter la couche de persistance aux seuls objets
 * de l'application.
 * 
 * @author leberre
 *
 */
public interface Persistable {

	/**
	 * Retourne un identifiant unique pour l'enregistrement dans la base de
	 * données. Cet identifiant sera utilisé essentiellement pour réaliser le
	 * patron de conception
	 * <a href="http://martinfowler.com/eaaCatalog/identityMap.html">Identity
	 * Map</a> afin de s'assurer qu'un seul objet en mémoire représente un
	 * enregistrement dans le SGBD.
	 * 
	 * @return un identifiant unique représentant l'objet dans la base de données.
	 */
	int getId();
}
