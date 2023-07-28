package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthAdminRepo extends JpaRepository<AuthenticationTokenAdmin,Long> {
    AuthenticationTokenAdmin findFirstByToken(String authToken);

    AuthenticationTokenAdmin findFirstByAdmin(AdminUser adminUser);
}
