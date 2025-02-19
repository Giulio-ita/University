package utils;

import model.Appointment;
import model.MedicalRecord;

public final class TreatmentCostCalculator {
    private static final double BASE_COST = 50.0;
    private static final double CHRONIC_CONDITION_SURCHARGE = 30.0;
    private static final double EMERGENCY_SURCHARGE = 100.0;
    private static final double INSTALLMENT_FEE_PERCENT = 0.1;
    private static final double INSURANCE_DISCOUNT_MAX_PERCENT = 0.5;
    private static final double REPEAT_CUSTOMER_DISCOUNT = 20.0;
    private static final double EVENING_APPOINTMENT_SURCHARGE = 25.0;
    private static final double LATE_PAYMENT_PENALTY = 10.0; // Penalità per ritardi

    public double calculateCost(Appointment appointment, MedicalRecord medicalRecord) {
        double totalCost = BASE_COST;

        // Sovrapprezzo per malattia cronica
        if (medicalRecord.hasChronic().isEmpty()) 
            totalCost += CHRONIC_CONDITION_SURCHARGE;
        
        // Sovrapprezzo per emergenze
        if (medicalRecord.isEmergency()) 
            totalCost += EMERGENCY_SURCHARGE;        

        // Costo basato sul tipo di trattamento
        totalCost += calculateTreatmentTypeCost(medicalRecord.getTreatmentType());

        // Sconto per storico del paziente
        if (medicalRecord.getAppointmentHistory() > 5)
            totalCost -= REPEAT_CUSTOMER_DISCOUNT;

        // Sovrapprezzo per pagamento rateizzato
        if (medicalRecord.isInstallment()) 
            totalCost += totalCost * INSTALLMENT_FEE_PERCENT;

        // Penalità per ritardi nei pagamenti
        //if (appointment.isLatePayment()) {
        //    totalCost += LATE_PAYMENT_PENALTY;
        //}

        // Sovrapprezzo per appuntamenti serali o nel weekend
        if (isEveningOrWeekendAppointment(appointment)) 
            totalCost += EVENING_APPOINTMENT_SURCHARGE;

        // Applicazione della copertura assicurativa
        if (medicalRecord.hasInsurance()) {
            double insuranceDiscount = totalCost * medicalRecord.getInsuranceCoveragePercentage();
            insuranceDiscount = Math.min(insuranceDiscount, totalCost * INSURANCE_DISCOUNT_MAX_PERCENT);
            totalCost -= insuranceDiscount;
        }

        // Sovrapprezzo in base alla gravità
        totalCost += calculateSeverityLevelSurcharge(medicalRecord.getSeverityLevel());

        return totalCost;
    }

    // Metodo per calcolare il costo aggiuntivo in base al tipo di trattamento
    public static double calculateTreatmentTypeCost(String treatmentType) {
        switch (treatmentType.toLowerCase()) {
            case "surgery":
                return 500.0;
            case "examination":
                return 100.0;
            case "routine checkup":
                return 50.0;
            default:
                return 0.0;
        }
    }

    // Metodo per calcolare sovrapprezzo in base alla gravità della condizione
    public double calculateSeverityLevelSurcharge(String severityLevel) {
        switch (severityLevel.toLowerCase()) {
            case "high":
                return 200.0;
            case "medium":
                return 100.0;
            case "low":
                return 50.0;
            default:
                return 0.0;
        }
    }

    // Controlla se l'appuntamento è serale o nel weekend
    private boolean isEveningOrWeekendAppointment(Appointment appointment) {
        int hour = appointment.getLocalDate().getHour();
        boolean isWeekend = appointment.getLocalDate().getDayOfWeek().getValue() >= 6;
        return hour >= 18 || isWeekend;
    }
}
