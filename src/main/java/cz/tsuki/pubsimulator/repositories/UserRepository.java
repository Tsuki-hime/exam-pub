package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.Drunk;
import cz.tsuki.pubsimulator.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    List<User> findAll();

    @Query("SELECT u FROM User u WHERE TYPE(u)= Drunk")
    List<Drunk> findAllDrunks();
}
