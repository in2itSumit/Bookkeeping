package com.kamjritztex.bookkeeping.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kamjritztex.bookkeeping.entity.Customer;
import com.kamjritztex.bookkeeping.entity.Permission;
import com.kamjritztex.bookkeeping.entity.Role;
import com.kamjritztex.bookkeeping.repository.CustomerRepository;
import com.kamjritztex.bookkeeping.repository.PermissionRepository;
import com.kamjritztex.bookkeeping.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, RoleRepository roleRepository,
                           PermissionRepository permissionRepository) {
        return args -> {
            // Create and save roles
            Role superAdminRole = createAndSaveRole(roleRepository, "SUPER_ADMIN");
            Role adminRole = createAndSaveRole(roleRepository, "ADMIN");
            Role customerRole = createAndSaveRole(roleRepository, "CUSTOMER");

            // Create and save permissions
            Permission readPermission = createAndSavePermission(permissionRepository, "READ_PRIVILEGES");
            Permission writePermission = createAndSavePermission(permissionRepository, "WRITE_PRIVILEGES");
            Permission updatePermission = createAndSavePermission(permissionRepository, "UPDATE_PRIVILEGES");
            Permission deletePermission = createAndSavePermission(permissionRepository, "DELETE_PRIVILEGES");
            Permission updateProfilePermission = createAndSavePermission(permissionRepository, "UPDATE_PROFILE_PRIVILEGES");

            // Assign permissions to roles
            superAdminRole.setPermissions(Set.of(readPermission, writePermission, updatePermission, deletePermission));
            roleRepository.save(superAdminRole);

            adminRole.setPermissions(Set.of(readPermission, writePermission, updatePermission));
            roleRepository.save(adminRole);
            
            customerRole.setPermissions(Set.of(readPermission,updateProfilePermission));
            roleRepository.save(customerRole);

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

    private Permission createAndSavePermission(PermissionRepository permissionRepository, String permissionName) {
        return permissionRepository.findByPermission(permissionName).orElseGet(() -> {
            Permission permission = new Permission();
            permission.setPermission(permissionName);
            permissionRepository.insert(permission);
//            log.debug("Permission {} created", permissionName);
            return permission;
        });
    }

    private void createAndSaveCustomer(CustomerRepository customerRepository, String firstName, String lastName, String email, String password, Role role) {
        if (customerRepository.findByEmailAndStatus(email,true).isEmpty()) {
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPassword(new BCryptPasswordEncoder().encode(password));
            customer.setRoles(Set.of(role)); // Ensure that 'role' is saved and not transient
            customerRepository.insert(customer);
//            log.debug("Employee {} created with role {}", email, role.getName());
        } else {
//            log.debug("Employee {} already exists", email);
        }
    }
}