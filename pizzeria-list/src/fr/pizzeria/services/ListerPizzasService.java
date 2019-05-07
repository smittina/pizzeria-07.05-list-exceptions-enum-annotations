package fr.pizzeria.services;

import java.util.List;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

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
	
	/**
	 * Permet d'afficher la liste de l'ensemble des pizzas
	 */
	@Override
	public void executeUC() {
		System.out.println("LISTE DES DIFFERENTES PIZZAS");
		List<Pizza> mesPizzas = getDaoPizza().findAllPizzas();
		for(Pizza p : mesPizzas) {
			System.out.println(p.getCode()+" -> "+ p.getLibelle()+" ("+p.getPrix()+"€)");
		}
	}

}

