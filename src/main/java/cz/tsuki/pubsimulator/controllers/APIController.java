package cz.tsuki.pubsimulator.controllers;

import cz.tsuki.pubsimulator.dtos.AllOrdersForUser;
import cz.tsuki.pubsimulator.dtos.DrinkAndItsOrdersDTO;
import cz.tsuki.pubsimulator.dtos.ProductOrdersDTO;
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

import java.util.ArrayList;
import java.util.List;
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
        try {
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
        } catch (NoSuchElementException notFound) {
            return ResponseEntity.status(404).body("This drink doesn't exist yet.");
        }
    }

    @GetMapping("/seed")
    public ResponseEntity<?> createDrinks() {

        Product drink1 = new Product();
        drink1.setProductName("Bloody Mary");
        drink1.setProductPrice(150);
        drink1.setForAdult(true);
        productService.save(drink1);

        Product drink2 = new Product();
        drink2.setProductName("GinTon");
        drink2.setProductPrice(100);
        drink2.setForAdult(true);
        productService.save(drink2);

        Product drink3 = new Product();
        drink3.setProductName("Spirited Water");
        drink3.setProductPrice(50);
        drink3.setForAdult(false);
        productService.save(drink3);

        return ResponseEntity.status(200).body("Drinks created.");
    }


    @GetMapping("/summary/all")
    public ResponseEntity<?> getAllOrdersForAllDrinks() {
        List<Product> products = productService.getAllProducts();
        List<DrinkAndItsOrdersDTO> drinkDTOS = new ArrayList<>();
        for (Product p : products) {
            drinkDTOS.add(new DrinkAndItsOrdersDTO(p));
        }
        return ResponseEntity.status(200).body(drinkDTOS);
    }

    @GetMapping("/summary/product")
    public ResponseEntity<?> getAllOrdersOfThisProduct(@RequestParam Optional<String> productName) {
        Optional<Product> optProduct = productService.findByName(productName.toString());

        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            return ResponseEntity.status(200).body(new ProductOrdersDTO(product));
        } else {
            return ResponseEntity.status(404).body("We don't serve this drink.");
        }
    }


    @GetMapping("/summary/user")
    public ResponseEntity<?> getAllOrdersOfUser(@RequestParam Optional<String> username) {
        Optional<User> maybeUser = userService.getUserByUsername(username.toString());

        if (maybeUser.isPresent()) {
            return ResponseEntity.status(200).body(new AllOrdersForUser(maybeUser.get()));
        } else {
            return ResponseEntity.status(404).body("We don't know this guy.");
        }
    }

    @GetMapping("/summary/users")
    public ResponseEntity<?> getAllOrdersOfAllUses() {
        List<User> drunks = userService.getAllUsers();
        List<AllOrdersForUser> myPrettyDTOs = new ArrayList<>();
        for (User user : drunks) {
            myPrettyDTOs.add(new AllOrdersForUser(user));
        }
        return ResponseEntity.status(200).body(myPrettyDTOs);
    }
}