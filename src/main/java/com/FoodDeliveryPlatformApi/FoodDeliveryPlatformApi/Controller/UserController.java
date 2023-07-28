package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Controller;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.FoodItem;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.Order;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignInInput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignUpOutput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.FoodService;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.OrderService;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.UserAuthenticatedService;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserAuthenticatedService userAuthenticatedService;
    @Autowired
    FoodService foodService;
    @Autowired
    OrderService orderService;

    @PostMapping("signUp/user")
    public SignUpOutput signUp(@RequestBody User user){
        return userService.signUp(user);
    }
    @PostMapping("signIn/user")
    public String signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);
    }
    @GetMapping("foodItem")
    public List<FoodItem> getAllFoods(@RequestParam String userEmail, @RequestParam String authToken){
        if(userAuthenticatedService.authenticate(userEmail,authToken)){
           return  foodService.getAllFood();
        }
        return null;
    }
    @PostMapping("order")
    public String createOrder(@RequestBody Order order, @RequestParam String userEmail, @RequestParam String authToken){
        if(userAuthenticatedService.authenticate(userEmail,authToken)){
            return orderService.placeOrder(order);
        }
        return "Authentication failed";
    }
    @GetMapping("order/Total")
    public String orderTotal(@RequestParam Long id,@RequestParam String userEmail, @RequestParam String authToken){
        if(userAuthenticatedService.authenticate(userEmail,authToken)){
            return orderService.orderTotal(id,userEmail);
        }else{
            return "Authentication Failed";
        }
    }

    @DeleteMapping("cancel/order")
    public String cancelOrder(@RequestParam Long id,@RequestParam String userEmail, @RequestParam String authToken){
        if(userAuthenticatedService.authenticate(userEmail,authToken)){
           return orderService.cancelOrder(id,userEmail);
        }
        return "Authentication Failed";
    }

    @GetMapping("orders")
    public List<Order> orderList(@RequestParam String userEmail, @RequestParam String authToken){
        if(userAuthenticatedService.authenticate(userEmail,authToken)){
            return orderService.getAllOrder(userEmail);
        }
        return new ArrayList<>();
    }
    @DeleteMapping("signOut/user")
    public String signOut(@RequestParam String userEmail, @RequestParam String authToken){
        if(userAuthenticatedService.authenticate(userEmail,authToken)){
            return userService.signOutUser(userEmail);
        }
        return "Authentication Failed";
    }


}
