package test.pizzeria.services;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exceptions.NoPizzaException;
import fr.pizzeria.services.ListerPizzasService;
import junit.framework.Assert;

public class TestListerPizzasService {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Test//(expected=NoPizzaException.class)
	public void testExecuteUCNoPizza() throws NoPizzaException {
		PizzaMemDao dao = new PizzaMemDao();
		dao.deletePizza("PEP");
		dao.deletePizza("MAR");
		dao.deletePizza("REIN");
		dao.deletePizza("FRO");
		dao.deletePizza("CAN");
		dao.deletePizza("SAV");
		dao.deletePizza("ORI");
		dao.deletePizza("IND"); 
		ListerPizzasService service = new ListerPizzasService(dao);
		thrown.expect(NoPizzaException.class);
		service.executeUC();
			
		
		
	}

}
