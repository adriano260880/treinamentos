public class DebitPayment implements PaymentsService{
    @Override
    public void pay(double amount) {
        System.out.println("Debit Payment "+ amount);
    }
}
