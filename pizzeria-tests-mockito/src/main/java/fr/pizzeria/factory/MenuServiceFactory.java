package fr.pizzeria.factory;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.services.AjouterPizzaService;
import fr.pizzeria.services.InitialiserPizzasService;
import fr.pizzeria.services.ListerPizzasService;
import fr.pizzeria.services.MenuService;
import fr.pizzeria.services.ModifierPizzaService;
import fr.pizzeria.services.SupprimerPizzaService;

/**
 * Factory de MenuService
 * @author Formation
 *
 */
public class MenuServiceFactory {

	/**
	 * Permet de créer un nouveau MenuService en fonction du choix de l'utilisateur
	 * @param daoPizza Dao de la Pizza
	 * @param scan Scanner pour que l'utilisateur puisse noter ses choix
	 * @param code choix de l'utilisateur concernant la création du Menu Service
	 * @return
	 */
	public MenuService creerMenuService(IPizzaDao daoPizza, IPizzaDao daoMem, Scanner scan, int code) {
		MenuService menu = null;
		switch(code) {
		case 1 : 
			menu = new ListerPizzasService(daoPizza);
			break;
		case 2: 
			menu = new AjouterPizzaService(daoPizza, scan);
			break;
		case 3:
			menu = new ModifierPizzaService(daoPizza, scan, creerMenuService(daoPizza,daoMem, scan,1));
			break;
		case 4:
			menu = new SupprimerPizzaService(daoPizza, scan, creerMenuService(daoPizza, daoMem, scan, 1));
			break;
		case 5:
			menu = new InitialiserPizzasService(daoPizza, daoMem);
			break;
		}
		return menu;
	}
}

