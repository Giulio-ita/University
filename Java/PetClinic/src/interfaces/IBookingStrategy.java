package interfaces;

import java.time.LocalDateTime;

import model.Animal;
import model.Appointment;
import model.Customer;

public interface IBookingStrategy {
	Appointment bookAppointment(Customer customer, Animal animal, LocalDateTime daTime);
}
