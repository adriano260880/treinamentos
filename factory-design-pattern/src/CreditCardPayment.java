public class CreditCardPayment implements PaymentsService{
    @Override
    public void pay(double amount) {
        System.out.println("Credit Card Payment" + amount);
    }
}
