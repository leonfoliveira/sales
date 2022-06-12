package br.edu.unifei.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI openAPI() {
        String securitySchemeName = "AccessToken";
        return new OpenAPI()
                .info(info())
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    private Info info() {
        return new Info()
                .title("Sales")
                .description("Sales management API")
                .version("v1.0")
                .license(new License().name("MIT").url("https://raw.githubusercontent.com/leonfoliveira/sales/main/LICENSE?token=GHSAT0AAAAAABT4PH4HU5RVR5JA7637M3XOYVFD2PQ"));
    }
}
