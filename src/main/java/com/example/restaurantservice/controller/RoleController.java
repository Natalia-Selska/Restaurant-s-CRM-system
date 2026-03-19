package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.AddRoleDto;
import com.example.restaurantservice.model.Role;
import com.example.restaurantservice.repository.RoleRepository;
import com.example.restaurantservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    @PostMapping
    public void addRole(@RequestBody AddRoleDto addRoleDto) {
        roleService.addRole(addRoleDto);
    }

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}
