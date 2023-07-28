package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationTokenUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime loginTime;
    @OneToOne
    private User user;
    public  AuthenticationTokenUser(User user){
        this.token = UUID.randomUUID().toString();
        this.loginTime =LocalDateTime.now();
        this.user =user;
    }
}
