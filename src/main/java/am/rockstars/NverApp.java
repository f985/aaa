package am.rockstars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NverApp {
    public static void main(String[] args) {
        SpringApplication.run(NverApp.class, args);
    }
}
