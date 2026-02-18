public class InventoryObserver implements OrderObserver{
    @Override
    public void update(Order order) {
        System.out.println("Updating inventory for order "+ order.id());
    }
}
