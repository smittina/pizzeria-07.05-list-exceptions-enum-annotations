package fr.pizzeria.utils;

import java.lang.reflect.Field;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Classe Utilitaire pour les chaînes de caractères
 * @author Formation
 *
 */
public class StringUtils {

	/**
	 * Renvoie une chaine de caractère résumant les attributs d'une pizza pourvus d'une annotation ToString
	 * @param categoriePizza enumération correspondant à la catégorie de la pizza
	 * @param p la pizza
	 * @return la chaine de caractère souhaitée
	 */
	public static String getStringWithToString(CategoriePizza categoriePizza,Pizza p) {
		String str = "";
		Object value = null;
		// Pour tous les attributs de la Classe Pizza, on recherche ceux qui ont une une annotation @ToString
		for(Field field : Pizza.class.getDeclaredFields()) {
			field.setAccessible(true);
			if(field.isAnnotationPresent(ToString.class)) {
				// On récupère l'annotation et ses différentes caractéristiques
				ToString ts = field.getAnnotation(ToString.class);
				// On regarde si l'annotation @ToString possède l'attribut uppercase=true
				if(ts.uppercase()) {
					try {
						value = field.get(p);
						str +=value.toString().toUpperCase()+" -> ";
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				// On regarde si l'annotation @ToString possède l'attribut price=true
				else if(ts.price()) {
					try {
						value = field.get(p);
						str +="("+value.toString().toUpperCase()+"€) -- Catégorie "+categoriePizza.getCategorie();
					}
					catch(Exception e) {
						e.printStackTrace();	
					}
				}
				// Les autres cas
				else {
					try {
						value = field.get(p);
						str +=value.toString()+" ";
					}
					catch(Exception e) {
						e.printStackTrace();	
					}
				}
				
			}
		}
		return str;
	}
}
