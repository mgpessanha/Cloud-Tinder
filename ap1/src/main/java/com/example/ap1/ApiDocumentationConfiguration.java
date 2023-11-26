package com.example.ap1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
    
@Configuration
public class ApiDocumentationConfiguration { 

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cloud - IBMEC")
                        .description("Ap1 de Cloud")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Maria Giulia")
                                .email("mgpessanh@gmail.com")));
    }
}
