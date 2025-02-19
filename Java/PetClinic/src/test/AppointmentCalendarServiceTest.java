package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import registry.AppointmentRegistry;
import service.AppointmentCalendarService;

class AppointmentCalendarServiceTest {

	private AppointmentRegistry registry;
    private AppointmentCalendarService service;

    @BeforeEach
    void setUp() {
        registry = new AppointmentRegistry();
        service = new AppointmentCalendarService(registry);
    }

    @Test
    void testFindAvailableSlots_NoAppointments() {
        LocalDateTime from = LocalDateTime.of(2023, Month.MARCH, 15, 9, 0);
        List<LocalDateTime> availableSlots = service.findAvailableSlots(from, dateTime -> true, 5);
        
        assertEquals(5, availableSlots.size());
        assertTrue(availableSlots.contains(LocalDateTime.of(2023, Month.MARCH, 15, 9, 0)));
        assertTrue(availableSlots.contains(LocalDateTime.of(2023, Month.MARCH, 15, 10, 0)));
        assertTrue(availableSlots.contains(LocalDateTime.of(2023, Month.MARCH, 15, 11, 0)));
        assertTrue(availableSlots.contains(LocalDateTime.of(2023, Month.MARCH, 15, 12, 0)));
        //assertTrue(availableSlots.contains(LocalDateTime.of(2023, Month.MARCH, 15, 13, 0)));
    }

    @Test
    void testFindAvailableSlots_FilterPredicate() {
        LocalDateTime from = LocalDateTime.of(2023, Month.MARCH, 15, 9, 0);
        Predicate<LocalDateTime> filter = dateTime -> dateTime.getHour() >= 12; // Filtra solo gli slot dopo le 12:00

        List<LocalDateTime> availableSlots = service.findAvailableSlots(from, filter, 5);
        
        assertEquals(5, availableSlots.size());
        assertTrue(availableSlots.stream().allMatch(slot -> slot.getHour() >= 12)); // Tutti gli slot devono essere dopo le 12:00
    }

    @Test
    void testIsWeekend() {
        assertTrue(service.isWeekend(LocalDateTime.of(2023, Month.MARCH, 18, 9, 0))); // Sabato
        assertTrue(service.isWeekend(LocalDateTime.of(2023, Month.MARCH, 19, 9, 0))); // Domenica
        assertFalse(service.isWeekend(LocalDateTime.of(2023, Month.MARCH, 20, 9, 0))); // Lunedì
    }

}
