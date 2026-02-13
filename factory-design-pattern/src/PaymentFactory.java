public class PaymentFactory {

    public static PaymentsService createPayment(String type) throws IllegalAccessException {
        if (type.equalsIgnoreCase("credit")) {
            return new CreditCardPayment();
        } else if (type.equalsIgnoreCase("pix")) {
            return new PixPayment();
        } else if (type.equalsIgnoreCase("DEBIT")) {
            return new DebitPayment();
        }

        throw new IllegalAccessException("Invalid Payment type");
    }
}
