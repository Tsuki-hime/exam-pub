package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @ManyToOne
    private User user;
    @OneToOne
    private Product product;
    private int amount;
    private int price;

    public Order(User user, Product drink, int amount) {
        this.user = user;
        this.amount = amount;
        this.price = amount * drink.getProductPrice();
        this.product = drink;
    }

}
