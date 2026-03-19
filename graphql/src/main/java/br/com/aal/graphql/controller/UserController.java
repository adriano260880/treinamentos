package br.com.aal.graphql.controller;

import br.com.aal.graphql.controller.repository.FakeDB;
import br.com.aal.graphql.model.Order;
import br.com.aal.graphql.model.Product;
import br.com.aal.graphql.model.User;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @QueryMapping
    public User user(@Argument Long id) {
        return FakeDB.getUser(id);
    }

    @SchemaMapping
    public List<Order> orders(User user) {
        return FakeDB.getOrdersByUser(user.id());
    }

    @SchemaMapping
    public List<Product> products(Order order) {
        return FakeDB.getProductsByOrder(order.id());
    }
}
