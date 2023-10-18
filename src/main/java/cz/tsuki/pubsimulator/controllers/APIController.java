package cz.tsuki.pubsimulator.controllers;

import cz.tsuki.pubsimulator.dtos.UserWithOrdersDTO;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.services.ProductService;
import cz.tsuki.pubsimulator.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class APIController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> maybeUser = userService.getUserById(id);
        if (maybeUser.isPresent()) {
            return ResponseEntity.status(200).body(new UserWithOrdersDTO(maybeUser.get()));
        } else {
            return ResponseEntity.status(404).body("User does not exist.");
        }
    }

    @GetMapping("drink-menu")
    public ResponseEntity<?> gimmeBooze() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

}

/*
   @PostMapping("/links")
    public ResponseEntity<?> links(@RequestBody Alias alias) {
        Optional<Alias> found = aliasService.findByAlias(alias.getAlias());
        if (found.isPresent()) {//if used response with code 400
            return ResponseEntity.status(400).build();

        } else { // else generate secret code, store entry in database, respond wit JSON format
            aliasService.save(alias);
            return ResponseEntity.status(200).body(alias);
        }
    }

    @GetMapping("/links")
    public ResponseEntity<?> links() {
        List<AliasDTO> output = new ArrayList<>();

        aliasService.getAll().forEach(alias -> output.add(new AliasDTO(alias)));

        return ResponseEntity.status(200).body(output);
    }
 */