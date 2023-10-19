package cz.tsuki.pubsimulator.services;

import cz.tsuki.pubsimulator.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService {
    private final UserRepository userRepository;
}
