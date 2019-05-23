package fr.pizzeria.services;

import java.util.List;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.NoPizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.IPizzaDao;

/**
 * Service permettant de Lister l'intégralité des Pizzas
 * @author Formation
 *
 */
public class ListerPizzasService extends MenuService {

	/**
	 * Constructeur
	 * @param daoPizza Dao Pizza
	 */
	public ListerPizzasService(PizzaMemDao daoPizza) {
		super(daoPizza);
	}
	
	public ListerPizzasService(IPizzaDao dao) {
		super(dao);
	}
	
	/**
	 * Permet d'afficher la liste de l'ensemble des pizzas
	 */
	@Override
	public void executeUC() throws NoPizzaException {
		System.out.println("LISTE DES DIFFERENTES PIZZAS");
		//List<Pizza> mesPizzas = getDaoPizza().findAllPizzas();
		List<Pizza> mesPizzas = getDao().findAllPizzas();
		if(mesPizzas.size()!=0) {
			for(Pizza p : mesPizzas) {
				//System.out.println(p.getCode()+" -> "+ p.getLibelle()+" ("+p.getPrix()+"€) -- "+p.toString());
				System.out.println(p.toString());
			}
		}
		else {
			throw new NoPizzaException("Il n'y a pas de pizzas dans la base de données \n Veuillez l'initialiser avec l'option 5");
		}
	}

}

