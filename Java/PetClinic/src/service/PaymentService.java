package service;

import java.time.LocalDateTime;

import interfaces.IPaymentMethod;
import model.Appointment;
import model.Invoice;
import model.MedicalRecord;
import model.PaymentMethod;
import registry.AppointmentRegistry;
import utils.TreatmentCostCalculator;


import java.time.LocalDateTime;
import java.util.Optional;

import interfaces.IPaymentMethod;
import model.Appointment;
import model.Invoice;
import model.MedicalRecord;
import model.PaymentMethod;
import registry.AppointmentRegistry;
import utils.TreatmentCostCalculator;

public class PaymentService implements IPaymentMethod {
    private final double BASE_COST = 50.0;
    private double effectiveCost;

    public Invoice generateInvoice(Appointment appointment, MedicalRecord medicalRecord, PaymentMethod paymentMethod) {
        double treatmentCost = calculateTreatmentCost(appointment, medicalRecord);
        applyLoyaltyDiscount(medicalRecord, treatmentCost); // Sconto fedeltà
        applyCancellationPenalty(appointment, treatmentCost); // Penalità per cancellazione
        Invoice invoice = new Invoice(appointment, treatmentCost, paymentMethod);
        return invoice;
    }

    // Applica sconti fedeltà basati sullo storico del paziente
    private void applyLoyaltyDiscount(MedicalRecord medicalRecord, double cost) {
        if (medicalRecord.getAppointmentHistory() >= 10) 
            cost -= 20.0; // Sconto fedeltà per clienti con più di 10 appuntamenti
    }

    // Penalità per la cancellazione dell'appuntamento
    private void applyCancellationPenalty(Appointment appointment, double cost) {
        if (appointment.getLocalDate().isBefore(LocalDateTime.now().minusHours(24))) 
            cost += 30.0; // Penalità per cancellazioni all'ultimo minuto
    }

    // Calcolo del costo del trattamento
    private double calculateTreatmentCost(Appointment appointment, MedicalRecord medicalRecord) {
        double baseCost = BASE_COST;        
        // Sovrapprezzo per emergenze
        if (medicalRecord.isEmergency()) {
            baseCost += 100.0;
        }
        // Costo aggiuntivo basato sul tipo di trattamento
        baseCost += TreatmentCostCalculator.calculateTreatmentTypeCost(medicalRecord.getTreatmentType());
        // Aggiunta di sovrapprezzi o sconti specifici in base a parametri complessi
        if (!medicalRecord.hasChronic().contains("none"))
            baseCost += 30.0;  // Sovrapprezzo per condizioni croniche
        // Applicazione del tasso di interesse per pagamenti rateizzati
        if (medicalRecord.isInstallment()) 
            baseCost += baseCost * 0.10; // 10% di interesse per pagamenti rateizzati
        return baseCost;
    }

    // Implementazione del metodo processPayment dell'interfaccia IPaymentMethod
    @Override
    public int processPayment(Invoice invoice) {
        System.out.println("Elaborazione del pagamento per l'appuntamento: " + invoice.getAppointment().getIdUnivoco());

        // Simulazione di un pagamento
        int paymentSuccess = invoice.getPaymentMethod().processPayment();

        if (paymentSuccess == 0) {
            System.out.println("Pagamento di " + invoice.getTotalCost() + " elaborato con successo.");
            completePayment(invoice, null); // Passa null per AppointmentRegistry se non è necessario
            return 0; // Successo
        } else {
            System.out.println("Errore nell'elaborazione del pagamento.");
            return 1; // Errore
        }
    }

    @Override
    public void completePayment(Invoice invoice, AppointmentRegistry appointmentRegistry) {
        System.out.println("Pagamento completato.");
        
        // Stampa i dettagli dell'Invoice
        System.out.println("Dettagli della fattura:");
        System.out.println("ID Appuntamento: " + invoice.getAppointment().getIdUnivoco());
        System.out.println("Costo Totale: " + invoice.getTotalCost());
        System.out.println("Metodo di Pagamento: " + invoice.getPaymentMethod());

        if (appointmentRegistry != null) {
            System.out.println("Dettagli dell'appuntamento associato all'invoice:");
            System.out.println(invoice.getAppointment());

            // Rimuovi l'appuntamento dall'AppointmentRegistry
            appointmentRegistry.removeAppointment(invoice.getAppointment());
            System.out.println("Appuntamento rimosso dall'AppointmentRegistry.");
        }
    }
}
