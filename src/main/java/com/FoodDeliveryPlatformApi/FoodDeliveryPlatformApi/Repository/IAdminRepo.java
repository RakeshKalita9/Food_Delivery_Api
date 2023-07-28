package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<AdminUser,Long> {
    AdminUser findFirstByAdminEmail(String adminEmail);
}
