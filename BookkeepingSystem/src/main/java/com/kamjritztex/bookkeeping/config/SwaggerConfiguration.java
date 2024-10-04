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
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8089");
        devServer.setDescription("Server URL in Development environment");

        Info info = new Info()
                .title("Employee CRUD REST API")
                .version("1.0")
                .description("This API exposes endpoints to manage SPIKE crud operations.")
                .termsOfService("https://www.w3.org/Provider/Style/dummy.html");
        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer))
                .addSecurityItem(new SecurityRequirement()
                        .addList("JavaInUseSecurityScheme"))
                .components(new Components()
                        .addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
                                .name("JavaInUseSecurityScheme")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                );
    }
//    @Bean
//    public OpenAPI myOpenAPI() {
//        Server devServer = new Server();
//        devServer.setUrl("http://localhost:8089");
//        devServer.setDescription("Server URL in Development environment");
//
//        Info info = new Info()
//                .title("Customer CRUD REST API")
//                .version("1.0")
//                .description("This API exposes endpoints to manage Bookkeeping Crud.");
////                .termsOfService("https://www.w3.org/Provider/Style/dummy.html");
//        return new OpenAPI()
//                .info(info)
//                .servers(List.of(devServer))
//                .addSecurityItem(new SecurityRequirement()
//                        .addList("JavaInUseSecurityScheme"))
//                .components(new Components()
//                        .addSecuritySchemes("JavaInUseSecurityScheme", new SecurityScheme()
//                                .name("JavaInUseSecurityScheme")
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("bearer")
//                                .bearerFormat("JWT"))
//                );
//    }



}
