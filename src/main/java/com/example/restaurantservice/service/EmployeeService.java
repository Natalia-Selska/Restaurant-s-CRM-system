package com.example.restaurantservice.service;

import com.example.restaurantservice.configuration.JwtService;
import com.example.restaurantservice.dto.AddEmployeeDto;
import com.example.restaurantservice.dto.AuthorizationEmployee;
import com.example.restaurantservice.dto.RegistrationEmployeeDto;
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
        String phoneNumber = addEmployeeDto.phoneNumber();
        String firstName = addEmployeeDto.firstName();
        String lastName = addEmployeeDto.lastName();
        LocalDate birthDate = addEmployeeDto.birthDate();
        UUID discountId = addEmployeeDto.discountId();
        BigDecimal salary = addEmployeeDto.salary();
        UUID roleId = addEmployeeDto.roleId();

        if (employeeRepository.existsByPhoneNumber((phoneNumber))) {
            throw new RuntimeException("This employee is exist");
        }
        Role existRoleById = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("This role don't exist"));
        Discount existDiscountById = discountRepository.findById(discountId)
                .orElseThrow(() -> new RuntimeException("This discount don't exist"));
        Employee employee = new Employee();
        employee.setRole(existRoleById);
        employee.setDiscount(existDiscountById);
        employee.setSalary(salary);
        employee.setBirthDate(birthDate);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPhoneNumber(phoneNumber);
        employeeRepository.save(employee);

        //потрібно щоб спочатку додав адмін працівника а потім працівник повинен зареєструватись
        // (логін - телефон, пароль - придумати)
    }

    public void registration(RegistrationEmployeeDto registrationEmployeeDto) {
        String phoneNumber = registrationEmployeeDto.phoneNumber();
        String password = passwordEncoder.encode(registrationEmployeeDto.password());

        Employee employee = employeeRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setPassword(password);
        employeeRepository.save(employee);
    }

    public String authorization(AuthorizationEmployee authorizationEmployee) {
        String phoneNumber = authorizationEmployee.phoneNumber();
        String password = authorizationEmployee.password();

        Employee employee = employeeRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new AuthorizationException("Invalid phone number or password"));

        String name = employee.getFirstName();
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new AuthorizationException("Invalid phone number or password");
        }
       return jwtService.generateToken(employee);

    }
}
