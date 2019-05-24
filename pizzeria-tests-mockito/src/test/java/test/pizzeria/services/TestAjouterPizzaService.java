package test.pizzeria.services;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.rules.ExpectedException;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.PizzaException;
import fr.pizzeria.exceptions.SavePizzaException;
import fr.pizzeria.services.AjouterPizzaService;
import org.junit.*;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.util.Scanner;

public class TestAjouterPizzaService {
	
	PizzaMemDao dao;
	AjouterPizzaService service;
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		this.dao = new PizzaMemDao();
		this.service = new AjouterPizzaService(dao, new Scanner(System.in));
	}

	@Test
	public void testExecuteUC() {
		
		systemInMock.provideLines("ANCH","Anchois","9","POISSON");
		try {
			service.executeUC();
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(9, dao.getLesPizzas().size());
		Assert.assertEquals(8,dao.getLesPizzas().get(8).getId());
		Assert.assertEquals("ANCH",dao.getLesPizzas().get(8).getCode());
		Assert.assertEquals("Anchois",dao.getLesPizzas().get(8).getLibelle());
		Assert.assertEquals(9,dao.getLesPizzas().get(8).getPrix(),0);
		Assert.assertEquals("Poisson",dao.getLesPizzas().get(8).getCategorie());
	}
	
	@Test
	public void testExecuteUCWithCodeAlreadyKnown() throws SavePizzaException, PizzaException{
		systemInMock.provideLines("PEP","Pépéroni","12.50","VIANDE");
		thrown.expect(SavePizzaException.class);
		service.executeUC();		
	}
}
