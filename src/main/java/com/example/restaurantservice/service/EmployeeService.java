package com.example.restaurantservice.service;

import com.example.restaurantservice.configuration.JwtService;
import com.example.restaurantservice.dto.AddEmployeeDto;
import com.example.restaurantservice.dto.AuthorizationEmployee;
import com.example.restaurantservice.exception.AuthorizationException;
import com.example.restaurantservice.model.Discount;
import com.example.restaurantservice.model.Employee;
import com.example.restaurantservice.model.Role;
import com.example.restaurantservice.repository.DiscountRepository;
import com.example.restaurantservice.repository.EmployeeRepository;
import com.example.restaurantservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DiscountRepository discountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void addEmployee(AddEmployeeDto addEmployeeDto) {
        String email = addEmployeeDto.email();
        UUID discountId = addEmployeeDto.discountId();
        BigDecimal salary = addEmployeeDto.salary();
        UUID roleId = addEmployeeDto.roleId();

        Role existRoleById = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("This role don't exist"));
        Discount existDiscountById = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("This discount don't exist"));

        if (employeeRepository.findByEmail(email).isEmpty()) {
            Employee employee = new Employee();
            employee.setEmail(email);
            employee.setSalary(salary);
            employee.setDiscount(existDiscountById);
            employee.setRole(existRoleById);
            employeeRepository.save(employee);
        }
        //потрібно щоб спочатку додав адмін працівника а потім працівник повинен зареєструватись
    }

    public void registration(AddEmployeeDto addEmployeeDto) {
        String phoneNumber = addEmployeeDto.phoneNumber();
        String firstName = addEmployeeDto.firstName();
        String lastName = addEmployeeDto.lastName();
        LocalDate birthDate = addEmployeeDto.birthDate();
        String password = passwordEncoder.encode(addEmployeeDto.password());
        String email = addEmployeeDto.email();

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setPhoneNumber(phoneNumber);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setBirthDate(birthDate);
        employee.setPassword(password);
        employeeRepository.save(employee);
    }

    public String authorization(AuthorizationEmployee authorizationEmployee) {
        String email = authorizationEmployee.email();
        String phoneNumber = authorizationEmployee.phoneNumber();
        String password = authorizationEmployee.password();

        Employee employee = employeeRepository.findByEmailOrPhoneNumber(email, phoneNumber)
                .orElseThrow(() -> new AuthorizationException("Invalid login or password"));

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new AuthorizationException("Invalid login or password");
        }

        return jwtService.generateToken(employee);
    }
}
