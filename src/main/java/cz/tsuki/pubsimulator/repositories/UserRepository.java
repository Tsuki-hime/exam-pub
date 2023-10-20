package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findUserByUsername(String username);
}
