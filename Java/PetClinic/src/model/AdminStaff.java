package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import service.AppointmentCalendarService;

public class AdminStaff {
    private AppointmentCalendarService calendarService;

    public AdminStaff(AppointmentCalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // Metodo per vedere la disponibilità di uno specifico giorno
    public void viewAvailableSlotsForDay(LocalDate day) {
        System.out.println("Disponibilità per il giorno: " + day);
        //calendarService.printAvailabilityForDay(day);
        calendarService.findAvailableSlots(null, null, 0);        
    }

}
