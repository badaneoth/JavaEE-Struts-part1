package ili.jai.model;

/**
 * Cette interface représente une entreprise, dans laquelle
 * l'ancien a soit effectué un stage soit travaillé.
 * 
 * @author leberre
 *
 */
public interface Entreprise extends Persistable {

	/**
	 * Objet spécifique pour les anciens en recherche d'emploi.
	 */
	public static final Entreprise NONE = new Entreprise() {

		@Override
		public String getNom() {
			return "Recherche d'emploi";
		}

		@Override
		public String getCodePostal() {
			return "";
		}

		@Override
		public String getVille() {
			return "";
		}

		@Override
		public void setNom(String nom) {
		}

		@Override
		public void setCodePostal(String codePostal) {
		}

		@Override
		public void setVille(String ville) {
		}

		@Override
		public int getId() {
			return -1;
		}
	};
	
	/**
	 * Objet spécifique pour les anciens n'ayant pas effectué leur stage en entreprise.
	 */
	public static final Entreprise CRIL = new Entreprise() {

		@Override
		public String getNom() {
			return "CRIL";
		}

		@Override
		public String getCodePostal() {
			return "62307";
		}

		@Override
		public String getVille() {
			return "LENS";
		}

		@Override
		public void setNom(String nom) {
		}

		@Override
		public void setCodePostal(String codePostal) {
		}

		@Override
		public void setVille(String ville) {
		}

		@Override
		public int getId() {
			return 0;
		}		
	};
	
	/**
	 * 
	 * @return le nom de l'entreprise.
	 */
	String getNom();
	
	/**
	 * Change le nom de l'entreprise
	 * 
	 * @param nom le nouveau nom de l'entreprise.
	 */
	void setNom(String nom);
	
	/**
	 * 
	 * @return le code postal de l'adresse du siège social de l'entreprise
	 */
	String getCodePostal();
	
	/**
	 * Change le code postal de l'entreprise
	 * 
	 * @param codePostal le nouveau code postal de l'entreprise
	 */
	void setCodePostal(String codePostal);
	
	/**
	 * 
	 * @return la ville où se situe le siège social de l'entreprise.
	 */
	String getVille();
	
	/**
	 * Change le nom de la ville du siège social de l'entreprise.
	 * 
	 * @param ville la nouvelle ville du siège social de l'entreprise
	 */
	void setVille(String ville);
}
