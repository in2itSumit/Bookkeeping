package com.kamjritztex.bookkeeping.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kamjritztex.bookkeeping.entity.Customer;
import com.kamjritztex.bookkeeping.entity.Role;
import com.kamjritztex.bookkeeping.repository.CustomerRepository;
import com.kamjritztex.bookkeeping.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, RoleRepository roleRepository) {
        return args -> {
            // Create and save roles
            Role superAdminRole = createAndSaveRole(roleRepository, "SUPER_ADMIN");
            Role adminRole = createAndSaveRole(roleRepository, "ADMIN");
            Role customerRole = createAndSaveRole(roleRepository, "CUSTOMER");

            // Create and save employees
            createAndSaveCustomer(customerRepository, "superadmin", "superadmin", "superadmin@gmail.com", "superadmin", superAdminRole);
            createAndSaveCustomer(customerRepository, "admin", "admin", "admin@gmail.com", "admin", adminRole);
        };
    }

    private Role createAndSaveRole(RoleRepository roleRepository, String roleName) {
        return roleRepository.findByName(roleName).orElseGet(() -> {
            Role role = new Role();
            role.setName(roleName);
            roleRepository.insert(role);
//            log.debug("Role {} created", roleName);
            return role;
        });
    }


    private void createAndSaveCustomer(CustomerRepository customerRepository, String firstName, String lastName, String email, String password, Role role) {
        if (customerRepository.findByEmailAndStatus(email,true).isEmpty()) {
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setStatus(true);
            customer.setPassword(new BCryptPasswordEncoder().encode(password));
            customer.setRoles(Set.of(role)); // Ensure that 'role' is saved and not transient
            customerRepository.insert(customer);
//            log.debug("Employee {} created with role {}", email, role.getName());
        } else {
//            log.debug("Employee {} already exists", email);
        }
    }
}