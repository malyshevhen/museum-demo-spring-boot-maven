package com.example;

import java.util.stream.Stream;

import com.example.gallery.domain.Artist;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.example.gallery.domain.Artwork;
import com.example.services.gallery.ArtworkService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.RequiredArgsConstructor;

@OpenAPIDefinition(
        info = @Info(
                title = "Museum Demo Application",
                version = "0.0",
                description = "Application create for demonstration herpeses.",
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
@ComponentScan(basePackages = "com.example.*")
@EntityScan(basePackages = "com.example.*")
@EnableJpaRepositories(basePackages = "com.example.*")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}

@Component
@RequiredArgsConstructor
class TestData implements CommandLineRunner {
    private final ArtworkService artWorkService;

    @Override
    public void run(String... args) {
        Stream.of(
                new Artwork("Monna Lisa", new Artist("Leonardo", "Da Vinci")),
                new Artwork("Black Square", new Artist("Kazimir", " Malevich")),
                new Artwork("Sunflowers", new Artist("Vincent", "Van Gogh"))
        ).toList().forEach(artWorkService::save);
    }
}
