package cz.tsuki.pubsimulator.controllers;

import cz.tsuki.pubsimulator.dtos.UserWithOrdersDTO;
import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.services.ProductService;
import cz.tsuki.pubsimulator.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> letsDrink(@RequestParam("drink")Optional<Product> drinkName,@RequestParam("amount") int amount){
        User user = User.builder()
                .userId(userService.getCurrentUserId())
                .username(userService.getCurrentUserName())
                .isActive(userService.getCurrentUserStatus())
                .isAdult(userService.getCurrentUserAgeLimit()).
                pocket(userService.getCurrentUserMoneyStatus()).
                build();

        if (user.getPocket() <= amount * productService.getProductPrice(drinkName.toString())) {
            return ResponseEntity.status(402).body("No money, no funney!");
        } else if (!user.isAdult() && productService.isForAdult(drinkName.toString())) {
            return ResponseEntity.status(400).body("Get lost, kid!");
        } else if (!user.isAdult() && !productService.isForAdult(drinkName.toString()) && user.getPocket() >= productService.getProductPrice(drinkName.toString())) {
            Order order = new Order(user, drinkName.get(), amount);
            userService.payingForIt(order.getPrice(), user.getUserId());
            return ResponseEntity.status(201).body("Pay up, kid!" + order);
        } else if (user.isAdult() && user.getPocket() >= amount * productService.getProductPrice(drinkName.toString())){
            Order order = new Order(user, drinkName.get(), amount);
            userService.payingForIt(order.getPrice(), user.getUserId());
            return ResponseEntity.status(201).body("Money on the table!" + order);
        } else {
            return  ResponseEntity.status(417).body("Looks like you aren't drinking tonight...");
        }

    }

    /*
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

*/
}
