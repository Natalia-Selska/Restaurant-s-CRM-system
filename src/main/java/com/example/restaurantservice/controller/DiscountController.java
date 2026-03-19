package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.AddDiscountDto;
import com.example.restaurantservice.model.Discount;
import com.example.restaurantservice.service.DiscountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public void addDiscount(@Valid @RequestBody AddDiscountDto addDiscountDto) {
        discountService.addDiscount(addDiscountDto);
    }

    @GetMapping
    public List<Discount> getDiscount() {
       return discountService.getDiscounts();
    }
}
