package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

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
	public ModifierPizzaService(PizzaMemDao daoPizza, Scanner scanner, MenuService menu) {
		super(daoPizza, scanner, menu );
	}
	
	/**
	 * Permet de modifier une pizza existante
	 */
	@Override
	public void executeUC() throws UpdatePizzaException {
		String code = "";
		String newCode;
		String newLibelle;
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
		if(!getDaoPizza().pizzasExists(code)) {
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
		if(newPrix <0) {
			throw new UpdatePizzaException("Le Prix saisi ne peut pas être négatif -- Recommencez");
		}
		
		// Modification via Dao
		Pizza p = new Pizza(newCode, newLibelle, newPrix);
		getDaoPizza().updatePizza(code, p);

	}

}

