package com.example.zsocial.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${zsocial.swagger.server-url}")
    private List<String> serverUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        List<Server> servers = new ArrayList<>();
        serverUrl.forEach(url -> {
            Server server = new Server();
            server.setUrl(url);
            server.setDescription("API documentation with JWT authentication for ZSocial System");
            servers.add(server);
        });

        return new OpenAPI()
                .info(new Info()
                        .title("ZSocial System API")
                        .version("1.0")
                        .description("API documentation with JWT authentication"))
                .servers(servers)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Enter JWT token")));
    }
}
