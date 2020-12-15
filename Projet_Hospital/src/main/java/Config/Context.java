package Config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAOCompte;
import dao.IDAOPatient;
import dao.IDAOVisite;
import dao.jpa.DAOCompteJPA;
import dao.jpa.DAOPatientJPA;
import dao.jpa.DAOVisiteJPA;
import dao.serial.DAOPatientSerial;

public class Context {

	private static Context _instance;

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Projet_Hospital");
	
	
	private DAOPatientSerial DaoPatientSerial= new DAOPatientSerial();
	private IDAOPatient daoPatient= new DAOPatientJPA();
	private IDAOCompte daoCompte= new DAOCompteJPA();
	private IDAOVisite daoVisite= new DAOVisiteJPA();
	
	




	private Context() {}



	public static Context getInstance()
	{
		if(_instance==null) 
		{
			_instance=new Context();
		}
		return _instance;
	}

	public DAOPatientSerial getDaoPatientSerial() {
		return DaoPatientSerial;
	}



	public void setDaoPatientSerial(DAOPatientSerial DaoPatientSerial) {
		this.DaoPatientSerial = DaoPatientSerial;
	}
	
	public IDAOPatient getDaoPatient() {
		return daoPatient;
	}



	public void setDaoPatient(IDAOPatient daoPatient) {
		this.daoPatient = daoPatient;
	}



	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}



	public void setDaoCompte(IDAOCompte daoCompte) {
		this.daoCompte = daoCompte;
	}



	public IDAOVisite getDaoVisite() {
		return daoVisite;
	}



	public void setDaoVisite(IDAOVisite daoVisite) {
		this.daoVisite = daoVisite;
	}



	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}



	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void closeEmf() 
	{
		emf.close();
	}

}
