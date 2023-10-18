package cz.tsuki.pubsimulator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bartenders")
@Data
@NoArgsConstructor
public class Bartender {
    @Id
    @GeneratedValue
    private Long bartenderId;
    private String bartenderName;
    private boolean isActive;

}
