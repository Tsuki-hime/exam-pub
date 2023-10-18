package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.Order;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<Order, Long> {
}
