package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long orderId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orders")
    private List<Product> products = new ArrayList<>();

    private int amount;
    private int price;
}
