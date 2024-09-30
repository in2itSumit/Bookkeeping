package com.kamjritztex.bookkeeping.auth;


import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kamjritztex.bookkeeping.config.JwtService;
import com.kamjritztex.bookkeeping.entity.Customer;
import com.kamjritztex.bookkeeping.repository.CustomerRepository;
import com.kamjritztex.bookkeeping.repository.RoleRepository;
import com.kamjritztex.bookkeeping.service.CustomerUserDetailsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final CustomerRepository repository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final RoleRepository roleRepository;
	private final CustomerUserDetailsService detailsService;

//	public AuthenticationResponse register(ResisterRequest registerRequest) {
//		
//		 var user = Customer.builder()
//				 .firstName(registerRequest.getFirstName())
//				 .lastName(registerRequest.getLastName())
//				 .gender(registerRequest.getGender())
//				 .password(passwordEncoder.encode(registerRequest.getPassword()))
//				 .email(registerRequest.getEmail())
//				 .status("ACTIVE")
//				 .roles(roleRepository.findByName("CUSTOMER").stream().collect(Collectors.toSet()))
//				 .build();
//		 var savedUser = repository.save(user); 
//		 UserDetails userDetails = detailsService.loadUserByUsername(savedUser.getEmail());
////		 String jwtTocken = jwtService.generateToken(savedUser);
//		 String jwtTocken = jwtService.generateToken(userDetails);
//		 return AuthenticationResponse.builder().accessToken(jwtTocken).email(savedUser.getEmail()).roles(savedUser.getRoles()).password(registerRequest.getPassword()).build();
//	
//	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request){
		// First Step(Create been for all)
			// We need to validate our request(validate whether password & username is correct)
			// Verify whether user present in data base
			// Which authentication provider -> DaoAuthenticationProvider(inject)
			// We need to authenticate using authentication manager injecting this authentication provider
		// Second Step
			//Verify whether userName and password is correct or not => UserNamePasswordAuthenticationToken
			//verify whether user present in db
			// Generate Token
			// Return the token
		try {
		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (Exception e) {
			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
		var user = repository.findByEmailAndStatus(request.getEmail(), true).orElseThrow(()-> new UsernameNotFoundException("User Dosen't Exist"));
		UserDetails userDetails = detailsService.loadUserByUsername(user.getEmail());
		String jwtTocken = jwtService.generateToken(userDetails);
		return AuthenticationResponse.builder().accessToken(jwtTocken).email(user.getEmail()).password("**************").roles(user.getRoles()).build();
	}

}
