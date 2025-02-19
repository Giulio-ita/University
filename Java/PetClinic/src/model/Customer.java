package model;


public class Customer extends User {
	private String name;
	private String password;

	public Customer(String username, String password, Role role) {
		super(username, password, role.CUSTOMER);
		if(!role.equals(role.CUSTOMER))
			throw new SecurityException("Solo gli utenti con ruolo Customer possono accreditarsi");		
		if(username == null || password == null)
			throw new IllegalArgumentException("Username e password sono obbligatorie");		
        this.name = username;
        this.password = password;
    }

    public String toString() {
    	return "customer " ;
    }

	public String getUsername() {
		return name;
	}
}
