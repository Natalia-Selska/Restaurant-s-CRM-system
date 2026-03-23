package com.example.restaurantservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

}
