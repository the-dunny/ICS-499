package tech.dunny.gameproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tech.dunny.gameproject.repos.PointRepository;


@SpringBootApplication
@EnableJpaRepositories(basePackages = {"repos"})
@EntityScan("entity")
public class GameProjectApplication {
    @Autowired
    private PointRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(GameProjectApplication.class, args);
    }
}
