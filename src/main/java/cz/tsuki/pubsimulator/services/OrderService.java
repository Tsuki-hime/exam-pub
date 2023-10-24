package cz.tsuki.pubsimulator.services;

import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<Long> getAllOrderIdsOfProduct(Product product){
        List<Order> orders = orderRepository.findAllByProduct(product);
        List<Long> orderIds = new ArrayList<>();
        for (Order o : orders) {
            orderIds.add(o.getOrderId());
        }
        return orderIds;
    }

    public int getTotalProductAmountForAllItsOrders(Product product){
        List<Order> orders = orderRepository.findAllByProduct(product);
        int totalAmount = 0;
        for (Order o : orders) {
            totalAmount += o.getAmount();
        }
        return totalAmount;
    }
}
