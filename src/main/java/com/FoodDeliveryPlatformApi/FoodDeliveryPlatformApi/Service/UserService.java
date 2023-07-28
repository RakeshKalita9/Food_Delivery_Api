package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service;

import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AdminUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenAdmin;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.AuthenticationTokenUser;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.User;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.Utilities.EmailHandler;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignInInput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto.SignUpOutput;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IAuthUserRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Repository.IUserRepo;
import com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Service.Hashing.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthUserRepo authUserRepo;
    public SignUpOutput signUp(User user) {
        String userEmail = user.getUserEmail();
        User user1 = userRepo.findFirstByUserEmail(userEmail);

        if(userEmail == null) return new SignUpOutput("Error Occurred",false);

        if(user1!=null) return new SignUpOutput("Email is Not registered",false);

        try {
            String hexValue = Encrypt.encryptPassword(user.getPassword());
            user.setPassword(hexValue);
            userRepo.save(user);
            return new SignUpOutput("Sign Up Successful",true);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String signIn(SignInInput signInInput) {
        String userEmail = signInInput.getEmail();
        if(userEmail==null) return "provide Email";
        User user = userRepo.findFirstByUserEmail(userEmail);

        if(user == null ) return "Admin is no Signed up yet ........";

        try {
            if(!user.getPassword().equals(Encrypt.encryptPassword(signInInput.getPassword())))
                return "Invalid  Sign in Credential";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        AuthenticationTokenUser authenticationTokenUser = new AuthenticationTokenUser(user);
        authUserRepo.save(authenticationTokenUser);

        try {
            EmailHandler.sendEmail(signInInput.getEmail(), "Auth token for " + user.getFirstName(), "auth token is " + authenticationTokenUser.getToken());
            return "Signed In ...";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        }

    public String signOutUser(String userEmail) {
        User user = userRepo.findFirstByUserEmail(userEmail);
        AuthenticationTokenUser authenticationTokenUser = authUserRepo.findFirstByUser(user);
        authUserRepo.delete(authenticationTokenUser);
        return "Signed Out Successfully...";
    }
}

