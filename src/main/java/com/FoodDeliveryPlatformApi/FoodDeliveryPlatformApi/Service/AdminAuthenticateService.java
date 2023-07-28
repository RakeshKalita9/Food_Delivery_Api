package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenAdmin;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAdminRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAuthAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticateService {
    @Autowired
    IAuthAdminRepo authAdminRepo;
    @Autowired
    IAdminRepo adminRepo;


    public boolean authenticate(String adminEmail, String authToken) {
        AuthenticationTokenAdmin authenticationTokenAdmin = authAdminRepo.findFirstByToken(authToken);
        if(authenticationTokenAdmin==null) return false;
        AdminUser adminUser = authenticationTokenAdmin.getAdmin();
        return adminUser.getAdminEmail().equals(adminEmail);
    }
}
