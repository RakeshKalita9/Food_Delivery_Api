package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model.SystemProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    public StringBuilder stringBuilder(){
        return new StringBuilder().append("Rakesh@#12");
    }
}
