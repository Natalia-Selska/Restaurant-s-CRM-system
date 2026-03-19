package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.AddRestaurantDto;
import com.example.restaurantservice.model.Restaurant;
import com.example.restaurantservice.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {

    public final RestaurantService restaurantService;

    @PostMapping
    public void addRestaurant(@Valid @RequestBody AddRestaurantDto addRestaurantDto) {
        restaurantService.addRestaurant(addRestaurantDto);
    }

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @DeleteMapping("/{uuid}")
    public void deleteRestaurant(@PathVariable UUID uuid){
        restaurantService.deleteRestaurant(uuid);
    }

    @PatchMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable UUID id, @RequestBody Restaurant restaurant){
     return restaurantService.updateRestaurant(id,restaurant);
    }

}
