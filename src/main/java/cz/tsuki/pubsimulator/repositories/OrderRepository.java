package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
