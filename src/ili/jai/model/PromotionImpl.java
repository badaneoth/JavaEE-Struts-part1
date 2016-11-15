package ili.jai.model;

public class PromotionImpl implements Promotion {
	private String nom;
	private int annee;

	public PromotionImpl(int anne, String nomm) {
		this.annee = anne;
		this.nom = nomm;

	}

	@Override
	public int getAnnee() {
		// TODO Auto-generated method stub
		return annee;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annee;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
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
		PromotionImpl other = (PromotionImpl) obj;
		if (annee != other.annee)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	public void setAnnee(int annee) {
		this.annee = annee;

	}

	public void setNom(String nom) {
		this.nom = nom;

	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
