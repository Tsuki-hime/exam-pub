package cz.tsuki.pubsimulator.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;

@Entity
@Builder
@DiscriminatorValue("BARTENDER")
public class Bartender extends User {
    public Bartender() {
        this.setRole(Role.BARTENDER);
    }

    @Override
    public boolean canBuyBooze() {
        return true;
    }
}
