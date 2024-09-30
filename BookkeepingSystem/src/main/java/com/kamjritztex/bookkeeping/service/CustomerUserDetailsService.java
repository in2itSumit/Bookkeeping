package com.kamjritztex.bookkeeping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kamjritztex.bookkeeping.entity.Customer;
import com.kamjritztex.bookkeeping.repository.CustomerRepository;

@Service
//@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findByEmailAndStatus(email, true);

        if (!customerOptional.isPresent()) {
            throw new UsernameNotFoundException("User not found with email_Id: " + email);
        }

        Customer customer = customerOptional.get();
        
        Set<SimpleGrantedAuthority> permissions = customer.getRoles().stream()
        .flatMap(role -> role.getPermissions().stream())
      .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
      .collect(Collectors.toSet());
        
        List<SimpleGrantedAuthority> roles = customer.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toList());
        
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(roles);
        authorities.addAll(permissions);

        

        return new org.springframework.security.core.userdetails.User(
        		customer.getEmail(),
        		customer.getPassword(),
                authorities
        );
         
        
    }
}