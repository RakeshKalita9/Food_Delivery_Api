package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String adminName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",message = "Email should be valid")
    @Column(unique = true)
    private String adminEmail;

    @NotBlank
    @Size(min = 8,message = "password length should be atLeast 8")
    private String adminPassword;
}
