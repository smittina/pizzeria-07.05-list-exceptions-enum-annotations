package fr.pizzeria.factory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJDBCDao;
import fr.pizzeria.dao.PizzaJPADao;
import fr.pizzeria.dao.PizzaMemDao;

/**
 * Permet de créer différentes instances de IPizzaDao en fonction du choix du Dao
 * @author Formation
 *
 */
public class DaoFactory {
	
	public IPizzaDao creerDaoPizza(int code) {
		IPizzaDao dao = null;
		switch(code) {
		case 1:
			dao = new PizzaMemDao();
			break;
		case 2:
			dao = new PizzaJDBCDao();
			break;
		case 3:
			dao = new PizzaJPADao();
			break;
		default:
			dao = new PizzaMemDao();
		}
		return dao;
	}

}
