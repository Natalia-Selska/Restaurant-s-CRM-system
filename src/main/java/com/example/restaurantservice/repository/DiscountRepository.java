package com.example.restaurantservice.repository;

import com.example.restaurantservice.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {
    boolean existsByInterest(BigDecimal interest);
    
}
