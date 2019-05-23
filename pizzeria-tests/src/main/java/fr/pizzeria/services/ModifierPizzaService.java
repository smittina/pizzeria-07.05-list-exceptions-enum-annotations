package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;

/**
 * Service permettant de modifier une pizza existante
 * @author Formation
 *
 */
public class ModifierPizzaService extends MenuService {

	/**
	 * Constructeur
	 * @param daoPizza Dao Pizza
	 * @param scanner Scanner permettant à l'utilisateur d'indiquer ses choix
	 * @param menu Autre menu pouvant intervenir
	 */
	public ModifierPizzaService(IPizzaDao daoPizza, Scanner scanner, MenuService menu) {
		super(daoPizza, scanner, menu );
	}
	
	/**
	 * Permet de modifier une pizza existante
	 * @throws PizzaException 
	 */
	@Override
	public void executeUC() throws PizzaException {
		String code = "";
		String newCode;
		String newLibelle;
		String categorie = "";
		double newPrix = -10;
		boolean ok = false;
		
		// Saisies utilisateurs
		System.out.println("MISE A JOUR D'UNE PIZZA");
		try {
			getMenu().executeUC();
		} catch (PizzaException e) {
			
		}
		System.out.println("Veuillez choisir le code de la pizza à modifier : ");
		code = getScanner().next();
		if(!getDao().pizzasExists(code)) {
			throw new UpdatePizzaException("Le code saisit n'existe pas -- Recommencez");
		}
		
		
		System.out.println("Veuillez saisir le nouveau code :");
		newCode = getScanner().next();
		System.out.println("Veuillez saisir le nouveau Nom (sans espaces)");
		newLibelle = getScanner().next();
		while(!ok) {
			System.out.println("Veuillez saisir le nouveau prix");
			try{
				newPrix = getScanner().nextDouble();
				ok = true;
			}
			catch (Exception e) {
				System.out.println("Attention, vous devez saisir un nombre décimal !");
				getScanner().next();
			}
		}
		/*if(newPrix <0) {
			throw new UpdatePizzaException("Le Prix saisi ne peut pas être négatif -- Recommencez");
		}*/
		Pizza p = null;
		ok = false;
		while(!ok) {
			System.out.println("Veuillez saisir la catégorie de la pizza : 1) VIANDE -- 2) POISSON -- 3) SANS_VIANDE");
			categorie = getScanner().next();
			switch(categorie) {
			case "VIANDE":
				p = new Pizza(newCode, newLibelle, newPrix, CategoriePizza.VIANDE);
				Validator.validatePizza(p);
				ok = true;
				break;
			case "POISSON":
				p = new Pizza(newCode, newLibelle, newPrix, CategoriePizza.POISSON);
				Validator.validatePizza(p);
				ok = true;
				break;
			case "SANS_VIANDE":
				p = new Pizza(newCode, newLibelle, newPrix, CategoriePizza.SANS_VIANDE);
				Validator.validatePizza(p);
				ok = true;
				break;
			default:
				System.out.println("Vous n'avez pas choisi une catégorie valide");
			}
		
		}

		
		// Modification via Dao
		getDao().updatePizza(code, p);

	}

}

