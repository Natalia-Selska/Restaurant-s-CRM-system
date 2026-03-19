package com.example.restaurantservice.repository;

import com.example.restaurantservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByRole(String role);

    Optional<Role> findById(UUID id);
}
