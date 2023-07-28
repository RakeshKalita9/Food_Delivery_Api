package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthenticationTokenAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime loginTime;
    @OneToOne
    private AdminUser admin;
    public  AuthenticationTokenAdmin(AdminUser admin){
        this.token = UUID.randomUUID().toString();
        this.loginTime =LocalDateTime.now();
        this.admin =admin;
    }
}
