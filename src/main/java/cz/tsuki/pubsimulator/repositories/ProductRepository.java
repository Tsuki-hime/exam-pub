package cz.tsuki.pubsimulator.repositories;

import cz.tsuki.pubsimulator.models.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
}
