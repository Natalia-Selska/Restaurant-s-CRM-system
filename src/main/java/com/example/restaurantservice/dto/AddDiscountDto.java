package com.example.restaurantservice.dto;

import java.math.BigDecimal;

public record AddDiscountDto(
         BigDecimal interest,
         Integer requiredAmound,
         String description
){
}
