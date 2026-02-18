public abstract class NotificationDecorator implements Notification {
    protected final Notification wrapped;

    protected NotificationDecorator(Notification wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void send(String message) {
        wrapped.send(message);
    }
}
