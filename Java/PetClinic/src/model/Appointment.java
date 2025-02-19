package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import model.Animal;
import model.Veterinarian;
import model.VisitRequest;

public class Appointment {
	private String idUnivoco;
	private String bookingMethod;
    private Animal animal;
    private Veterinarian veterinarian;
    private LocalDateTime localDate;
    private String status;
	private User customer;
    private boolean emergency;

	// Costruttore privato per il builder
    private Appointment(AppointmentBuilder builder) {
    	this.idUnivoco = UUID.randomUUID().toString(); // Genera un ID univoco 
        this.bookingMethod = builder.bookingMethod;
        this.animal = builder.animal;
        this.veterinarian = builder.veterinarian;
        this.localDate = builder.localDate;
        this.status = "Scheduled"; // Imposta lo stato predefinito
        this.emergency = false;
        this.customer = builder.customer;
    }

	public static class AppointmentBuilder {
        private String bookingMethod;
        private Animal animal;
        private Veterinarian veterinarian;
        private LocalDateTime localDate;
        private User customer;
		private String status;
		private String bookingMetod;

        public AppointmentBuilder setAnimal(Animal animal) {
            this.animal = animal;
            return this;
        }
        
	      public AppointmentBuilder setCustomer(User customer2) {
	      this.customer = customer2;
	      return this;
	  }
        
    	public AppointmentBuilder setStatus(String status) {
    		this.status = status;
    		return this;
    	}

        public AppointmentBuilder setLocalDate(LocalDateTime localDate) {
            this.localDate = localDate;
            return this;
        }

        public AppointmentBuilder setBookingMethod(String bookingMethod) {
            this.bookingMetod = bookingMethod;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
    public String getIdUnivoco() {
		return idUnivoco;
	}

    public LocalDateTime getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDateTime localDate) {
		this.localDate = localDate;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void schedule(LocalDateTime appointmentDate) {
        this.localDate = appointmentDate;
        this.status = "Scheduled";
        
    }
    public void cancel() {
        this.status = "Cancelled";        
    }    

    public String getMethod() {
        return bookingMethod;
    }

    public String getStatus() {
		return status;
	}
    public void setStatus(String status) {
		this.status = status;
	}
    public boolean isEmergency() {
		return emergency;
	}

	public void setEmergency(boolean emergency) {
		this.emergency = emergency;
	}
}



