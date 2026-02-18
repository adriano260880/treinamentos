public class SmsDecorator extends NotificationDecorator{
    protected SmsDecorator(Notification wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SMS "+message);
    }
}
