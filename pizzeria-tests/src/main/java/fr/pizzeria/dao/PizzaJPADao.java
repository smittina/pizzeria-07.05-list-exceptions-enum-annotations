package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaJPADao implements IPizzaDao {
	
	private EntityManager em;
	
	public PizzaJPADao() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-pizza");
		this.em = emf.createEntityManager();
	}
	
	@Override
	public void initialiserPizzas(List<Pizza> lesPizzas) {
		// Delete all pizzas
		Query queryDelete = em.createQuery("DELETE FROM Pizza p");
		em.getTransaction().begin();
		queryDelete.executeUpdate();
		
		// Insertions de la liste de pizzas
		for(Pizza p : lesPizzas) {
			em.persist(p);
		}
		em.getTransaction().commit();
		
	}

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> p = new ArrayList<Pizza>();
		TypedQuery<Pizza> query = em.createQuery("select p from Pizza p", Pizza.class);
		p = query.getResultList();
		return p;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		em.getTransaction().begin();
		em.persist(pizza);
		em.getTransaction().commit();

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		Query query = em.createQuery("UPDATE Pizza p SET p.code = :code,  p.libelle = :libelle, p.prix = :prix, p.categoriePizza = :categorie WHERE p.code= :codePizza");
		query.setParameter("code", pizza.getCode());
		query.setParameter("libelle", pizza.getLibelle());
		query.setParameter("prix", pizza.getPrix());
		switch(pizza.getCategorie().toUpperCase()) {
		case "VIANDE":
			query.setParameter("categorie", CategoriePizza.VIANDE);
			break;
		case "SANS_VIANDE":
			query.setParameter("categorie", CategoriePizza.SANS_VIANDE);
			break;
		case "POISSON":
			query.setParameter("categorie", CategoriePizza.POISSON);
			break;
		}
		query.setParameter("codePizza", codePizza);
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();

	}

	@Override
	public void deletePizza(String codePizza) {
		Query query = em.createQuery("DELETE FROM Pizza p WHERE p.code = :codePizza");
		query.setParameter("codePizza", codePizza);
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		Pizza p = null;
		TypedQuery<Pizza> query = em.createQuery("Select p From Pizza p where p.code= :code", Pizza.class);
		p = query.setParameter("code", codePizza).getSingleResult();
		return p;
	}

	@Override
	public boolean pizzasExists(String codePizza) {
		TypedQuery<Pizza> query = em.createQuery("Select p From Pizza p where p.code= :code", Pizza.class);
		return query.setParameter("code", codePizza).getResultList().size() == 1;
		
	}



}
