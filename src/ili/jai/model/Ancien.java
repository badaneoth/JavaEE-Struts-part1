package ili.jai.model;

import java.util.List;

/**
 * Cette interface représente un ancien étudiant du master pro ILI, après
 * obtention de son diplôme.
 * 
 * @author leberre
 *
 */
public interface Ancien extends Persistable {

	/**
	 * Le nom de l'ancien.
	 * 
	 * @return le nom de l'étudiant.
	 */
	String getNom();

	/**
	 * Le prénom de l'ancien.
	 * 
	 * @return le prénom de l'étudiant.
	 */
	String getPrenom();

	/**
	 * La promotion de l'étudiant.
	 * 
	 * @return la promotion de l'ancien
	 */
	Promotion getPromotion();

	/**
	 * Retourne l'entreprise dans laquelle l'étudiant a fait son stage de M1.
	 * Retourne {@link Entreprise#CRIL} si l'ancien n'a pas fait son stage en
	 * entreprise.
	 * 
	 * @return l'entreprise dans laquelle le stage de M1 s'est effectué.
	 */
	Entreprise getEntrepriseStageM1();

	/**
	 * Retourne l'entreprise dans laquelle l'étudiant a fait son stage de M2.
	 * 
	 * @return l'entreprise dans laquelle le stage de M2 s'est effectué.
	 */
	Entreprise getEntrepriseStageM2();

	/**
	 * Retourne l'entreprise dans laquelle l'ancien se trouve. Si l'ancien est
	 * en recherche d'emploi, on devra retourner l'objet {@link Entreprise#NONE}
	 * .
	 * 
	 * @return l'entreprise dans laquelle l'ancien se trouve.
	 */
	Entreprise getEntrepriseActuelle();

	/**
	 * Retourne la liste des entreprises dans laquelle l'ancien a effectué son
	 * stage ou travaillé, par ordre chronologique. {@link Entreprise#CRIL} et
	 * {@link Entreprise#NONE} ne doivent pas apparai^tre dans cette liste.
	 * 
	 * @return la liste des entreprises par lesquelles l'ancien est passé.
	 */
	List<Entreprise> getCarriere();

	void addEntreprise(Entreprise e);

}
