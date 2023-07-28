package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Controller;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.FoodItem;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.Order;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignInInput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignUpOutput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IFoodItemRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.AdminAuthenticateService;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.AdminService;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminAuthenticateService adminAuthenticateService;

    @Autowired
    IFoodItemRepo iFoodItemRepo;
    @Autowired
    OrderService orderService;


    @PostMapping("signUp/admin")
    public SignUpOutput signUpAdmin(@RequestBody AdminUser admin, @RequestParam String systemPassword) throws NoSuchAlgorithmException {
        return adminService.signUp(admin,systemPassword);
    }
    @PostMapping("signIn/admin")
    public String signInAdmin(SignInInput signInInput) throws NoSuchAlgorithmException, MessagingException {
        return adminService.signIn(signInInput);
    }
    @PostMapping("foodItem")
    public String addFoodItem(@RequestParam String adminEmail, @RequestParam String authToken, @RequestBody List<FoodItem> foodItemList){
        if(adminAuthenticateService.authenticate(adminEmail,authToken)){
            iFoodItemRepo.saveAll(foodItemList);
            return "food Added";
        }
        return "Authentication Failed";
    }
    @GetMapping("allOrders")
    public List<Order> getAllOrders(@RequestParam String adminEmail, @RequestParam String authToken){
        if(adminAuthenticateService.authenticate(adminEmail,authToken)){
            return orderService.getAllOrderForAdmin();}
        else{
            return null;
        }
    }
    @DeleteMapping("signOut/admin")
    public String signOut(@RequestParam String adminEmail, @RequestParam String authToken){
        if(adminAuthenticateService.authenticate(adminEmail,authToken)){
            return adminService.signOut(adminEmail);
        }
        return "Authentication Failed";
    }
}
