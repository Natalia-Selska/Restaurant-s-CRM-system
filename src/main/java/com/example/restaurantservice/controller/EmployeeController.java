package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.AddEmployeeDto;
import com.example.restaurantservice.dto.AuthorizationEmployee;
import com.example.restaurantservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public void addEmployee(@RequestBody AddEmployeeDto addEmployeeDto) {
        employeeService.addEmployee(addEmployeeDto);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody AddEmployeeDto addEmployeeDto) {
        employeeService.registration(addEmployeeDto);
    }

    @PostMapping("/authorization")
    public String  authorization(@RequestBody AuthorizationEmployee authorizationEmployee) {
       return employeeService.authorization(authorizationEmployee);
    }
}
