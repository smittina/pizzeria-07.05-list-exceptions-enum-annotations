package fr.pizzeria.utils;

import java.lang.reflect.Field;

import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Classe permettant de valider les instances d'une classe en fonction des annotations
 * @author Formation
 *
 */
public class Validator {

	/**
	 * Permet de valider une instance de la classe Pizza
	 * @param p l'instance Pizza
	 * @throws PizzaException envoie une exception de type PizzaException
	 */
	public static void validatePizza(Pizza p) throws PizzaException{
		// On parcourt l'intégralité des attributs de la classe Pizza
		for(Field field : Pizza.class.getDeclaredFields()) {
			field.setAccessible(true);
			//On regarde si l'annotation @Rule est présente
			if(field.isAnnotationPresent(Rule.class)) {
				Rule rule = field.getAnnotation(Rule.class);
				//On regarde la valeur de l'attribut min de @Rule
				if(rule.min()==5) {
					// Si l'instance n'est pas conforme à la règle, on lance une exception
					if(p.getPrix() <5) {
						throw new PizzaException("Erreur :Le prix doit être supérieur à 5€ -- Recommencez");
					}		
				}
				// On regarde la valeur de l'attribut max de @Rule
				if(rule.max()==30) {
					// Si l'instance n'est pas conforme à la règle, on lance une exception
					if(p.getPrix()>30) {
						throw new PizzaException("Erreur : Le prix doit être inférieur à 30€ -- Recommencez");
					}
				}
			}
		}
	}
	
	
}
