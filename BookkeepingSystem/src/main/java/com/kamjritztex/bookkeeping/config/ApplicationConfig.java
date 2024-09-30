package com.kamjritztex.bookkeeping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kamjritztex.bookkeeping.service.CustomerUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

//	private final EmployeeRepository repository;
	private final CustomerUserDetailsService userDetailsService;

	/*
	 * Bean for password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	 * Bean for user details service
	 */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> repository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
	@Bean
	public UserDetailsService userDetailsService() {

		return email -> userDetailsService.loadUserByUsername(email);

	}

	/*
	 * Bean for authentication provider
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	/*
	 * Bean for authentication manager
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	/*
	 * Bean for Role hierarchy
	 */
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = "SUPER_ADMIN > ADMIN\n" + "ADMIN > CUSTOMER";
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}


}
