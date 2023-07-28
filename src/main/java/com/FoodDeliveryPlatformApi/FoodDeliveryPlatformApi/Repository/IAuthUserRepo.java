package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthUserRepo extends JpaRepository<AuthenticationTokenUser,Long> {
    AuthenticationTokenUser findFirstByToken(String authToken);

    AuthenticationTokenUser findFirstByUser(User user);
}
