package fr.pizzeria.console;

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
		boolean ok = false;
		MenuServiceFactory factory = new MenuServiceFactory();
		
		//Premier affichage console
		app.afficherMenu();
		
		while(!ok) {
			try {
				choixUser = scan.nextInt();
				ok = true;
			}
			catch (Exception e) {
				System.out.println("Attention, vous devez saisir un nombre uniquement");
				scan.next();
			}
		}
		
		
		// Boucle de choix utilisateurs
		do {
			ok = false;
			if(choixUser<1 || choixUser>4 && choixUser !=99) {
				System.out.println("Erreur de saisie");
				app.afficherMenu();
				choixUser=scan.nextInt();
			}
			else {
				
				while(!ok) {
					System.out.println("While");
					try {
						factory.creerMenuService(daoPizza, scan, choixUser).executeUC();
						ok=true;
					} catch (PizzaException e) {
						System.out.println(e.getMessage());
						System.out.println();
					}
				}
				
				app.afficherMenu();
				choixUser=scan.nextInt();
			}
		}while(choixUser != 99);
		app.direAuRevoir();
		
		

	}

}

