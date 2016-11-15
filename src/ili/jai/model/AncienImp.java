package ili.jai.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AncienImp implements Ancien {
	private int id;
	private String nom;
	private String prenom;
	private Promotion promotion;
	private Entreprise m1;
	private Entreprise m2;

	private List<Entreprise> carriere;

	public AncienImp(int id, String nom, String prenom, Promotion promo, Entreprise m1, Entreprise m2) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.promotion = promo;
		this.m1 = m1;
		this.m2 = m2;
		this.carriere = new ArrayList<Entreprise>();
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return prenom;
	}

	@Override
	public Entreprise getEntrepriseStageM1() {
		// TODO Auto-generated method stub
		return m1;
	}

	@Override
	public Entreprise getEntrepriseStageM2() {
		// TODO Auto-generated method stub
		return m2;
	}

	public void setEntrepriseStageM1(Entreprise m1) {
		this.m1 = m1;
	}

	public void setEntrepriseStageM2(Entreprise m2) {
		this.m2 = m2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public Entreprise getEntrepriseActuelle() {
		// TODO Auto-generated method stub
		if (this.carriere.isEmpty())
			return Entreprise.NONE;
		return this.carriere.get(this.carriere.size() - 1);
	}

	public Entreprise getM1() {
		return m1;
	}

	public void setM1(Entreprise m1) {
		this.m1 = m1;
	}

	public Entreprise getM2() {
		return m2;
	}

	public void setM2(Entreprise m2) {
		this.m2 = m2;
	}

	@Override
	public List<Entreprise> getCarriere() {
		// TODO Auto-generated method stub
		List<Entreprise> liste = Collections.unmodifiableList(this.carriere);
		return liste;
	}

	@Override
	public void addEntreprise(Entreprise e) {
		// TODO Auto-generated method stub
		this.carriere.add(e);
	}

	@Override
	public Promotion getPromotion() {
		// TODO Auto-generated method stub
		return promotion;
	}

}
