package fr.pizzeria.services;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJDBCDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;

public class InitialiserPizzasService extends MenuService {
	
	PizzaMemDao memDao;

	public InitialiserPizzasService(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}
	
	public InitialiserPizzasService(IPizzaDao dao, PizzaMemDao memDao) {
		super(dao);
		this.memDao = memDao;
	}

	@Override
	public void executeUC() throws PizzaException {
		((PizzaJDBCDao) getDao()).initialiserPizzas(memDao.getLesPizzas());

	}

}
