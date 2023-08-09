package com.example.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@OpenAPIDefinition(
        info = @Info(
                title = "Museum Demo Application",
                version = "0.0",
                description = "Application create for demonstration purposes.",
                license = @License(
                        name = "Apache 2.0",
                        url = "http://example.com/licence"),
                contact = @Contact(
                        url = "http://example.com/contacts",
                        name = "Evhen Malysh",
                        email = "email@example.com")
        )
)
@SpringBootApplication(scanBasePackages = "com.example.*")
@ComponentScan(basePackages = "com.example.*")
@EntityScan(basePackages = "com.example.*")
@EnableJpaRepositories(basePackages = "com.example.*")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
