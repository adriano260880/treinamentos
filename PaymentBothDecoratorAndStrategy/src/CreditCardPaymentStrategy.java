public class CreditCardPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(Order order) {
    System.out.println("Processing Credit Card payment "+order.amount());
    }
}
