package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class User {
    @Id
    @GeneratedValue
    private Long userId;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean isActive;
    private int pocket;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

    public abstract boolean canBuyBooze();

    public void payForIt(int price) {
        if (pocket < price) {
            throw new RuntimeException("Not enough gold.");
        }
        pocket -= price;
    }

    public boolean isBartender(Long id) {
        return this.role == Role.BARTENDER;
    }
}
