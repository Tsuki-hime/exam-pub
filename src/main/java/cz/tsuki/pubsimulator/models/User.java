package cz.tsuki.pubsimulator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
