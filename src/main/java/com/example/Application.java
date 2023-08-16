package com.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
