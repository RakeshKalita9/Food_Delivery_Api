package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenAdmin;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.SystemProperties.AdminValidator;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.Utilities.EmailHandler;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignInInput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignUpOutput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAdminRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAuthAdminRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.Hashing.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    IAdminRepo adminRepo;
    @Autowired
    AdminValidator adminValidator;

    @Autowired
    IAuthAdminRepo authAdminRepo;
    public SignUpOutput signUp(AdminUser admin, String systemPassword) throws NoSuchAlgorithmException {
        String adminEmail = admin.getAdminEmail();
        AdminUser admin1 = adminRepo.findFirstByAdminEmail(adminEmail);

        if(adminEmail == null) return new SignUpOutput("Error Occurred",false);

        if(admin1!=null) return new SignUpOutput("Email is Not registered",false);

        if(!adminValidator.getSystemPassword().equals(systemPassword)){
           return new SignUpOutput("Owner verification failed",false);
        }
       String hexValue = Encrypt.encryptPassword(admin.getAdminPassword());
       admin.setAdminPassword(hexValue);
       adminRepo.save(admin);
       return new SignUpOutput("Sign Up successful",true);
    }

    public String signIn(SignInInput signInInput) throws NoSuchAlgorithmException, MessagingException {
        String adminEmail = signInInput.getEmail();
        if(adminEmail==null) return "provide Email";
        AdminUser admin = adminRepo.findFirstByAdminEmail(adminEmail);

        if(admin == null ) return "Admin is no Signed up yet ........";

        if(!admin.getAdminPassword().equals(Encrypt.encryptPassword(signInInput.getPassword())))
            return "Invalid  Sign in Credential";
        AuthenticationTokenAdmin authenticationTokenAdmin = new AuthenticationTokenAdmin(admin);
        authAdminRepo.save(authenticationTokenAdmin);
        EmailHandler.sendEmail(signInInput.getEmail(),"Auth token for "+admin.getAdminName(),"auth token is "+authenticationTokenAdmin.getToken());
        return "Signed In ...";
    }

    public String signOut(String adminEmail) {
        AdminUser adminUser = adminRepo.findFirstByAdminEmail(adminEmail);
        AuthenticationTokenAdmin authenticationTokenAdmin = authAdminRepo.findFirstByAdmin(adminUser);
        authAdminRepo.delete(authenticationTokenAdmin);
        return "Signed Out Successfully";
    }
}
