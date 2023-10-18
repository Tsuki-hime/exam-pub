package cz.tsuki.pubsimulator.dtos;

import cz.tsuki.pubsimulator.models.Order;
import cz.tsuki.pubsimulator.models.User;
import cz.tsuki.pubsimulator.services.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

public class UserWithOrdersDTO {

    private Long userId;
    private String username;
    private boolean isActive;
    private boolean isAdult;
    private int pocket;
    private List<Order> orders;
    private UserService userService;

    public UserWithOrdersDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.isActive = user.isActive();
        this.isAdult = user.isAdult();
        this.pocket = user.getPocket();
        this.orders = userService.getOrders(userId);
    }



}
