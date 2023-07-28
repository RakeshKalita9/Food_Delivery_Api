package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.FoodItem;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IFoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodService {
    @Autowired
    IFoodItemRepo foodItemRepo;
    public List<FoodItem> getAllFood() {
        return foodItemRepo.findAll();
    }
}
