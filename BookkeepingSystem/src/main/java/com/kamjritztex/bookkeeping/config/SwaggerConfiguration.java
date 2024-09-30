package com.kamjritztex.bookkeeping.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:8088");
		server.setDescription("Security controller");

		Contact myContact = new Contact();
		myContact.setName("Sumit Mishra");
		myContact.setEmail("sumitkumarmishra8235@gmail.com");

		Info information = new Info().title(" Secured Bookkeeping website API").version("1.0")
				.summary("This Project is created to make a double entry Bookkeeping Website with Proper Authentications")
				.description("This API exposes endpoints to handle whole the bookkeeping website.").contact(myContact);
		return new OpenAPI().info(information).addSecurityItem(new SecurityRequirement().addList("JWT Token Authentication")).components(new Components().addSecuritySchemes("JWT Token Authentication", new SecurityScheme()
				.name("JWT Token Authentication").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))).servers(List.of(server));
	}



}
