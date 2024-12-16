package danik.may.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@org.springframework.boot.autoconfigure.SpringBootApplication(scanBasePackages = "danik.may")
@ComponentScan(basePackages = {"danik.may.*"})
@EntityScan(basePackages = {"danik.may.entity"})
@EnableJpaRepositories("danik.may.repository")
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

}