package fr.pizzeria.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.PropertiesLoader;

public class PizzaJDBCDao implements IPizzaDao {
	
	private Connection connection;
	private Properties properties;
	
	public PizzaJDBCDao() {
		connection = null;
		properties = PropertiesLoader.loadProperties("C:\\Users\\Formation\\Documents\\WorkSpace07.05\\pizzeria-jdbc\\src\\fr\\pizzeria\\ressources\\jdbc.properties");
	}
	
	private void connecterJDBC() {		
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("mdp"));

		} catch (SQLException e) {
			System.out.println("Problème de connexion -- \n Vérifiez vos informations dans le fichier jdbc.properties");
			e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private void fermerJDBC() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialiserPizzas(List<Pizza> lesPizzas) {
		connecterJDBC();
	
		
		PreparedStatement insertAllPizzasSt = null;
		Statement statement = null;
		try {
			// Drop if exists
			statement = connection.createStatement();
			statement.execute("DROP TABLE IF EXISTS pizza");
			// Create table
			String sql = "CREATE TABLE pizza("
					+ "ID INT NOT NULL PRIMARY KEY,"
					+ "CODE VARCHAR(10),"
					+ "LIBELLE VARCHAR(50),"
					+ "PRIX DOUBLE,"
					+ "CATEGORIE VARCHAR(50))";
			statement.execute(sql);
			statement.close();
			// Insertion Table

			insertAllPizzasSt = connection.prepareStatement("INSERT INTO pizza VALUES (?,?,?,?,?)");
			for(Pizza p : lesPizzas) {
				insertAllPizzasSt.setInt(1, p.getId());
				insertAllPizzasSt.setString(2, p.getCode());
				insertAllPizzasSt.setString(3, p.getLibelle());
				insertAllPizzasSt.setDouble(4, p.getPrix());
				insertAllPizzasSt.setString(5, p.getCategorie().toUpperCase());
				insertAllPizzasSt.executeUpdate();
			}
			insertAllPizzasSt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fermerJDBC();
	}
	

	@Override
	public List<Pizza> findAllPizzas() {
		connecterJDBC();
		List<Pizza> lesPizzas = new ArrayList<>();
		PreparedStatement findAllPizzasSt = null;
		ResultSet results = null;
		Pizza p = null;
		int id=-1;
		String code = "";
		String libelle = "";
		double prix = -1;
		String categorie = "";
		// Récupération des données
		try {
			findAllPizzasSt = connection.prepareStatement("SELECT * FROM pizza");
			results = findAllPizzasSt.executeQuery();
			// Traitement des données
			while(results.next()) {
				id = results.getInt("ID");
				code = results.getString("CODE");
				libelle = results.getString("LIBELLE");
				prix = results.getDouble("PRIX");
				categorie = results.getString("CATEGORIE");
				switch(categorie) {
				case "VIANDE":
					p = new Pizza(id, code, libelle, prix, CategoriePizza.VIANDE);
					break;
				case "POISSON":
					p = new Pizza(id, code, libelle, prix, CategoriePizza.POISSON);
					break;
				case "SANS_VIANDE":
					p = new Pizza(id, code, libelle, prix, CategoriePizza.SANS_VIANDE);
					break;
				default:
					System.out.println("Vous n'avez pas choisi une catégorie valide");
				}
				lesPizzas.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fermerJDBC();
		}
		
		return lesPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		connecterJDBC();
		PreparedStatement insertNewPizzaSt = null;
		try {
			insertNewPizzaSt = connection.prepareStatement("INSERT INTO pizza VALUES(?,?,?,?,?)");
			insertNewPizzaSt.setInt(1, pizza.getId());
			insertNewPizzaSt.setString(2, pizza.getCode());
			insertNewPizzaSt.setString(3, pizza.getLibelle());
			insertNewPizzaSt.setDouble(4, pizza.getPrix());
			insertNewPizzaSt.setString(5, pizza.getCategorie().toUpperCase());
			insertNewPizzaSt.executeUpdate();
			insertNewPizzaSt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				insertNewPizzaSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fermerJDBC();
		}
		

	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		connecterJDBC();
		PreparedStatement updatePizzaSt = null;
		try {
			updatePizzaSt = connection.prepareStatement("UPDATE pizza SET ID = ?, CODE = ?, LIBELLE = ?, PRIX = ?, CATEGORIE = ? WHERE code = ?");
			updatePizzaSt.setInt(1, pizza.getId());
			updatePizzaSt.setString(2, pizza.getCode());
			updatePizzaSt.setString(3, pizza.getLibelle());
			updatePizzaSt.setDouble(4, pizza.getPrix());
			updatePizzaSt.setString(5, pizza.getCategorie().toUpperCase());
			updatePizzaSt.setString(6, codePizza);
			updatePizzaSt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				updatePizzaSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fermerJDBC();

		}

	}

	@Override
	public void deletePizza(String codePizza) {
		connecterJDBC();
		PreparedStatement deletePizzaSt = null;
		try {
			deletePizzaSt = connection.prepareStatement("DELETE FROM pizza WHERE code = ?");
			deletePizzaSt.setString(1, codePizza);
			deletePizzaSt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				deletePizzaSt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fermerJDBC();

		}

	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		connecterJDBC();
		PreparedStatement selectPizzaSt = null;
		Pizza p = null;
		ResultSet result = null;
		int id = -1;
		String code = "";
		String libelle = "";
		double prix = -1;
		String categorie = "";
		try {
			selectPizzaSt = connection.prepareStatement("SELECT * FROM pizza WHERE CODE = ?");
			selectPizzaSt.setString(1, codePizza);
			result = selectPizzaSt.executeQuery();
			while(result.next()) {
				
			
			id = result.getInt("ID");
			code = result.getString("CODE");
			libelle = result.getString("LIBELLE");
			prix = result.getDouble("PRIX");
			categorie = result.getString("CATEGORIE");
			switch(categorie) {
			case "VIANDE":
				p = new Pizza(id, code, libelle, prix, CategoriePizza.VIANDE);
				break;
			case "POISSON":
				p = new Pizza(id, code, libelle, prix, CategoriePizza.POISSON);
				break;
			case "SANS_VIANDE":
				p = new Pizza(id, code, libelle, prix, CategoriePizza.SANS_VIANDE);
				break;
			default:
				System.out.println("Vous n'avez pas choisi une catégorie valide");
			}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			fermerJDBC();

		}
		return p;
	}

	@Override
	public boolean pizzasExists(String codePizza) {
		Pizza p = findPizzaByCode(codePizza);
		if(p != null) {
			return true;
		}		
		return false;
	}

}
