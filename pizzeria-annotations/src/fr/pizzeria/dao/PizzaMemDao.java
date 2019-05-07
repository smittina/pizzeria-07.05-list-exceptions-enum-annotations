package fr.pizzeria.dao;

import java.util.List;
import java.util.Vector;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Classe représentant le Dao des Pizzas
 * @author Formation
 *
 */
public class PizzaMemDao implements IPizzaDao {
	
	/**
	 * Le tableau des différentes pizzas
	 */
	private List<Pizza> lesPizzas;	
	

	/**
	 * Constructeur par défaut qui initialise le tableau de pizzas
	 */
	public PizzaMemDao() {
		// Initialisations du tableau de pizzas
		lesPizzas = new Vector<>();
		Pizza p = new Pizza(0,"PEP", "Pépéroni",12.50,CategoriePizza.VIANDE);
		lesPizzas.add(p);
		p =  new Pizza(1,"MAR", "Margherita",14.00, CategoriePizza.SANS_VIANDE);
		lesPizzas.add(p);
		p =  new Pizza(2,"REIN", "La Reine",11.50, CategoriePizza.VIANDE);
		lesPizzas.add(p);
		p =  new Pizza(3,"FRO", "La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE);
		lesPizzas.add(p);
		p =   new Pizza(4,"CAN", "La cannibale",12.50, CategoriePizza.VIANDE);
		lesPizzas.add(p);
		p =  new Pizza(5,"SAV", "La Savoyarde",13.00, CategoriePizza.VIANDE);
		lesPizzas.add(p);
		p =  new Pizza(6,"ORI", "L'Orientale",13.50, CategoriePizza.VIANDE);
		lesPizzas.add(p);
		p =  new Pizza(7,"IND", "L'indienne",14.00, CategoriePizza.VIANDE);
		lesPizzas.add(p); 
	}

	/**
	 * Permet d'obtenir l'ensemble des pizzas
	 */
	@Override
	public List<Pizza> findAllPizzas() {
		return lesPizzas;
	}

	/**
	 * Permet d'enregistrer une nouvelle Pizza
	 * @param pizza la nouvelle pizza
	 */
	@Override
	public void saveNewPizza(Pizza pizza) {
		lesPizzas.add(pizza);
	}

	/**
	 * Permet de mettre à jour une pizza
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		if(pizzasExists(codePizza)) {
			int index = rechercherIndexPizza(codePizza);
			lesPizzas.set(index, pizza);
		}

	}

	/**
	 * Permet de supprimer une pizza
	 */
	@Override
	public void deletePizza(String codePizza) {
		if(pizzasExists(codePizza)) {
			int index = rechercherIndexPizza(codePizza);
			lesPizzas.remove(index);
		}
		

	}

	/**
	 * Permet de trouver une pizza en fonction de son code
	 */
	@Override
	public Pizza findPizzaByCode(String codePizza) {
		
		return lesPizzas.get(rechercherIndexPizza(codePizza));
	}

	/**
	 * Permet de vérifier qu'une pizza existe en fonction de son code
	 */
	@Override
	public boolean pizzasExists(String codePizza) {
		for(Pizza p : lesPizzas) {
			if(p.getCode().equalsIgnoreCase(codePizza)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Permet de trouver l'index d'une pizza en fonction de son code
	 * @param codePizza le code de la pizza
	 * @return l'index de la pizza dans le tableau
	 */
	public int rechercherIndexPizza(String codePizza) {
		int index = -1;
		for(int i=0; i<lesPizzas.size();i++) {
			if(lesPizzas.get(i).getCode().equalsIgnoreCase(codePizza)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * Permet d'obtenir la liste des pizzas
	 * @return la liste des pizzas
	 */
	public List<Pizza> getLesPizzas() {
		return lesPizzas;
	}

	/**
	 * Permet de modifier la liste des Pizzas
	 * @param lesPizzas la nouvelle liste des Pizzas
	 */
	public void setLesPizzas(List<Pizza> lesPizzas) {
		this.lesPizzas = lesPizzas;
	}


	
	
}
