package tech.dunny.cap_refactored;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import tech.dunny.model.Player;
import tech.dunny.repositories.PlayerRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"tech.dunny.repositories"})
@EntityScan("tech.dunny.model")
public class CapRefactoredApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapRefactoredApplication.class, args);
    }

}

@Component
class CapstoneCommandLineRunner implements CommandLineRunner{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        Player player = new Player();
        player.setPlayerID(1L);
        player.setUserName("jordan@dunny.tech");
        player.setPassword("Password");
        player.setBestScore(1234);

        playerRepository.save(player);

    }
}
