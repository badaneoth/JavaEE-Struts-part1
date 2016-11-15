package ili.jai.model;

public class EntrepriseImp implements Entreprise {
	private int id;
	private String nom;
	private String codepostale;
	private String ville;

	public EntrepriseImp(int id, String nom, String codepostale, String ville) {
		this.id = id;
		this.nom = nom;
		this.codepostale = codepostale;
		this.ville = ville;

	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public String getCodePostal() {
		// TODO Auto-generated method stub
		return codepostale;
	}

	@Override
	public String getVille() {
		// TODO Auto-generated method stub
		return ville;
	}

	public String getCodepostale() {
		return codepostale;
	}

	public void setCodepostale(String codepostale) {
		this.codepostale = codepostale;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codepostale == null) ? 0 : codepostale.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntrepriseImp other = (EntrepriseImp) obj;
		if (codepostale == null) {
			if (other.codepostale != null)
				return false;
		} else if (!codepostale.equals(other.codepostale))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setCodePostal(String codePostal) {
		// TODO Auto-generated method stub
		this.codepostale = codePostal;
	}

}
