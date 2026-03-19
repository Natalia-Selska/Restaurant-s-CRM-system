package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.AddDiscountDto;
import com.example.restaurantservice.model.Discount;
import com.example.restaurantservice.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public void addDiscount(AddDiscountDto addDiscountDto) {
        BigDecimal interest = addDiscountDto.interest();
        Integer requiredAmound = addDiscountDto.requiredAmound();
        String description = addDiscountDto.description();
        if (!discountRepository.existsByInterest(interest)) {
            Discount discount = new Discount();
            discount.setDescription(description);
            discount.setInterest(interest);
            discount.setRequiredAmound(requiredAmound);
            discountRepository.save(discount);
        } else {
            throw new RuntimeException("This discount already exist");
        }
    }


    public List<Discount> getDiscounts() {
        return discountRepository.findAll();
    }
}
