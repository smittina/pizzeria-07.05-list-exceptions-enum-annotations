package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.model.Pizza;

/**
 * Service permettant d'ajouter une pizza dans le Menu
 * @author Formation
 *
 */
public class AjouterPizzaService extends MenuService {
	
	
	/**
	 * Constructeur 
	 * @param daoPizza Dao Pizza
	 * @param scanner Scanner pour que l'utilisateur puisse donner ses choix
	 */
	public AjouterPizzaService(PizzaMemDao daoPizza, Scanner scanner) {
		super(daoPizza, scanner);
	}
	
	/**
	 * Permet d'ajouter une nouvelle pizza au tableau des pizza
	 */
	@Override
	public void executeUC() throws SavePizzaException{
		// Récupération des données auprès de l'utilisateur
		String code = "";
		String nom = "";
		double prix = -10;
		boolean ok = false;
		System.out.println("AJOUTER UNE NOUVELLE PIZZA");
		System.out.println("Veuillez saisir le code : ");
		code = getScanner().next();
		if(getDaoPizza().pizzasExists(code)){
			throw new SavePizzaException("Le code saisi existe déjà pour une autre pizza -- Recommencez");
		}
		System.out.println("Veuillez saisir le nom (sans Espace)");
		nom = getScanner().next();
		while(!ok) {
			try{
				System.out.println("Veuillez saisir son prix (€)");
				prix = getScanner().nextDouble();
				ok = true;
			}
			catch (Exception e){
				System.out.println("Attention, vous devez saisir un nombre décimal !");
				getScanner().next();
			}
		}
		
		if(prix <0) {
			throw new SavePizzaException("Le Prix ne peut pas être négatif -- Recommencez");
		}
		
		
		// Ajout via Dao
		Pizza p = new Pizza(code, nom, prix);
		getDaoPizza().saveNewPizza(p);

	}

}

