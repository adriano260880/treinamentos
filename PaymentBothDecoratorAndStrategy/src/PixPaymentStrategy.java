public class PixPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Order order) {
        System.out.println("Processing Pix payment "+order.amount());
    }
}
