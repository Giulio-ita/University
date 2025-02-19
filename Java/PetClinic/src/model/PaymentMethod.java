package model;

public enum PaymentMethod {
	CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL,
    CASH;

    public int processPayment() {
        // Logica per elaborare il pagamento in base al metodo
        switch (this) {
            case CREDIT_CARD:
                System.out.println("Elaborazione pagamento con carta di credito...");
                break;
            case DEBIT_CARD:
                System.out.println("Elaborazione pagamento con carta di debito...");
                break;
            case PAYPAL:
                System.out.println("Elaborazione pagamento con PayPal...");
                break;
            case CASH:
                System.out.println("Pagamento in contante ricevuto.");
                break;
        }
        return 0; // Successo
    }
}
