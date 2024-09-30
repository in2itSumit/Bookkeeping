package com.kamjritztex.bookkeeping.auth;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kamjritztex.bookkeeping.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	@JsonProperty("access_token")
	private String accessToken;
	private String email;
	private String password;
	private Set<Role> roles;
}
