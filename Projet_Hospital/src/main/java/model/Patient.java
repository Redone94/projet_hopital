package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import Config.Context;

@Entity

public class Patient implements Serializable  {
	
	@Id
	//@OneToMany
	private int n_patient;
	private String nom;
	private String prenom;
	private Integer num_voie;
	private String voie;
	private String ville;
	private String code_postal;
	public Patient() {
		super();
	}
	public Patient(int n_patient, String nom, String prenom, Integer num_voie, String voie, String ville,
			String code_postal) {
		super();
		this.n_patient = n_patient;
		this.nom = nom;
		this.prenom = prenom;
		this.num_voie = num_voie;
		this.voie = voie;
		this.ville = ville;
		this.code_postal = code_postal;
	}
	public Integer getN_patient() {
		return n_patient;
	}
	public void setN_patient(Integer n_patient) {
		this.n_patient = n_patient;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Integer getNum_voie() {
		return num_voie;
	}
	public void setNum_voie(Integer num_voie) {
		this.num_voie = num_voie;
	}
	public String getVoie() {
		return voie;
	}
	public void setVoie(String voie) {
		this.voie = voie;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	@Override
	public String toString() {
		return "Patient [n_patient=" + n_patient + ", nom=" + nom + ", prenom=" + prenom + ", num_voie=" + num_voie
				+ ", voie=" + voie + ", ville=" + ville + ", code_postal=" + code_postal + "]";
	}
	
	

}
