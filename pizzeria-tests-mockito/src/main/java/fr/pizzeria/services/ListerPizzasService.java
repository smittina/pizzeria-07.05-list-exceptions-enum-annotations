package fr.pizzeria.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.NoPizzaException;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.IPizzaDao;

/**
 * Service permettant de Lister l'intégralité des Pizzas
 * @author Formation
 *
 */
public class ListerPizzasService extends MenuService {
	
	public static final Logger LOG = LoggerFactory.getLogger(ListerPizzasService.class);

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
	public void executeUC() throws NoPizzaException, PizzaException {
		System.out.println("LISTE DES DIFFERENTES PIZZAS");
		//List<Pizza> mesPizzas = getDaoPizza().findAllPizzas();
		LOG.info("Récupération de la liste des pizzas");
		List<Pizza> mesPizzas = getDao().findAllPizzas();
		if(mesPizzas.size()!=0) {
			for(Pizza p : mesPizzas) {
				if(p == null) {
					LOG.warn("PizzaException Lancée : Incohérence dans la base de données : une pizza ne peut pas être nulle");
					throw new PizzaException("Il y a une incohérence - une pizza ne peut être nulle");
					
				}
				if(p.getCode() == null) {
					LOG.warn("PizzaException Lancée : Incohérence dans la base de données : une pizza ne peut pas avoir un code null");
					throw new PizzaException("Il y a une incohérence - une pizza ne peut avoir un code = null");
				}
				//System.out.println(p.getCode()+" -> "+ p.getLibelle()+" ("+p.getPrix()+"€) -- "+p.toString());
				LOG.info("Affichage de la liste des pizzas");
				System.out.println(p.toString());
			}
		}
		else {
			LOG.warn("NoPizzaExecption Lancée : Aucune pizza dans la base de données");
			throw new NoPizzaException("Il n'y a pas de pizzas dans la base de données \n Veuillez l'initialiser avec l'option 5");
		}
	}

}

