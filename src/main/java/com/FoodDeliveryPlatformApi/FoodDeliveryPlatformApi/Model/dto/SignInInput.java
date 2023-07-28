package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInput {
    private  String Email;
    private String Password;
}
