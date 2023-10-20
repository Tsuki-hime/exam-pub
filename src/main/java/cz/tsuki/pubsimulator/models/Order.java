package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    private int amount;
    private int price;

    public Order(User user, Product drinkName, int amount) {
        this.user = user;
        this.amount = amount;
        this.price = amount*drinkName.getProductPrice();
        products.add(drinkName);
    }

}
