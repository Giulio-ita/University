package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Customer;
import model.Role;
import model.User;
import service.AuthenticationService;

class RegistrationCustomerTest2 {
	@Test
	public void RegistrationCustomerTest() {
		User user1 = new User("marioRossi", "ciao", Role.CUSTOMER);
		User user2 = new User("marioVerdi", "ciao", Role.VETERINARIAN);
		
		
		User customer = (Customer) AuthenticationService.registerUser(user1);
		
		assertEquals(user1.getRole(), Role.CUSTOMER);
		
		assertEquals(user2.getRole(), Role.VETERINARIAN);
		
		assertEquals(customer.getUsername(), "marioRossi");
		
	}

}
