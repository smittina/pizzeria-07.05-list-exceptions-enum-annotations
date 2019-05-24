package fr.pizzeria.model;

import fr.pizzeria.utils.ToString;

public enum CategoriePizza {
	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans_Viande");
	
	@ToString(uppercase=true)
	private String categorie;
	
	private CategoriePizza(String categorie) {
		this.categorie = categorie;
	}
	
	public String getCategorie() {
		return categorie;
	}
}
