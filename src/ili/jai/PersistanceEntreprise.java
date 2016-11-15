package ili.jai;

import java.sql.SQLException;
import java.util.List;

import ili.jai.model.Entreprise;

/**
 * Le service de persistance des entreprises : on y retrouve toutes les méthodes
 * qui retournent des entreprises.
 * 
 * @author leberre
 *
 */
public interface PersistanceEntreprise extends PersistanceComplete<Entreprise> {

	/**
	 * Persiste une entreprise dans le SGBD et retourne un objet Entreprise.
	 * 
	 * @param nom
	 *            le nom de l'entreprise
	 * @param codePostal
	 *            le code postal du siège de l'entreprise
	 * @param ville
	 *            la ville du siège social de l'entreprise
	 * @return un objet Entreprise utilisable dans les autres couches de
	 *         l'application
	 * @throws SQLException
	 *             si un problème de persistance est détecté.
	 */
	Entreprise persiste(String nom, String codePostal, String ville) throws SQLException;

	/**
	 * 
	 * @param name
	 *            le nom d'une entreprise
	 * @return un objet Entreprise correspondant à une entreprise de ce nom.
	 * @throws SQLException
	 *             si un problème de persistance est détecté.
	 * @throws IllegalArgumentException
	 *             si l'entreprise n'existe pas en base.
	 */
	Entreprise findByName(String name) throws SQLException;

	/**
	 * Retourne toutes les entreprises d'une même ville.
	 * 
	 * @param ville
	 *            un nom de ville, la casse ne doit pas avoir d'importance
	 * @return les entreprises situées dans cette ville, par ordre alphabétique.
	 * @throws SQLException
	 *             si un problème de persistance est détecté.
	 */
	List<Entreprise> toutesParVille(String ville) throws SQLException;
}
