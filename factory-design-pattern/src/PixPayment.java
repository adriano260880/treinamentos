public class PixPayment implements PaymentsService{
    @Override
    public void pay(double amount) {
        System.out.println("Pix Payment "+ amount);
    }
}
