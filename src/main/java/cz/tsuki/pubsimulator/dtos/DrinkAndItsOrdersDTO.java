package cz.tsuki.pubsimulator.dtos;

import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.services.OrderService;
import cz.tsuki.pubsimulator.services.ProductService;
import lombok.Data;

import java.util.List;

@Data
public class DrinkAndItsOrdersDTO {

    private Long productId;
    private String productName;
    private int amount;
    private int summaryPrice;
    private int unitPrice;
    private List<Long> orderIds;
    private OrderService orderService;
    private ProductService productService;

    public DrinkAndItsOrdersDTO(Product product) {
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.unitPrice = product.getProductPrice();
        this.amount = orderService.getTotalProductAmountForAllItsOrders(product);
        this.summaryPrice = amount * unitPrice;
        this.orderIds = orderService.getAllOrderIdsOfProduct(product);
    }
}
