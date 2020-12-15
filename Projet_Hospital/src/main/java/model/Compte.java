package model;

import javax.persistence.*;

import Config.Context;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "type_compte")
public abstract class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	//@Column(unique = true)
	protected String login;
	protected String password;

	
	public Compte() {
	}

	public Compte(Integer id, String login, String password) {
	
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public Compte(String login, String password) {
	
		this.login = login;
		this.password = password;
	}

	public Integer getid() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + "]";
	}

	//Verify
	public static void showAllCompte() {
		System.out.println("\nListe des Comptes : \n");
		for(Compte c : Context.getInstance().getDaoCompte().selectAll()) 
		{
			System.out.println(c);
		}
	}
	
	//Verify
	/*public static void creatAdherent(Integer num_voie, String voie, String code_postal, String ville, String nom,
			String prenom, String login, String password, double solde) {
		Adresse adresse = new Adresse(num_voie, voie, code_postal,ville);
		Context.getInstance().getDaoCompte().ajouter(new Secretaire(nom, prenom, login, password, solde, adresse));
	}*/
}
