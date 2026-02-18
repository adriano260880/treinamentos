import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void createOrder(Order order) {
        System.out.println("Order created "+ order.id());
        notifyObservers(order);
    }

    private void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
}
