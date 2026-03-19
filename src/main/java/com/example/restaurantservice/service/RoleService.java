package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.AddRoleDto;
import com.example.restaurantservice.model.Role;
import com.example.restaurantservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void addRole(AddRoleDto addRoleDto) {
        String role = addRoleDto.role();
        if (!roleRepository.existsByRole(role)) {
            Role newRole = new Role();
            newRole.setRole(role);
            roleRepository.save(newRole);
        } else {
            throw new RuntimeException("This Role already exist");
        }
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

}
