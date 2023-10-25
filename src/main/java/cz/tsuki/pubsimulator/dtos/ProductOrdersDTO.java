package cz.tsuki.pubsimulator.dtos;

import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.services.OrderService;
import lombok.Data;

import java.util.List;

@Data
public class ProductOrdersDTO {
    private List<Order> orders;
    private OrderService orderService;

    public ProductOrdersDTO(Product product) {
        this.orders = orderService.getAllOrdersOfProduct(product);
    }
}
