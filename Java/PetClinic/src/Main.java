import java.time.LocalDateTime;

import model.AdminStaff;
import model.Animal;
import model.Appointment;
import model.Customer;
import model.Invoice;
import model.MedicalRecord;
import model.PaymentMethod;
import model.Role;
import model.User;
import model.Veterinarian;
import registry.AppointmentRegistry;
import service.AnimalHealthService;
import service.AppointmentCalendarService;
import service.AuthenticationService;
import service.PaymentService;
import utils.AppointmentConflictException;


public class Main {

	public static void main(String[] args) {
		 // Creazione del registro degli appuntamenti
        AppointmentRegistry appointmentRegistry = new AppointmentRegistry();
        
        // Creazione del servizio di calendario per gli appuntamenti
        AppointmentCalendarService calendarService = new AppointmentCalendarService(appointmentRegistry);
        
        // Registrazione di un nuovo cliente
        User customerUser = new User("customer1", "password", Role.CUSTOMER);
        User customer =  AuthenticationService.registerUser(customerUser);
        
       // Tentativo di registrare nuovamente lo stesso cliente
        try {
            User customerUser1 = new User("customer1", "password", Role.CUSTOMER);
            User customer1 = AuthenticationService.registerUser(customerUser1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Stampa il messaggio di errore
        }
        
        User customerUser2 = new User("customer1", "password", Role.CUSTOMER);

        User customer2 = AuthenticationService.registerUser(customerUser2);        
        
        // Registrazione di un veterinario
        User vetUser = new User("vet1", "password", Role.VETERINARIAN);
        User veterinarian = AuthenticationService.registerUser(vetUser);
        
        
        // Creazione di un animale
        Animal animal = new Animal(1, "Fido", "Dog", "Labrador", "5", "Male", customer);
        
        // Creazione di un appuntamento
        LocalDateTime appointmentTime = LocalDateTime.now().plusDays(1);
        Appointment appointment = new Appointment.AppointmentBuilder()
                .setAnimal(animal)
                .setCustomer(customer)
                .setLocalDate(appointmentTime)
                .setBookingMethod("Online")
                .build();
        
        // Aggiunta dell'appuntamento al calendario
        try {
            if (calendarService.addAppointment(appointment)) {
                System.out.println("Appuntamento aggiunto con successo per " + customer.getUsername());
            }
        } catch (AppointmentConflictException e) {
            System.out.println(e.getMessage());
        }        
        // Tentativo di aggiungere un nuovo appuntamento nello stesso slot
        Appointment anotherAppointment = new Appointment.AppointmentBuilder()
                .setAnimal(animal)
                .setCustomer(customer)
                .setLocalDate(appointmentTime) // Stesso slot
                .setBookingMethod("Online")
                .build();
        
        // Aggiunta del secondo appuntamento
        try {
            if (calendarService.addAppointment(anotherAppointment)) {
                System.out.println("Appuntamento aggiunto con successo per " + customer.getUsername());
            }
        } catch (AppointmentConflictException e) {
            System.out.println(e.getMessage());
        }        
        // Calcolo del rischio per la salute dell'animale
        AnimalHealthService healthService = new AnimalHealthService();
        double healthRisk = healthService.calculateHealthRisk("dog", "labrador", 5, true, "none", "none", "none", "none");
        System.out.println("Rischio per la salute: " + healthService.getHealthRiskDescription(healthRisk));
        
        
        // Caso 1: Alto rischio
        double highRisk1 = healthService.calculateHealthRisk("dog", "labrador", 5, true, "none", "vomiting", "none", "none");
        System.out.println("Rischio per la salute (alto): " + healthService.getHealthRiskDescription(highRisk1));
        
        
        // Caso 2: Moderato rischio
        double lowRisk = healthService.calculateHealthRisk("dog", "labrador", 1, true, "none", "none", "none", "none");
        System.out.println("Rischio per la salute (basso): " + healthService.getHealthRiskDescription(lowRisk));
        
        
        // Generazione della fattura
        MedicalRecord medicalRecord = new MedicalRecord("none", "none", "none", "none", false, true, false, "Routine Check", 5, 80.0, "Low");
        PaymentService paymentService = new PaymentService();
        Invoice invoice = paymentService.generateInvoice(appointment, medicalRecord, PaymentMethod.CREDIT_CARD);
        
        // Completamento dell'appuntamento
        veterinarian.completeAppointment(appointment.getIdUnivoco(), new MedicalRecord("Routine Check", "none", "none", "none", false, true, false, "Routine Check", 5, 80.0, "Low"), appointmentRegistry);
        
        // Elaborazione del pagamento
        paymentService.processPayment(invoice);
        
        // Visualizzazione dello stato dell'appuntamento
        System.out.println("Stato dell'appuntamento: " + appointment.getStatus());
	}
}
