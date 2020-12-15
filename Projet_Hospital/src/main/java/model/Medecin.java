package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Medecin")
public class Medecin extends Compte{

	protected Integer salle;
	@OneToMany(mappedBy = "medecin",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Visite> visites= new ArrayList();
	public Medecin() {}

	public Medecin(Integer salle) {
		super();
		this.salle = salle;
	}
	public Medecin(Integer id, String login, String password, Integer salle) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.salle = salle;
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	public Integer getSalle() {
		return salle;
	}

	public void setSalle(Integer salle) {
		this.salle = salle;
	}

	@Override
	public String toString() {
		return "Medecin [salle=" + salle + ", id=" + id + ", login=" + login + ", password=" + password + "]";
	}
	




}
