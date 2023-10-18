package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.Bartender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BartenderRepository extends JpaRepository<Bartender, Long> {
}
