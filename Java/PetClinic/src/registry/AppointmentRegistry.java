package registry;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Appointment;
import model.Customer;
import model.User;

public class AppointmentRegistry {
	private List<Appointment> appointments;
	
	public AppointmentRegistry() {
		this.appointments = new ArrayList<>();
	}
	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}
	public void removeAppointment(Appointment appointment) {
		this.appointments.remove(appointment);
	}
	public List<Appointment> getAllAppointments(){
		return new ArrayList<>(appointments);
	}
	  public Optional<Appointment> getAppointmentById(String idUnivoco) {
	        return appointments.stream()
	                .filter(appointment -> appointment.getIdUnivoco().equals(idUnivoco))
	                .findFirst();
	    }
	public Optional<Appointment> findAppointmentByCustomerAndDate(User user, LocalDateTime dateTime){
		return appointments.stream()
				.filter(a->a.getCustomer().equals(user) && a.getLocalDate().equals(dateTime))
				.findFirst();
	}
	
}
