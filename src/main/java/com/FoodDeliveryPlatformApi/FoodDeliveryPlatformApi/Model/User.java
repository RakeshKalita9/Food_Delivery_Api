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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    @Pattern(regexp = "^[A-Z].*",message = "first letter should be capital")
    private String firstName;
    @NotBlank
    @Pattern(regexp = "^[A-Z].*",message = "first letter should be capital")
    private String lastName;
    @NotBlank
    @Email
    @Column(unique = true)
    private String userEmail;
    @NotBlank
    @Size(min = 8,message = "password length should be atLeast 8")
    private String password;

}
