package model;

import java.util.UUID;

public class Invoice {
    private String invoiceId;
    private Appointment appointment;
    private double totalCost;
    private PaymentMethod paymentMethod;
    private boolean isPaid;

    public Invoice(Appointment appointment, double totalCost, PaymentMethod paymentMethod) {
        this.invoiceId = UUID.randomUUID().toString();
        this.appointment = appointment;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.isPaid = false;
    }
    // Metodo per completare il pagamento
    public void completePayment() {
        if (!isPaid) {
            this.isPaid = true;
            System.out.println("Payment completed for invoice: " + invoiceId);
        } else {
            System.out.println("Invoice already paid.");
        }
    }
    // Getters e Setters
    public String getInvoiceId() {
        return invoiceId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }
}


