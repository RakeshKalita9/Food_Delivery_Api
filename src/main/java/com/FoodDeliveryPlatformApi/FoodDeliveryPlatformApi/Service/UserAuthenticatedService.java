package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenAdmin;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAuthAdminRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAuthUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticatedService {
    @Autowired
    IAuthUserRepo authUserRepo;
    public boolean authenticate(String userEmail, String authToken) {
        AuthenticationTokenUser authenticationTokenUser = authUserRepo.findFirstByToken(authToken);
        if(authenticationTokenUser==null) return false;
        User user = authenticationTokenUser.getUser();
        return user.getUserEmail().equals(userEmail);
    }
}
