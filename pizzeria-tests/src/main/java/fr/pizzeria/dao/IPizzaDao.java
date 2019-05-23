package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.model.Pizza;

/**
 * Interface pour DAO Pizza
 * @author Formation
 *
 */
public interface IPizzaDao {
	
	public abstract void initialiserPizzas(List<Pizza> lesPizzas);
	/**
	 * Permet de trouver toutes les pizzas
	 * @return la liste des pizzas
	 */
	public abstract List<Pizza> findAllPizzas();
	/**
	 * Permet de sauvegarder une nouvelle pizza
	 * @param pizza la nouvelle pizza à sauvegarder
	 */
	public abstract void saveNewPizza(Pizza pizza);
	/**
	 * Permet de mettre à jour une pizza
	 * @param codePizza le code de la pizza à modifier
	 * @param pizza la nouvelle pizza
	 */
	public abstract void updatePizza(String codePizza, Pizza pizza);
	/**
	 * Permet de supprimer une pizza en fonction de son code 
	 * @param codePizza le code de la pizza à supprimer
	 */
	public abstract void deletePizza(String codePizza);
	/**
	 * Permet de trouver une pizza en fonction de son code
	 * @param codePizza le code de la pizza
	 * @return la Pizza recherchée
	 */
	public abstract Pizza findPizzaByCode(String codePizza);
	/**
	 * Permet de savoir si une pizza existe ou non dans le tableau de pizza
	 * @param codePizza le code de la pizza
	 * @return true si elle existe, false sinon
	 */
	public abstract boolean pizzasExists(String codePizza);
	
}
