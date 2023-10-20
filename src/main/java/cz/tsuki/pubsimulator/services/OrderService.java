package cz.tsuki.pubsimulator.services;

import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }
}
