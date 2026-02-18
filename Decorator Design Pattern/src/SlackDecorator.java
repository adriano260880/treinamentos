public class SlackDecorator extends NotificationDecorator {
    protected SlackDecorator(Notification wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SLACK "+message);
    }
}
