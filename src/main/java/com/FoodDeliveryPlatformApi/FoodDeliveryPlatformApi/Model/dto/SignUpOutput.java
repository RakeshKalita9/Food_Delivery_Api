package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpOutput {
    private String signUpMessage;
    private Boolean status;
}
