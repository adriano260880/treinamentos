package br.com.aal.graphql.controller.repository;

import br.com.aal.graphql.model.Order;
import br.com.aal.graphql.model.Product;
import br.com.aal.graphql.model.User;

import java.util.List;

public class FakeDB {

    static List<User> users = List.of(
            new User(1L, "Adriano"),
            new User(2L, "Lopes")
    );

    static List<Order> orders = List.of(
            new Order(1L, 100.0, 1L)
    );

    static List<Product> products = List.of(
            new Product(1L, "Keyboard", 50.0),
            new Product(2L, "Mouse", 45.0)
    );

    public static User getUser(Long id) {
        return users.stream().filter(u -> u.id().equals(id))
                .findFirst().orElse(null);
    }

    public static List<Order> getOrdersByUser(Long userId) {
        return orders.stream().filter(o -> o.userId().equals(userId))
                .toList();
    }

    public static List<Product> getProductsByOrder(Long orderId) {
        return products;
    }
}
