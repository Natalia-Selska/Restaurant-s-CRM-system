package com.example.restaurantservice.repository;

import com.example.restaurantservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByEmailOrPhoneNumber(String email,String phoneNumber);
}
