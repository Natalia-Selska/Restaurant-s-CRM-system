package com.example.restaurantservice.dto;

public record AuthorizationEmployee(

        String password,
        String email,
        String phoneNumber
) {
}
