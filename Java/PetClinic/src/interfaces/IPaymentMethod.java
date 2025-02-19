package interfaces;

import model.Invoice;
import registry.AppointmentRegistry;

public interface IPaymentMethod {
    public int processPayment(Invoice invoice);
    public void completePayment(Invoice invoice, AppointmentRegistry appointmentRegistry);
}
