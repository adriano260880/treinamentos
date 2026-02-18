public class FraudCheckDecorator extends PaymentDecorator {

    protected FraudCheckDecorator(PaymentStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public void pay(Order order) {
        System.out.println("Running fraud check...");
        super.pay(order);
    }
}
