package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOPatient;
import model.Patient;

public class DAOPatientJPA implements IDAOPatient {

	@Override
	public void ajouter(Patient t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Patient t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		t=em.merge(t);
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void supprimer(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		id=em.merge(id);

		em.remove(id);

		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public Patient selectById(Integer n_patient) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Patient a = em.find(Patient.class, n_patient);
		em.close();
		return a;		
	}
	
	
	@Override
	public List<Patient> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from patient",Patient.class);
		return maRequete.getResultList();
	}

}
