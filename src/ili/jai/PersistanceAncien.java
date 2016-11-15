package ili.jai;

import java.sql.SQLException;
import java.util.List;

import ili.jai.model.Ancien;
import ili.jai.model.Entreprise;
import ili.jai.model.Promotion;

/**
 * Le service de persistance des anciens. On y retrouve toutes les méthodes qui
 * retournent des anciens.
 * 
 * @author leberre
 *
 */
public interface PersistanceAncien extends PersistanceComplete<Ancien> {

	/**
	 * Persiste un ancien en base de données et retourne un objet Ancien.
	 * 
	 * @param prenom
	 *            le prénom de l'ancien (ne peut pas être null)
	 * @param nom
	 *            le nom de l'ancien (ne peut pas être null)
	 * @param promo
	 *            la promotion de l'ancien (ne peut pas être null)
	 * @param m1
	 *            l'entreprise dans laquelle le M1 s'est déroulé (ne peut pas
	 *            être nul)
	 * @param m2
	 *            l'entreprise dans laquelle le M2 s'est déroulé (ne peut pas
	 *            être nul)
	 * @return un objet Ancien, correspondant à un enregistrement en base de
	 *         données.
	 * @throws SQLException
	 *             si un problème est détecté.
	 * @throws IllegalArgumentException
	 *             si l'un des arguments est nul.
	 * 
	 */
	Ancien persiste(String prenom, String nom, Promotion promo, Entreprise m1, Entreprise m2) throws SQLException;

	/**
	 * Retourne tous les anciens d'une promotion particulière.
	 * 
	 * @param promo
	 *            une promotion existante
	 * @return les anciens de la promotion, classés par ordre alphabétique.
	 * @throws IllegalArgumentException
	 *             si la promotion n'existe pas dans le SGBD.
	 */
	List<Ancien> touteLaPromo(Promotion promo) throws SQLException;

	/**
	 * Retourne tous les anciens qui se trouvent actuellement dans une
	 * entreprise.
	 * 
	 * @param entreprise
	 *            une entreprise
	 * @return les anciens qui travaillent actuellement dans l'entreprise
	 * @throws IllegalArgumentException
	 *             si l'entreprise n'existe pas dans le SGBD.
	 */
	List<Ancien> travaillentDans(Entreprise entreprise) throws SQLException;
}
