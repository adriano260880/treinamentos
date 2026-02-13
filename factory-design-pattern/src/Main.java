public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Hello world!");

        PaymentsService paymentPix = PaymentFactory.createPayment("Pix");
        paymentPix.pay(100);

        PaymentsService paymentCreditCard = PaymentFactory.createPayment("CRedit");
        paymentCreditCard.pay(50);

        PaymentsService teste = PaymentFactory.createPayment("Debit");
        teste.pay(10);
    }
}