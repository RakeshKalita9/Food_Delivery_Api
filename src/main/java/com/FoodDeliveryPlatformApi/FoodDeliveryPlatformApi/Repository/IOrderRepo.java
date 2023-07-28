package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.Order;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepo extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
}
