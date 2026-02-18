public class EmailNotificationObserver implements OrderObserver{
    @Override
    public void update(Order order) {
        System.out.println("Sending email for order "+order.id());
    }
}
