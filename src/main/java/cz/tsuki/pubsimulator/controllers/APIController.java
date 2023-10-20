package cz.tsuki.pubsimulator.controllers;

import cz.tsuki.pubsimulator.dtos.UserWithOrdersDTO;
import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.Product;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.services.OrderService;
import cz.tsuki.pubsimulator.services.ProductService;
import cz.tsuki.pubsimulator.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class APIController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.status(200).body(userService.getAllDrunks());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> maybeUser = userService.getUserById(id);

        if (maybeUser.isPresent() && maybeUser.get().isBartender(id)) {
            return ResponseEntity.status(401).body("That's bartender's account, get out!");
        } else if (maybeUser.isPresent()) {
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
    public ResponseEntity<?> letsDrink(@RequestParam("drink") Long drinkId, @RequestParam("amount") int amount) {
        User user = userService.getCurrentUser();
        try{
            Product drink = productService.getProductById(drinkId);
            Order order = new Order(user, drink, amount);

            if (user.getPocket() <= order.getPrice()) {
                return ResponseEntity.status(402).body("No money, no funney!");
            } else if (!user.canBuyBooze() && drink.isForAdult()) {
                return ResponseEntity.status(400).body("Get lost, kid!");
            } else if (user.getPocket() >= order.getPrice()) {
                user.payForIt(order.getPrice());
                orderService.save(order);
                return ResponseEntity.status(201).body("Money on the table!" + order);
            } else {
                return ResponseEntity.status(417).body("Looks like you aren't drinking tonight...");
            }
        } catch (NoSuchElementException notFound){
            return ResponseEntity.status(404).body("This drink doesn't exist yet.");
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
