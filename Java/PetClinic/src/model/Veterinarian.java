package model;

import java.util.Optional;

import registry.AppointmentRegistry;

public class Veterinarian extends User {
    private String username;
    private String password;
    private Role role;

    public Veterinarian(String username, String password, Role role) {
    	super(username, password, role);
    }

    public Appointment findAppointment(String idUnivoco, AppointmentRegistry appointmentRegistry) {
        return appointmentRegistry.getAppointmentById(idUnivoco).orElse(null);
    }

    public void completeAppointment(String idUnivoco, MedicalRecord medicalRecord, AppointmentRegistry appointmentRegistry) {
        Appointment appointment = findAppointment(idUnivoco, appointmentRegistry);
        if (appointment != null) {
            // Compila il MedicalRecord
            // (Assumendo che MedicalRecord abbia un metodo per essere compilato)
            // medicalRecord.compile(appointment);
            appointment.setStatus("completed");
            System.out.println("Appuntamento completato: " + appointment.getIdUnivoco());
        } else {
            System.out.println("Appuntamento non trovato.");
        }
    }
}
