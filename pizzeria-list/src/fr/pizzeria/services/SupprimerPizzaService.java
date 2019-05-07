package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;

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
	public SupprimerPizzaService(PizzaMemDao daoPizza, Scanner scanner,  MenuService menu) {
		super(daoPizza, scanner, menu);
	}
	
	/**
	 * Permet de supprimer une pizza de la liste des pizzas connues
	 */
	@Override
	public void executeUC() {
		// Déclarations et Initialisations des variables locales
		String code = "";

		System.out.println("SUPPRESSION D'UNE PIZZA");
		//Affichage de la liste des pizzas
		getMenu().executeUC();
		while(!getDaoPizza().pizzasExists(code)) {
			System.out.println("Veuillez choisir le code de la pizza à supprimer : ");
			code = getScanner().next();
			
			if(!getDaoPizza().pizzasExists(code)) {
				System.out.println("Le Code Pizza saisi n'existe pas");
			}
		}
		// Suppression via Dao
		getDaoPizza().deletePizza(code);

	}

}
