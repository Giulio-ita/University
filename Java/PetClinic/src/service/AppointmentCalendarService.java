package service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import model.Appointment;
import registry.AppointmentRegistry;
import utils.AppointmentConflictException;

public class AppointmentCalendarService {
	private final AppointmentRegistry registry;
	
	public AppointmentCalendarService(AppointmentRegistry registry) {
		this.registry = registry;
	}
	
	public boolean addAppointment(Appointment appointment) throws AppointmentConflictException {
	    if (appointment.getCustomer() == null) {
	        throw new IllegalArgumentException("Il cliente non può essere null");
	    }
	    if (!isSlotAvailable(appointment.getLocalDate())) {
	        throw new AppointmentConflictException("Slot già occupato per l'appuntamento di " + appointment.getCustomer().getUsername() + " alla data " + appointment.getLocalDate());
	    	//System.out.println("Slot già occupato per l'appuntamento di " + appointment.getCustomer().getUsername() + " alla data " + appointment.getLocalDate());
	    }
	    registry.addAppointment(appointment);
	    return true;
	}
    
    public boolean removeAppointment(Appointment appointment) {
        // Verifica se l'appuntamento esiste nel registro
        Optional<Appointment> existingAppointment = registry.findAppointmentByCustomerAndDate(appointment.getCustomer(), appointment.getLocalDate());
        
        if (existingAppointment.isPresent()) {
            // Se l'appuntamento esiste, rimuovilo dal registro
            registry.removeAppointment(appointment);
            return true; // Rimozione avvenuta con successo
        }
        return false; // L'appuntamento non esiste
    }
	
	public List<LocalDateTime> findAvailableSlots(LocalDateTime from, Predicate<LocalDateTime> filter, int limit){
		return generateTimeSlots(from).stream()
				.filter(this::isSlotAvailable)
				.filter(filter)
				.limit(limit)
				.collect(Collectors.toList());
	}
	// Java.unit.Period misura tempo in years-month-day(ISO 8601)
	private List<LocalDateTime> generateTimeSlots(LocalDateTime from){
		List<LocalDateTime> slots = new ArrayList<>();
		LocalDateTime current = from.withHour(8).withMinute(0).withSecond(0).withNano(0);
		// genera slot dalle 8:00 alle 20:00 per i giorni feriali
		while(current.getHour() < 20) {
			if(!isWeekend(current))
				slots.add(current);
			
			current = current.plusHours(1);
		}
		return slots;
	}
	
	private boolean isSlotAvailable(LocalDateTime dateTime) {
		return registry.getAllAppointments().stream()
				.noneMatch(a -> a.getLocalDate().equals(dateTime))
				&& isWithWorkingHours(dateTime);
	}
	public boolean isWeekend(LocalDateTime dateTime) {
	    DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
	    return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}
	private boolean isWithWorkingHours(LocalDateTime dateTime) {
	    int hour = dateTime.getHour();
	    return hour >= 8 && hour < 20; // Dalle 8:00 alle 19:59
	}
	
}
