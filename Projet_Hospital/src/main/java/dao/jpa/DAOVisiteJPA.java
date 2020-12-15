package dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOVisite;
import model.Compte;
import model.Visite;


public class DAOVisiteJPA implements IDAOVisite{

	@Override
	public void ajouter(Visite t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void modifier(Visite t) {
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
	public Visite selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Visite a = em.find(Visite.class, id);
		em.close();
		return a;		
	}

	@Override
	public List<Visite> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from visite",Visite.class);
		return maRequete.getResultList();
	}
	@Override
	public List<Visite> selectByIdPatient(Integer n_patient){
		List <Visite> v=new ArrayList();
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("Select v from Visite v join v.patient p where p.n_patient=:id",Visite.class);
		maRequete.setParameter("id",n_patient);

		try {
			v = maRequete.getResultList();
		} catch (Exception e) {}
		em.close();
		return v;
	}

	
}
