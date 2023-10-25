package cz.tsuki.pubsimulator.services;

import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) throws NoSuchElementException {
        return productRepository.findById(productId).get();
    }

    public void save(Product product) {
        productRepository.save(product);
    }


    public Optional<Product> findByName(String productName) {
       return Optional.ofNullable(productRepository.findByProductName(productName));
    }
}
