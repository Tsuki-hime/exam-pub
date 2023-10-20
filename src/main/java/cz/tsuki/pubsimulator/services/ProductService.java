package cz.tsuki.pubsimulator.services;

import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

public int getProductPrice(String productName){
        return productRepository.findProductByProductName(productName).get().getProductPrice();
}

    public boolean isForAdult(String drinkName) {
        return productRepository.findProductByProductName(drinkName).get().isForAdult();
    }
}
