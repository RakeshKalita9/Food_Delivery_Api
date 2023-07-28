package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.SystemProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {
     @Autowired
     StringBuilder sb;
     public String getSystemPassword(){
         return sb.toString();
     }
}
