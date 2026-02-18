public abstract class PaymentDecorator implements PaymentStrategy{

    protected final PaymentStrategy wrapped;

    protected PaymentDecorator(PaymentStrategy wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void pay(Order order) {
        wrapped.pay(order);
    }
}
