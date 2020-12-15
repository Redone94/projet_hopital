package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Config.Context;
import dao.IDAOCompte;
import model.Secretaire;
import model.Medecin;
import model.Compte;
import model.Compte;

public class DAOCompteJPA implements IDAOCompte {

	@Override
	public void ajouter(Compte t) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();		
	}

	@Override
	public void modifier(Compte t) {
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
	public Compte selectById(Integer id) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		Compte a = em.find(Compte.class, id);
		em.close();
		return a;	
	}

	@Override
	public List<Compte> selectAll() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte",Compte.class);
		return maRequete.getResultList();
	}

	@Override
	public List<Medecin> SelectMedecin() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		Query maRequete = em.createQuery("from Compte WHERE type_compte='Medecin'",Compte.class);
		return maRequete.getResultList();
	}

	@Override
	public List<Secretaire> SelectSecretaire() {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();

		//Query maRequete = em.createQuery("from Compte WHERE type_compte='Secretaire'",Compte.class);
		Query maRequete = em.createQuery("from Secretaire",Secretaire.class);

		return maRequete.getResultList();
	}



	@Override
	public Compte SelectByLoginMdp(String login, String mdp) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		
		
		Query maRequete = em.createQuery("from Compte c  where c.login=:loginReq and c.password=:password ",Compte.class);
		maRequete.setParameter("loginReq",login);
		maRequete.setParameter("password",mdp);
		Compte c = (Compte) maRequete.getSingleResult();
		em.close();
		return c;
		
	}

	@Override
	public Compte SelectByLogin(String login) {
		EntityManager em=Context.getInstance().getEmf().createEntityManager();
		
		Compte c =null;
		Query maRequete = em.createQuery("from Compte c where c.login=:loginReq",Compte.class);
		maRequete.setParameter("loginReq",login);
		try {
			c = (Compte) maRequete.getSingleResult();
		} catch (Exception e) {}
		em.close();
		return c;
	}
}
