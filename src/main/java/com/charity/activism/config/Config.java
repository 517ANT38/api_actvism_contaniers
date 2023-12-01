package com.charity.activism.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class Config {
    
    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement()        
           .addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes
            ("Bearer Authentication", createAPIKeyScheme()))
           .info(new Info()
                    .title("Activism and charity API")
                    .description("""
                        Default users: login:admin,password:admin,role:ADMIN;
                                       login:fisrtUser,password:fisrtUser,role:USER  
                    """)
            );
    }
    

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
