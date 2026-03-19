package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.AddRestaurantDto;
import com.example.restaurantservice.model.Restaurant;
import com.example.restaurantservice.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public void addRestaurant(AddRestaurantDto addRestaurantDto) {
        String name = addRestaurantDto.name();
        String address = addRestaurantDto.address();
        if (!restaurantRepository.existsByName(name)) {
            Restaurant restaurant = new Restaurant();
            restaurant.setName(name);
            restaurant.setAddress(address);
            restaurantRepository.save(restaurant);
        }else {
            throw new RuntimeException("restaurant already exists");
        }
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public void deleteRestaurant(UUID uuid) {
        if (restaurantRepository.findById(uuid).isPresent()) {
            restaurantRepository.deleteById(uuid);
        } else {
            throw new RuntimeException("Restaurant not found");
        }
    }

    public Restaurant updateRestaurant(UUID id, Restaurant restaurant) {
        String name = restaurant.getName();
        String address = restaurant.getAddress();

        Predicate predicate = (value) -> 1 == 1;
        Supplier supplier = () -> getRestaurants();
        supplier.get();
        predicate.test(id);

        Restaurant oldRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        if (name != null) {
            oldRestaurant.setName(name);
        }
        if (address!= null) {
            oldRestaurant.setAddress(address);
        }
        return restaurantRepository.save(oldRestaurant);
    }
}
