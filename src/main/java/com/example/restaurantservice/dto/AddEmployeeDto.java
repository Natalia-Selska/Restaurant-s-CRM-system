package com.example.restaurantservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AddEmployeeDto(
        String phoneNumber,
        String firstName,
        String lastName,
        LocalDate birthDate,
        UUID discountId,
        BigDecimal salary,
        UUID roleId,
        String email,
        String password

) {
}
