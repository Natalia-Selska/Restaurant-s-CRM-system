package com.example.restaurantservice.configuration;

import com.example.restaurantservice.model.Employee;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY = "my-secret-key-my-secret-key-my-secret-key";

    public String generateToken(Employee employee) {
        return Jwts.builder()
                .setSubject(employee.getPhoneNumber())
                .claim(employee.getFirstName(), "FIRST_NAME")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 година
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); }
}
