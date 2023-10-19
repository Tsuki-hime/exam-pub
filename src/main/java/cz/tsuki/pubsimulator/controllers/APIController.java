package cz.tsuki.pubsimulator.controllers;

import cz.tsuki.pubsimulator.dtos.UserWithOrdersDTO;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.services.ProductService;
import cz.tsuki.pubsimulator.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class APIController {

    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> maybeUser = userService.getUserById(id);
        if (maybeUser.isPresent()) {
            return ResponseEntity.status(200).body(new UserWithOrdersDTO(maybeUser.get()));
        } else {
            return ResponseEntity.status(404).body("User does not exist.");
        }
    }

    @GetMapping("/drink-menu")
    public ResponseEntity<?> gimmeBooze() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/buy")
    public ResponseEntity<?> letsDrink(){
// validate if user isAdult  and has enough money
        // userId, productId, price

        return ResponseEntity.status(200).body(productService.getAllProducts());
    }
    @GetMapping("/summary/all")
    public ResponseEntity<?> getAllOrdersOfAllDrinks(){
        //return DTO that contains  product, amount, unitprice summarz price / kinda like inventorz linsting


    }

    @GetMapping("/summary/product")
    public ResponseEntity<?> getAllOrdersOfThisProduct(@RequestParam Optional<String> productName){
        //return DTO that contains  all orders of this drink for all drinks like in a table?


    }

    @GetMapping("/summary/user")
    public ResponseEntity<?> getAllOrdersOfUser(@RequestParam Optional<String> username){
        //return DTO that contains  all orders of this user
        //or returns DTO that contains all orders of user and displazs it for all users

    }


}
