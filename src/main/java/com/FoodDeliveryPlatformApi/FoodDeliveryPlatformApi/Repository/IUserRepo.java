package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {
    User findFirstByUserEmail(String userEmail);
}
