package test.pizzeria.dao;

import org.junit.Test;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import org.junit.Assert;
import org.junit.Before;

/**
 * Classe de Test à PizzaMemDao
 * @author Formation
 *
 */
public class TestPizzaMemDao {
	
	/**
	 * L'instance à tester
	 */
	private PizzaMemDao dao;
	
	/**
	 * Opérations à réaliser avant le démarrage de chaque test unitaire
	 */
	@Before
	public void setUp() {
		this.dao = new PizzaMemDao();
	}
	
	/**
	 * Test qui permet de vérifier que toutes les pizzas sont bien renvoyées
	 */
	@Test
	public void testFindAllPizzas() {
		
		Assert.assertEquals(8, dao.getLesPizzas().size());
		Assert.assertEquals(0, dao.getLesPizzas().get(0).getId());
		Assert.assertEquals("PEP", dao.getLesPizzas().get(0).getCode());
		Assert.assertEquals("Pépéroni", dao.getLesPizzas().get(0).getLibelle());
		Assert.assertEquals(12.50, dao.getLesPizzas().get(0).getPrix(),0);
		Assert.assertEquals("Viande", dao.getLesPizzas().get(0).getCategorie());
		
		Assert.assertEquals(7, dao.getLesPizzas().get(7).getId());
	}
	
	/**
	 * Test de la sauvegarder d'une pizza dans le dao
	 */
	@Test
	public void testSaveNewPizza() {
		Pizza p = new Pizza(8,"ANCH","Anchois",9,CategoriePizza.POISSON);
		
		dao.saveNewPizza(p);
		
		Assert.assertEquals(9, dao.getLesPizzas().size());
		Assert.assertEquals(8, dao.getLesPizzas().get(8).getId());
		Assert.assertEquals("ANCH", dao.getLesPizzas().get(8).getCode());
		Assert.assertEquals("Anchois", dao.getLesPizzas().get(8).getLibelle());
		Assert.assertEquals(9, dao.getLesPizzas().get(8).getPrix(),0);
		Assert.assertEquals("Poisson", dao.getLesPizzas().get(8).getCategorie());
		
	}
	
	/**
	 * Test de la sauvegarde d'une pizza NULL dans le dao
	 */
	@Test
	public void testNullSaveNewPizza() {
		Pizza p = null;
		
		dao.saveNewPizza(p);
		Assert.assertEquals(8, dao.getLesPizzas().size());
		
	}
	
	/**
	 * Test vérifiant la mise à jour d'une pizza du dao
	 */
	@Test
	public void testUpdatePizza() {
		Pizza newPizza = new Pizza(0,"ANCH","Anchois",9,CategoriePizza.POISSON);
		dao.updatePizza("PEP", newPizza);
		Assert.assertEquals(8, dao.getLesPizzas().size());
		Assert.assertEquals(0, dao.getLesPizzas().get(0).getId());
		Assert.assertEquals("ANCH", dao.getLesPizzas().get(0).getCode());
		Assert.assertEquals("Anchois", dao.getLesPizzas().get(0).getLibelle());
		Assert.assertEquals(9, dao.getLesPizzas().get(0).getPrix(),0);
		Assert.assertEquals("Poisson", dao.getLesPizzas().get(0).getCategorie());

	}
	
	/**
	 * Teste vérifiant le comportement de la méthode lorsqu'on tente de mettre à jour une pizza par un Null
	 */
	@Test
	public void testUpdatePizzaNull() {
		dao.updatePizza("PEP", null);
		Assert.assertEquals(8, dao.getLesPizzas().size());
		Assert.assertEquals(0, dao.getLesPizzas().get(0).getId());
		Assert.assertEquals("PEP", dao.getLesPizzas().get(0).getCode());
		Assert.assertEquals("Pépéroni", dao.getLesPizzas().get(0).getLibelle());
		Assert.assertEquals(12.50, dao.getLesPizzas().get(0).getPrix(),0);
		Assert.assertEquals("Viande", dao.getLesPizzas().get(0).getCategorie());
	}
	
