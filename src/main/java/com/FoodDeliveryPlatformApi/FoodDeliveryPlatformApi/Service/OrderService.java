package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.FoodItem;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.Order;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IOrderRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {
    @Autowired
    IOrderRepo orderRepo;
    @Autowired
    IUserRepo userRepo;

    public  String orderTotal(Long id, String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        List<Order> orderList = orderRepo.findByUser(user);
        double Total = (double) 0;
        for(Order order : orderList){
            if(order.getOrderId().equals(id)){
                List<FoodItem> foodItemList = order.getFoodItemList();
                for(FoodItem foodItem : foodItemList){
                    Total = Total + foodItem.getFoodPrice();
                }
                return user.getFirstName()+" has order amount having orderId "+id+" equals to "+Total;
            }
        }
        return "Error occurred";
    }


    public String placeOrder(Order order) {
        orderRepo.save(order);
        return "Order Successfully Placed";
    }

    public String cancelOrder(Long id, String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        List<Order> orderList = orderRepo.findByUser(user);
        for(Order order : orderList){
            if(order.getOrderId().equals(id)){
                orderRepo.delete(order);
                return "order canceled";
            }
        }
        return "error Occurred";
    }

    public List<Order> getAllOrder( String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        List<Order> orderList = orderRepo.findByUser(user);
        ArrayList<Order> orders = new ArrayList<>();
        for(Order order : orderList){
          order.getUser().setPassword("Confidential");
          orders.add(order);
        }
        return orders;
    }

    public List<Order> getAllOrderForAdmin() {
        List<Order> orderList = orderRepo.findAll();
        ArrayList<Order> orders = new ArrayList<>();
        for(Order order : orderList){
            order.getUser().setPassword("Confidential");
            orders.add(order);
        }
        return orders;
    }
}
