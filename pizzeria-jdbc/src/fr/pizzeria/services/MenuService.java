package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJDBCDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.exceptions.UpdatePizzaException;

/**
 * Classe abstraite représentant un service de Menu
 * @author Formation
 *
 */
public abstract class MenuService {
	
	/**
	 * Dao Pizza
	 */
	private PizzaMemDao daoPizza;
	
	private IPizzaDao dao;
	

	/**
	 * Scanner permettant à l'utilisateur d'indiquer ses choix
	 */
	private Scanner scanner;
	/**
	 * Autre menu pouvant intervenir
	 */
	private MenuService menu;
	

	
	public MenuService(IPizzaDao dao) {
		this.dao = dao;
		scanner = null;
		menu = null;
	}
	

	/**
	 * Constructeur
	 * @param daoPizza Dao Pizza
	 * @param scanner Scanner permettant à l'utilisateur d'indiquer ses choix
	 */
	public MenuService(IPizzaDao daoPizza, Scanner scanner) {
		this.dao = daoPizza;
		this.scanner = scanner;
		menu = null;
	}
	
	/**
	 * Constructeur
	 * @param daoPizza Dao Pizza
	 * @param scanner Scanner permettant à l'utilisateur d'indiquer ses choix
	 * @param menu Autre menu pouvant intervenir
	 */
	public MenuService(IPizzaDao daoPizza, Scanner scanner, MenuService menu) {
		this.dao = daoPizza;
		this.scanner = scanner;
		this.menu = menu;
	}
	
	public MenuService(IPizzaDao dao, PizzaMemDao daoPizza) {
		this.dao = dao;
		this.daoPizza = daoPizza;
		this.scanner = null;
		this.menu = null;
	}
	/**
	 * Permet d'exécuter des actions en fonction du MenuService
	 * @throws PizzaException 
	 */
	public abstract void executeUC() throws PizzaException;

	// GETTERS & SETTERS 
	
	/**
	 * Permet d'obtenir le Dao Pizza
	 * @return le dao Pizza
	 */
	public PizzaMemDao getDaoPizza() {
		return daoPizza;
	}

	/**
	 * Permet de modifier le Dao Pizza
	 * @param daoPizza le nouveau Dao Pizza
	 */
	public void setDaoPizza(PizzaMemDao daoPizza) {
		this.daoPizza = daoPizza;
	}

	/**
	 * Permet d'obtenir le scanner
	 * @return le scanner
	 */
	public Scanner getScanner() {
		return scanner;
	}

	/**
	 * Permet de modifier le scanner
	 * @param scanner le nouveau scanner
	 */
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Permet d'obtenir le menuService
	 * @return le MenuService
	 */
	public MenuService getMenu() {
		return menu;
	}

	/**
	 * Permet de modifier le MenuService
	 * @param menu le nouveau MenuService
	 */
	public void setMenu(MenuService menu) {
		this.menu = menu;
	}
	
	public IPizzaDao getDao() {
		return dao;
	}


	public void setDao(IPizzaDao dao) {
		this.dao = dao;
	}
	
	
}
