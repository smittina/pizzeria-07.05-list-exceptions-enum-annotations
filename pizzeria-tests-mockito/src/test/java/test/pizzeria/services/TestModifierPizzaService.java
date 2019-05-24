package test.pizzeria.services;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.services.ListerPizzasService;
import fr.pizzeria.services.ModifierPizzaService;
import org.junit.Assert;

public class TestModifierPizzaService {
	
	ModifierPizzaService service;
	ListerPizzasService serviceLister;
	PizzaMemDao dao;
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUp() {
		this.dao = new PizzaMemDao();
		this.serviceLister = new ListerPizzasService(dao);
		this.service = new ModifierPizzaService(dao, new Scanner(System.in), serviceLister);
	}
	
	@Test
	public void testExecuteUC() {
		systemInMock.provideLines("PEP","PEPE","Pépéroni","9","VIANDE");
		try {
			service.executeUC();
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(8, dao.getLesPizzas().size());
		Assert.assertEquals(0, dao.getLesPizzas().get(0).getId());
		Assert.assertEquals("PEPE", dao.getLesPizzas().get(0).getCode());
		Assert.assertEquals("Pépéroni", dao.getLesPizzas().get(0).getLibelle());
		Assert.assertEquals(9, dao.getLesPizzas().get(0).getPrix(),0);
		Assert.assertEquals("Viande", dao.getLesPizzas().get(0).getCategorie());
	}
	
	// -------------------- TESTS AVEC MOCKITO ---------------------------
	
	@Test(expected=PizzaException.class)
	public void testExecuteUCPizzasNull() throws PizzaException{
		systemInMock.provideLines("PEP","PEPE","Pépéroni","9","VIANDE");
		PizzaMemDao mockedDao = Mockito.mock(PizzaMemDao.class);
		ModifierPizzaService serviceMocked = new ModifierPizzaService(mockedDao,new Scanner(System.in),new ListerPizzasService(mockedDao));
		// Simulation d'une liste de pizza vide avec Mockito
		Mockito.when(mockedDao.findAllPizzas()).thenReturn(new ArrayList<Pizza>());
		
		serviceMocked.executeUC();
	}
	
	@Test(expected=PizzaException.class)
	public void testExecuteUCListePizzasIncoherente() throws PizzaException{
		systemInMock.provideLines("PEP","PEPE","Pépéroni","9","VIANDE");
		PizzaMemDao mockedDao = Mockito.mock(PizzaMemDao.class);
		ModifierPizzaService serviceMocked = new ModifierPizzaService(mockedDao,new Scanner(System.in),new ListerPizzasService(mockedDao));
		
		// Simulation incohérente avec 1er élément NULL, 2nd élément code Pizza Null
		List<Pizza> pizzasTest = new ArrayList<Pizza>();
		Pizza p = null;
		pizzasTest.add(p);
		p = new Pizza(1,null,"PizzaTest",9,CategoriePizza.POISSON);
		pizzasTest.add(p);
		Mockito.when(mockedDao.findAllPizzas()).thenReturn(pizzasTest);
		
		// Lancement de la méthode à tester
		serviceMocked.executeUC();
	}
	
	

}
