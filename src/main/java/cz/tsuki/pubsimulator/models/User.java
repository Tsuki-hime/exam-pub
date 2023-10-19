package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    @Column(unique = true)
    private String username;
    private boolean isActive;
    private boolean isAdult;
    private int pocket;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

}
