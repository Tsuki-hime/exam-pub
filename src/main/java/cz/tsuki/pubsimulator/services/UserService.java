package cz.tsuki.pubsimulator.services;

import cz.tsuki.pubsimulator.models.Drunk;
import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.Role;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.repositories.OrderRepository;
import cz.tsuki.pubsimulator.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public List<Order> getOrders(Long id) {
        return orderRepository.findAllByUser(userRepository.findById(id).get());
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return userRepository.findUserByUsername(name).get();
    }

    public List<Drunk> getAllDrunks() {
        return userRepository.findAllDrunks();
    }


}
