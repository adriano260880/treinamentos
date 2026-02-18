public class LoggingDecorator extends PaymentDecorator{

    protected LoggingDecorator(PaymentStrategy wrapped) {
        super(wrapped);
    }

    @Override
    public void pay(Order order) {
        System.out.println("[Log] Starting payment for order "+ order.id());
        super.pay(order);
        System.out.println("[LOG] Payment finished!");
    }
}
