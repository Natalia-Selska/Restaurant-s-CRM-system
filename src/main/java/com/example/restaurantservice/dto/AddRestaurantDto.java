package com.example.restaurantservice.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddRestaurantDto(
        @NotBlank
        String name,
        @NotBlank
        String address
){
}
