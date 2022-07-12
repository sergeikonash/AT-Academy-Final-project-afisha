package app.sergeikonash.classifier_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("app.sergeikonash.classifier_service.dao.api")
public class ClassifierApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassifierApplication.class, args);
    }
}
