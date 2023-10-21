package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    @Column(unique = true)
    private String productName;
    private int productPrice;
    private boolean isForAdult;
}
