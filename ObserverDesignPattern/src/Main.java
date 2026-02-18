import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        OrderService service = new OrderService();

        service.addObserver(new EmailNotificationObserver());
        service.addObserver(new InventoryObserver());

        Order order = new Order("1", new BigDecimal("100.00"));
        service.createOrder(order);

    }
}