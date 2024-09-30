package com.kamjritztex.bookkeeping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.kamjritztex.bookkeeping.exception.JwtAccessDeniedHandler;
import com.kamjritztex.bookkeeping.exception.JwtAuthEntryPoint;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@AllArgsConstructor
@Slf4j
public class SecurityConfiguration {
	
	
	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthFilter jwtAuthFilter;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
	private final JwtAccessDeniedHandler accessDeniedHandler;
	private final RoleHierarchy roleHierarchy;

	private static final String[] WHITE_LIST_URL = { "/api/auth/**", "/api-docs", "/api-docs",
			"/api-docs/**", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
			"/configuration/security", "/swagger-ui/**", "/webjars/**", "/swagger-ui.html", "/api/auth/**",
			 "/authenticate"};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(req ->
		req.requestMatchers(WHITE_LIST_URL).permitAll()
			.requestMatchers("/bookeeping/auth/authenticate/login").permitAll()
				.requestMatchers("bookkeeping/customer/*").permitAll()
				.requestMatchers("bookkeeping/customer/create").hasAnyRole("SUPER_ADMIN","ADMIN")
				.requestMatchers("bookkeeping/customer/update").permitAll()
				.requestMatchers("bookkeeping/customer/get-by-id").hasAnyRole("SUPER_ADMIN","ADMIN")
				.requestMatchers("bookkeeping/customer/get-by-email").hasAnyRole("SUPER_ADMIN","ADMIN")
				.requestMatchers("bookkeeping/customer/get-all").hasAnyRole("SUPER_ADMIN","ADMIN")
				.requestMatchers("bookkeeping/customer/delete-by-id").hasRole("SUPER_ADMIN")
//				.requestMatchers("in2it/employees/**").hasAnyRole("SUPER_ADMIN","ADMIN","EMPLOYEE")
				.requestMatchers(HttpMethod.GET,"bookkeeping/customer/**").hasAnyAuthority("READ_PRIVILEGES")
//				.requestMatchers(HttpMethod.GET,"in2it/departments/**").hasAnyAuthority("READ_PRIVILEGES")
				.requestMatchers(HttpMethod.POST,"bookkeeping/customer/**").hasAnyAuthority("WRITE_PRIVILEGES")
				.anyRequest().authenticated()
				)

		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
		.exceptionHandling(e->e.accessDeniedHandler(accessDeniedHandler))

				.build();
	}

	
}



