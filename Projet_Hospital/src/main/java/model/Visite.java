package model;

import java.time.LocalDateTime;

import javax.persistence.*;
import Config.Context;


@Entity
public class Visite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_visite;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Patient patient;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Medecin medecin;
	private Integer cout=20;
	private Integer salle;
	@Column(columnDefinition = "DATE")
	private LocalDateTime date;
	

	
	
	
		public Visite() {
	}

		public Visite(Patient patient, Medecin medecin, Integer cout, Integer salle, LocalDateTime date) {
			this.patient = patient;
			this.medecin = medecin;
			this.cout = cout;
			this.salle = salle;
			this.date = date;
		}


		


		public Integer getId_visite() {
			return id_visite;
		}





		public void setId_visite(Integer id_visite) {
			this.id_visite = id_visite;
		}





		public Patient getPatient() {
			return patient;
		}





		public void setPatient(Patient patient) {
			this.patient = patient;
		}





		public Medecin getMedecin() {
			return medecin;
		}





		public void setMedecin(Medecin medecin) {
			this.medecin = medecin;
		}





		public Integer getCout() {
			return cout;
		}





		public void setCout(Integer cout) {
			this.cout = cout;
		}





		public Integer getSalle() {
			return salle;
		}





		public void setSalle(Integer salle) {
			this.salle = salle;
		}


		public LocalDateTime getDate() {
			return date;
		}


		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		@Override
		public String toString() {
			return "Visite [id_visite=" + id_visite + ", n_patient=" + patient + ", id_medecin=" + medecin
					+ ", cout=" + cout + ", salle=" + salle + ", date=" + date + "]";
		}


}
