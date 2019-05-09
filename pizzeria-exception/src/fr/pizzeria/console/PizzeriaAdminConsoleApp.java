package fr.pizzeria.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
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
		PizzeriaAdminConsoleApp app = new PizzeriaAdminConsoleApp();
		PizzaMemDao daoPizza = new PizzaMemDao();
		Scanner scan = new Scanner(System.in);
		int choixUser = -1;
		MenuServiceFactory factory = new MenuServiceFactory();
		
		
		
		// Boucle de choix utilisateurs
		do {
			app.afficherMenu();
			System.out.println();
			try {
				choixUser = scan.nextInt();
				if(choixUser<1 || choixUser>4 && choixUser !=99) {
					System.out.println("Erreur de saisie");
					app.afficherMenu();
					choixUser=scan.nextInt();
					
				}
				else {
					factory.creerMenuService(daoPizza, scan, choixUser).executeUC();
					System.out.println();
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

