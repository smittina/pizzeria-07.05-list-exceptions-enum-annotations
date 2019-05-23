package fr.pizzeria.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaJDBCDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.factory.DaoFactory;
import fr.pizzeria.factory.MenuServiceFactory;

/**
 * Classe principale
 * @author Mélodie
 *
 */
public class PizzeriaAdminConsoleApp {

	/**
	 * Permet d'afficher le menu
	 */
	public void afficherMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("5. Initialiser la Base de Données des Pizzas");
		System.out.println("99. Sortir");
	}
	
	
	
	/**
	 * Permet de dire au Revoir
	 */
	public void direAuRevoir() {
		System.out.println("Au Revoir :(");		
	}
	
	public static void main(String[] args) {
		// Initialisations
		DaoFactory daoFactory = new DaoFactory();
		PizzeriaAdminConsoleApp app = new PizzeriaAdminConsoleApp();
		// DAO pour avoir accès à la liste des pizzas dans le cas d'une réinitialisation de la table
		IPizzaDao daoMem = daoFactory.creerDaoPizza(1);
		
		// Création du DAO utilisé
		/*
		 * Variable typeDao à changer pour changer de type de DAO
		 * 1 : Memoire
		 * 2 : JDBC
		 * 3 : JPA
		 */
		int typeDao = 1;
		IPizzaDao dao = daoFactory.creerDaoPizza(typeDao); 
		
		
		Scanner scan = new Scanner(System.in);
		int choixUser = -1;
		MenuServiceFactory factory = new MenuServiceFactory();
		
		
		
		// Boucle de choix utilisateurs
		do {
			app.afficherMenu();
			System.out.println();
			try {
				choixUser = scan.nextInt();
				if(choixUser<1 || choixUser>5 && choixUser !=99) {
					System.out.println("Erreur de saisie");
					app.afficherMenu();
					choixUser=scan.nextInt();
					
				}
				else {
					if(choixUser !=99) {
						factory.creerMenuService(dao, daoMem, scan, choixUser).executeUC();
						System.out.println();
						
					}
				}
			}
			catch(InputMismatchException e) {
				System.out.println("Attention, vous devez saisir un nombre uniquement");
				scan.next();
			}
			catch(PizzaException e ) {
				System.out.println(e.getMessage());
				System.out.println();
			}	
			
		}while(choixUser != 99);
		app.direAuRevoir();
		
		

	}

}