	/**
	 * Teste vérifiant le comportement de la méthode lorsqu'on tente de mettre à jour une pizza dont le code n'existe pas
	 */
	@Test 
	public void testUpdatePizzaCodeUnknown() {
		Pizza newPizza = new Pizza(0,"ANCH","Anchois",9,CategoriePizza.POISSON);
		dao.updatePizza("ANCH", newPizza);
		Assert.assertEquals(8, dao.getLesPizzas().size());
		Assert.assertEquals(0, dao.getLesPizzas().get(0).getId());
		Assert.assertEquals("PEP", dao.getLesPizzas().get(0).getCode());
		Assert.assertEquals("Pépéroni", dao.getLesPizzas().get(0).getLibelle());
		Assert.assertEquals(12.50, dao.getLesPizzas().get(0).getPrix(),0);
		Assert.assertEquals("Viande", dao.getLesPizzas().get(0).getCategorie());
	}
	
	/**
	 * Permet de vérifier le comportement de la méthode lorsqu'on tente de supprimer une pizza
	 */
	@Test
	public void testDeletePizza() {
		dao.deletePizza("PEP");
		Assert.assertEquals(7, dao.getLesPizzas().size());
		Assert.assertEquals("IND", dao.getLesPizzas().get(6).getCode());
		Assert.assertEquals("L'indienne", dao.getLesPizzas().get(6).getLibelle());
		Assert.assertEquals(14.00, dao.getLesPizzas().get(6).getPrix(),0);
		Assert.assertEquals("Viande", dao.getLesPizzas().get(6).getCategorie());
		Assert.assertEquals(7, dao.getLesPizzas().get(6).getId());
	}
	
	/**
	 * Permet de vérifier la recherche d'index de la pizza
	 */
	@Test
	public void testRechercherIndexPizza() {
		int indexObtenu = dao.rechercherIndexPizza("IND");
		Assert.assertEquals(7, indexObtenu);
		indexObtenu = dao.rechercherIndexPizza("PEP");
		Assert.assertEquals(0, indexObtenu);
		
	}

	/**
	 * Teste le comportement lorsqu'on tente de trouver une pizza via son code
	 */
	@Test
	public void testFindPizzaByCode() {
		Pizza laPizza = dao.findPizzaByCode("PEP");
		Assert.assertEquals(0,laPizza.getId());
		Assert.assertEquals("PEP",laPizza.getCode());
		Assert.assertEquals("Pépéroni", laPizza.getLibelle());
		Assert.assertEquals(12.50, laPizza.getPrix(),0);
		Assert.assertEquals("Viande", laPizza.getCategorie());
	}
	
	/**
	 * Teste le comportement de la méthode lorsque le code de la pizza est inconnu du dao
	 */
	@Test
	public void testFindPizzaByCodeUnknown() {
		Pizza laPizza = dao.findPizzaByCode("ANCH");
		Assert.assertNull(laPizza);
	}


	/**
	 * Teste qu'une pizza existe bien en fonction de son code, connu
	 */
	@Test
	public void testPizzasExists() {
		Assert.assertTrue(dao.pizzasExists("PEP"));
		Assert.assertTrue(dao.pizzasExists("MAR"));
		Assert.assertTrue(dao.pizzasExists("IND"));
		Assert.assertTrue(dao.pizzasExists("ORI"));
	}
	
	/**
	 * Teste qu'une pizza avec un code inconnu, n'existe pas
	 */
	@Test
	public void testPizzasNotExist() {
		Assert.assertFalse(dao.pizzasExists("ANCH"));
		Assert.assertFalse(dao.pizzasExists("KIK"));
		Assert.assertFalse(dao.pizzasExists("LALA"));
	}
	


	


}
