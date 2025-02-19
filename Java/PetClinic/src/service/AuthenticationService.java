package service;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Role;
import model.User;
import model.Veterinarian;

public class AuthenticationService {

    private static List<User> registeredUsers = new ArrayList<>();

    public static boolean authenticateUser(User user, Role role) {
        return user.getRole().equals(role);
    }

    public static User registerUser(User user) {
        try {
            if (registeredUsers.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
                throw new IllegalArgumentException("Utente già registrato con username: " + user.getUsername());
            }

            if (authenticateUser(user, Role.CUSTOMER)) {
                Customer newCustomer = new Customer(user.getUsername(), "pass", user.getRole());
                registeredUsers.add(newCustomer);
                return newCustomer;
            }
            if (authenticateUser(user, Role.VETERINARIAN)) {
                Veterinarian newVeterinarian = new Veterinarian(user.getUsername(), "pass", user.getRole());
                registeredUsers.add(newVeterinarian);
                return newVeterinarian;
            } else {
                throw new SecurityException("Solo utenti con ruolo OWNER possono registrarsi.");
            }
        } catch (IllegalArgumentException | SecurityException e) {
            System.out.println(e.getMessage()); // Stampa il messaggio di errore
            return null; // Restituisci null per indicare che la registrazione non è riuscita
        }
    }
}
