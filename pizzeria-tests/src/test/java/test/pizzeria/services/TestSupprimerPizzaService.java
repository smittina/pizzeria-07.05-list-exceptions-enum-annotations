package test.pizzeria.services;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.DeletePizzaException;
import fr.pizzeria.exceptions.NoPizzaException;
import fr.pizzeria.services.ListerPizzasService;
import fr.pizzeria.services.SupprimerPizzaService;
import org.junit.Assert;

public class TestSupprimerPizzaService {
	
	ListerPizzasService serviceLister;
	SupprimerPizzaService service;
	PizzaMemDao dao;

	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	
	@Before
	public void setUp() {
		this.dao = new PizzaMemDao();
		this.serviceLister = new ListerPizzasService(dao);
		this.service = new SupprimerPizzaService(dao, new Scanner(System.in), serviceLister);
		
	}
	
	@Test
	public void testExecuteUc() {
		systemInMock.provideLines("IND");
		try {
			service.executeUC();
		} catch (DeletePizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoPizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(7, dao.getLesPizzas().size());
	}
}
