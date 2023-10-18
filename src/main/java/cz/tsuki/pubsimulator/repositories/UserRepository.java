package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
