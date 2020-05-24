package am.rockstars;

import am.rockstars.dto.EmailPayload;
import am.rockstars.service.EmailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Set;

@EnableJpaAuditing
@SpringBootApplication
public class NverApp {
    public static void main(String[] args) {
        SpringApplication.run(NverApp.class, args);
    }
}
