package com.FoodDeliveryPlatformApi.FoodDeliveryPlatformApi.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private LocalDateTime orderPlaceTime;

    @ManyToOne
    @JoinColumn(name = "fk_user_userId")
    private User user;

    @ManyToMany
    @JoinTable(name="join_Table_Order_foodList")
    private List<FoodItem> foodItemList;

}
