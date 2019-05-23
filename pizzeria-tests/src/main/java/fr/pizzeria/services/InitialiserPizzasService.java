package fr.pizzeria.services;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJDBCDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;

public class InitialiserPizzasService extends MenuService {
	
	IPizzaDao memDao;

	public InitialiserPizzasService(IPizzaDao dao) {
		super(dao);
		// TODO Auto-generated constructor stub
	}
	
	public InitialiserPizzasService(IPizzaDao dao, IPizzaDao memDao) {
		super(dao);
		this.memDao = memDao;
	}

	@Override
	public void executeUC() throws PizzaException {
		//((PizzaJDBCDao) getDao()).initialiserPizzas(((PizzaMemDao) memDao).getLesPizzas());
		
		getDao().initialiserPizzas(((PizzaMemDao) memDao).getLesPizzas());
	}

}
