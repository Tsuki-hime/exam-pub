package cz.tsuki.pubsimulator.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
@DiscriminatorValue("DRUNK")
@AllArgsConstructor
public class Drunk extends User {

    private boolean isAdult;

    public Drunk() {
        this.setRole(Role.DRUNK);
    }

    @Override
    public boolean canBuyBooze() {
        return isAdult;
    }
}
