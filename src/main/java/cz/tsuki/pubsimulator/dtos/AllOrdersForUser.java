package cz.tsuki.pubsimulator.dtos;

import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.repositories.OrderRepository;
import lombok.Data;

import java.util.List;

@Data
public class AllOrdersForUser {

    private Long userId;
    private String username;
    private List<Order> orders;
    private OrderRepository orderRepository;

    public AllOrdersForUser(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.orders = orderRepository.findAllByUser(user);

    }
}
