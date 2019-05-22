package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.NoPizzaException;
import fr.pizzeria.exceptions.PizzaException;

/**
 * Service permettant de supprimer une pizza 
 * @author Formation
 *
 */
public class SupprimerPizzaService extends MenuService {

	/**
	 * Constructeur
	 * @param daoPizza Dao Pizza
	 * @param scanner Scanner permettant à l'utilisateur d'indiquer ses choix
	 * @param menu Autre menu pouvant intervenir
	 */
	public SupprimerPizzaService(IPizzaDao daoPizza, Scanner scanner,  MenuService menu) {
		super(daoPizza, scanner, menu);
	}
	
	/**
	 * Permet de supprimer une pizza de la liste des pizzas connues
	 */
	@Override
	public void executeUC() throws DeletePizzaException, NoPizzaException {
		// Déclarations et Initialisations des variables locales
		String code = "";

		System.out.println("SUPPRESSION D'UNE PIZZA");
		//Affichage de la liste des pizzas
		try {
			getMenu().executeUC();
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		if(getDao().findAllPizzas().size()!=0) {
			while(!getDao().pizzasExists(code)) {
				System.out.println("Veuillez choisir le code de la pizza à supprimer : ");
				code = getScanner().next();
				
				if(!getDao().pizzasExists(code)) {
					throw new DeletePizzaException("Le code saisit n'existe pas");
					
				}
			}
			// Suppression via Dao
			getDao().deletePizza(code);
		}
		else {
			throw new NoPizzaException("Il n'y a plus de pizzas à supprimer");
		}
		
		

	}

}
