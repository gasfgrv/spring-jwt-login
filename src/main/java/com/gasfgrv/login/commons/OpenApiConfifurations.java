package com.gasfgrv.login.commons;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "login api",
        description = "Api que valida um usuário a partir de um token JWT",
        version = "V1",
        contact = @Contact(
                name = "gasfgrv",
                email = "gustavo_almeida11@hotmail.com",
                url = "https://github.com/gasfgrv")
))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfifurations {

    @Bean
    public GroupedOpenApi logApi() {
        return GroupedOpenApi.builder()
                .group("api-v1")
                .packagesToScan("com.gasfgrv.login.api.controller")
                .pathsToMatch("/**")
                .build();
    }

}
