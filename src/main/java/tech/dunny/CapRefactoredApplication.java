package tech.dunny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import tech.dunny.model.Player;
import tech.dunny.repositories.PlayerRepository;
import tech.dunny.services.PlayerServiceImpl;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CapRefactoredApplication {
    public static void main(String... args) {
        SpringApplication.run(CapRefactoredApplication.class, args);
    }
}

/*@Component
class CapstoneCommandLineRunner implements CommandLineRunner {

    @Autowired
    private PlayerServiceImpl impl;

    @Override
    public void run(String... args) throws Exception {
        impl.addPlayer(
                new Player(
                        0L, "Service2", "fromCMDRunner2", 12344
                )

        );
    }

    *//*
    @Override
    public void run(String... args) throws Exception {
        Player player = new Player();
        player.setPlayerID((playerRepository.count()+1));
        player.setUserName("cmdLineRunner3");
        player.setPassword("11_34_10_17");
        player.setBestScore(1234);

        playerRepository.save(player);
    *//*
}*/

