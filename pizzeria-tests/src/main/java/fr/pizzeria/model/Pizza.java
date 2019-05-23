package fr.pizzeria.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.StringUtils;
import fr.pizzeria.utils.ToString;

/**
 * Classe représentant une pizza
 * @author Formation
 *
 */

@Entity
@Table(name="pizza")
public class Pizza {

	@Id
	private int id;
	
	@Column(name="CODE")
	@ToString(uppercase=true)
	private String code;
	
	@Column(name="LIBELLE")
	@ToString
	private String libelle;
	
	@Column(name="PRIX")
	@ToString(price=true)
	@Rule(min=5, max=30)
	private double prix;
	/**
	 * Compteur du nombre de Pizzas crées
	 */
	private static int compteur = 0;
	
	@Column(name="CATEGORIE")
	@Enumerated(EnumType.STRING)
	private CategoriePizza categoriePizza;
	
	public Pizza() {
		
	}
	
	/**
	 * Constructeur
	 * @param code code de la pizza
	 * @param libelle libelle de la pizza
	 * @param prix prix de la pizza
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categoriePizza) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.id = compteur;
		compteur++;
		
		this.categoriePizza = categoriePizza;
	}
	
	/**
	 * Constructeur 
	 * @param id identifiant de la pizza
	 * @param code code de la pizza
	 * @param libelle libelle de la pizza
	 * @param prix prix de la pizza
	 */
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza categoriePizza) {
		if(id > compteur) {
			compteur = id+1;
		}
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		
		this.categoriePizza = categoriePizza;
	}
	
	
	
	// GETTERS & SETTERS

	@Override
	public String toString() {
		return StringUtils.getStringWithToString(categoriePizza, this);
	}

	/**
	 * Permet d'obtenir l'Id de la pizza
	 * @return l'id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Permet de modifier l'id de la pizza
	 * @param id le nouvel id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Permet d'obtenir le code de la pizza
	 * @return le code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Permet de modifier le code de la pizza
	 * @param code le nouveau code de la pizza
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Permet d'obtenir le libelle de la pizza
	 * @return le libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Permet de modifier le libelle de la pizza
	 * @param libelle le nouveau libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Permet d'obtenir le prix de la pizza
	 * @return le prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Permet de modifier le prix de la pizza
	 * @param prix le nouveau prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Permet d'obtenir le compteur du nombre de pizza créés
	 * @return le nombre de pizzas créés
	 */
	public static int getCompteur() {
		return compteur;
	}

	/**
	 * Permet de modifier le nombre de pizza créés
	 * @param compteur le nouveau nombre de pizza créés
	 */
	public static void setCompteur(int compteur) {
		Pizza.compteur = compteur;
	}
	
	public String getCategorie() {
		String categorie = categoriePizza.getCategorie();
		return categorie;
	}
	
		
	
}

